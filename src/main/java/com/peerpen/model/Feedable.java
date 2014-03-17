package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.annotations.ActiveRecordField;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.ocpsoft.prettytime.PrettyTime;

public class Feedable extends Base {

    @ActiveRecordField
    private String type;

    @ActiveRecordField
    private String status;

    @ActiveRecordField
    private String notifyStatus;

    @ActiveRecordField
    private Integer childId;

    @ActiveRecordField
    private Integer userId;

    private Feedable trueSelf;  //Actual Object that feedable is attached to

    private String modelPath = "com.peerpen.model.";


    public Feedable() {
        super();
    }

    public Feedable( Map<String, Object> HM ) {
        super( HM );
    }

    @Override
    public boolean save() {
        Feedable a = new Feedable();
        super.save();
        System.out.println( this.getId() );
        if ( this.getId() != null ) {
            a.setType( this.getClass().getSimpleName() );
            a.setChildId( this.getId() );
            a.setStatus( "new" );
            a.setNotifyStatus( "UNSEND" );

            if ( this instanceof Changeset ) {
                Changeset ch = (Changeset) this;
                if ( ch.getHunk() != null ) {
                    a.setUserId( ch.getPeerId() );
                    return a.saveFeedable();
                }

            }

            if ( this instanceof Comment ) {
                Comment com = (Comment) this;
                if ( com.getType().contentEquals( "Document" ) ) {
                    Document doc = new Document().find( com.getObjectId() );
                    if ( doc != null ) {
                        a.setUserId( doc.getPeerId() );
                        return a.saveFeedable();
                    }
                } else if ( com.getType().contentEquals( "Changeset" ) ) {
                    Changeset cs = new Changeset().find( com.getObjectId() );
                    if ( cs != null ) {
                        a.setUserId( cs.getPeerId() );
                        return a.saveFeedable();
                    }
                }

            }

            if ( this instanceof Broadcast ) {
                Broadcast bc = (Broadcast) this;
                if ( bc.getGroup() != null ) {
                    for ( Peer p : bc.getGroup().getPeers() ) {
                        Feedable feedable = new Feedable();
                        feedable.setType( this.getClass().getSimpleName() );
                        feedable.setChildId( this.getId() );
                        feedable.setStatus( "new" );
                        feedable.setUserId( p.getId() );
                        feedable.saveFeedable();
                    }
                }
                return true;
            }

            if ( this instanceof Joingroup ) {
                Joingroup jg = (Joingroup) this;
                if ( jg.getGroup() != null ) {
                    a.setUserId( jg.getGroup().getAdminId() );
                }
                return a.saveFeedable();
            }

        }
        return false;

    }

    private boolean saveFeedable() {
        return super.save();
    }

    @Override
    public boolean update() {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put( "childId", this.getId() );
        Feedable a = new Feedable().find( m );
        super.update();
        if ( a == null ) {
            System.out.println( "NO FEEDABLE FOR OBJECT!!!!" );
            return false;
        } else {
            a.setStatus( "update" );
            a.setNotifyStatus( "UNSEND" );
            if ( this instanceof Changeset ) {
                a.updateFeedable();
            }
            if ( this instanceof Comment ) {
                a.updateFeedable();
            }
            if ( this instanceof Broadcast ) {
                //TODO: if user update his broadcast then you need to set all feedable to update status
                return a.updateFeedable();
            }

            if ( this instanceof Joingroup ) {
                return a.updateFeedable();
            }
            return true;
        }
    }


    public boolean updateFeedable() {
        System.out.println( this.getStatus() );
        System.out.println( "updating feedable" );
        return super.update();
    }

    @Override
    public boolean destroy() {
        Map<String, Object> map = new HashMap<>();
        map.put( "childId", this.getId() );
        map.put( "type", this.getClass().getSimpleName() );

        if ( this instanceof Broadcast ) {
            List<Feedable> feeds = new Feedable().findAll( map );
            boolean deletedAllFeedable = false;
            for ( Feedable a : feeds ) {
                deletedAllFeedable = a.baseDestroy();
                if ( !deletedAllFeedable ) {
                    System.out.println( "CANNOT DELETE FEEDABLE" );
                    break;

                }

            }

            return this.baseDestroy() && deletedAllFeedable;

        } else {
            Feedable a = new Feedable().find( map );
            super.destroy();
            if ( this.getId() == null ) {
                if ( a != null ) {
                    return a.baseDestroy();
                } else {
                    System.out.println( "NO FEEDABLE FOR OBJECT!!!!" );
                    return false;
                }
            }
        }
        return false;
    }

    public boolean baseDestroy() {
        return super.destroy();
    }



    private Feedable reveal() {
        if ( trueSelf == null ) {
            try {
                Constructor cons = Class.forName( modelPath + this.getType() ).getConstructor();
                trueSelf = (Feedable) Class.forName( modelPath + this.getType() )
                        .getMethod( "find", int.class )
                        .invoke( cons.newInstance(), this.getChildId() );
            } catch ( NoSuchMethodException e ) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch ( ClassNotFoundException e ) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch ( InvocationTargetException e ) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch ( IllegalAccessException e ) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch ( InstantiationException e ) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return trueSelf;
    }


    public static void revealList( List<Feedable> feedables ) {
        for ( Feedable f : feedables ) {
            Integer index = feedables.indexOf( f );
            feedables.add( index, f.reveal() );
        }
    }

    public static List<Feedable> getFeed( Peer p ) {
        return getFeed( p.getId() );
    }

    public static List<Feedable> getFeed( int id ) {
        DSLContext jooq = startQuery();

        Field<?> allField = DSL.field( "a.*" );
        Field<?> lastModifiedDate = DSL.field( "a.last_modified_date" );

        String query = jooq.select( allField )
                .from( "feedables a" )
                .where( "a.user_id = " + id )
                .orderBy( lastModifiedDate.desc() )
                .toString();
        return new Feedable().queryAll( query );
    }

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
        setUpdateFlag( true );
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId( Integer childId ) {
        this.childId = childId;
        setUpdateFlag( true );
    }

    public String getStatus() {
        return status;
    }

    public void setStatus( String status ) {
        this.status = status;
        setUpdateFlag( true );
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId( Integer userId ) {
        this.userId = userId;
        setUpdateFlag( true );
    }

    public Feedable getTrueSelf() {
        reveal();
        return trueSelf;
    }

    public String getTimesAgo() {
        return new PrettyTime().format( getCreationDate() );
    }

    public String getNotifyStatus() {
        return notifyStatus;
    }

    public void setNotifyStatus( String notifyStatus ) {

        this.notifyStatus = notifyStatus;
        setUpdateFlag( true );
    }
}
