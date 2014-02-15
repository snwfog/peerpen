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
    private Integer childId;

    @ActiveRecordField
    private Integer userId;

    private Feedable trueSelf;

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

            if ( this instanceof Changeset ) {
                Changeset ch = (Changeset) this;
                if ( ch.getHunk().getDocument() != null ) {
                    a.setUserId( ch.getHunk().getDocument().getPeerId() );
                }
                a.saveFeedable();
            }

            if ( this instanceof Comment ) {
                Comment com = (Comment) this;
                if ( com.getDocumentId() != 0 ) {
                    if ( com.getDocument() != null ) {
                        a.setUserId( com.getDocument().getPeerId() );
                    }
                } else {
                    if ( com.getChangeset() != null ) {
                        a.setUserId( com.getChangeset().getHunk().getDocument().getPeerId() );
                    }
                }
                a.saveFeedable();
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
            System.out.println( "FOUND FEEDABLE" );
            a.setStatus( "update" );
            if ( this instanceof Changeset ) {
                Changeset ch = (Changeset) this;
                if ( ch.getHunk().getDocument() != null ) {
                    a.setUserId( ch.getHunk().getDocument().getPeerId() );
                }
                a.updateFeedable();
            }
            if ( this instanceof Comment ) {
                Comment com = (Comment) this;
                if ( com.getDocumentId() != 0 ) {
                    if ( com.getDocument() != null ) {
                        a.setUserId( com.getDocument().getPeerId() );
                    }
                } else {
                    if ( com.getChangeset() != null ) {
                        a.setUserId( com.getChangeset().getHunk().getDocument().getPeerId() );
                    }
                    a.updateFeedable();
                }
            }
            if ( this instanceof Broadcast ) {
                return a.updateFeedable();
            }
            return true;
        }
    }

    @Override
    public boolean destroy() {
        Map<String, Object> map = new HashMap<>();
        map.put( "childId", this.getId() );

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

    public boolean updateFeedable() {
        System.out.println( this.getStatus() );
        System.out.println( "updating feedable" );
        return super.update();
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
}
