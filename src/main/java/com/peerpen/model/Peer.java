package com.peerpen.model;

import com.google.common.collect.Maps;
import com.peerpen.framework.InternalHttpServletRequest;
import com.peerpen.framework.exception.MissingArgumentException;
import com.peerpen.framework.exception.NotLoggedInException;
import com.sunnyd.Base;
import com.sunnyd.annotations.ActiveRecordField;
import com.sunnyd.annotations.ActiveRelationHasMany;
import com.sunnyd.annotations.ActiveRelationHasOne;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Peer extends Base {

    static final Logger logger = LoggerFactory.getLogger( Peer.class );
    public static final String tableName = "peers";
    @ActiveRecordField
    private String firstName;
    @ActiveRecordField
    private String lastName;
    @ActiveRecordField
    private String email;
    @ActiveRecordField
    private String userName;
    @ActiveRecordField
    private String password;
    @ActiveRecordField
    private Integer point;
    @ActiveRecordField
    private Integer rankId;
    @ActiveRecordField
    private String personalWebsite;
    @ActiveRecordField
    private String description;
    @ActiveRecordField
    private Date dateOfBirth;
    @ActiveRecordField
    private Integer experience;
    @ActiveRecordField
    private String country;
    @ActiveRecordField
    private String industry;
    @ActiveRecordField
    private String gender;
    @ActiveRelationHasMany
    private List<Document> documents;
    @ActiveRelationHasMany
    private List<Changeset> changesets;
    @ActiveRelationHasOne
    private Avatar avatar;
    @ActiveRecordField
    private Integer avatarId;

    public Peer() {
        super();
    }

    // TODO: We need to add a parameterized constructor here
    public Peer( Map<String, Object> HM ) {
        super( HM );
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
        setUpdateFlag( true );
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
        setUpdateFlag( true );
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
        setUpdateFlag( true );
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName( String userName ) {
        this.userName = userName;
        setUpdateFlag( true );
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
        setUpdateFlag( true );
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint( Integer point ) {
        this.point = point;
        setUpdateFlag( true );
    }

    public Integer getRankId() {
        return rankId;
    }

    public void setRankId( Integer rankId ) {
        this.rankId = rankId;
        setUpdateFlag( true );
    }

    public String getPersonalWebsite() {
        if ( personalWebsite == null ) {
            return "";
        } else {
            return personalWebsite;
        }
    }

    public void setPersonalWebsite( String personalWebsite ) {
        this.personalWebsite = personalWebsite;
        setUpdateFlag( true );
    }

    public String getDescription() {
        if ( description == null ) {
            return "";
        } else {
            return description;
        }
    }

    public void setDescription( String description ) {
        this.description = description;
        setUpdateFlag( true );
    }

    public List<Document> getDocuments() {
        initRelation( "documents" );
        return this.documents;
    }

    public void setDocuments( List<Document> docs ) {
        this.documents = docs;
    }

    public List<Changeset> getChangesets() {
        initRelation( "changesets" );
        return this.changesets;
    }

    public void setChangesets( List<Changeset> changeSets ) {
        this.changesets = changeSets;
    }

    public static void main( String[] args ) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put( "email", "asn.brotha@gmail.com" );

        List<Peer> matches = new Peer().findAll( map );


        System.out.println( matches.get( 0 ) );
        Peer pear = matches.get( 0 );
        pear.setRankId( null );
        pear.setPassword( "HAHAHAHA" );
        pear.update();
        //        Peer a = new Peer().find(3);
        //        System.out.println(a.getFirstName());
        //        a.setFirstName("dsfdsaf");
        //        a.update();

    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth( Date dateOfBirth ) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getExperience() {
        if ( experience == null ) {
            return 0;
        } else {
            return experience;
        }
    }

    public void setExperience( Integer experience ) {
        this.experience = experience;
    }

    public String getCountry() {
        if ( country == null ) {
            return "";
        } else {
            return country;
        }
    }

    public void setCountry( String country ) {
        this.country = country;
    }

    public String getIndustry() {
        if ( industry == null ) {
            return "";
        } else {
            return industry;
        }
    }

    public void setIndustry( String industry ) {
        this.industry = industry;
    }

    public String getGender() {
        return gender;
    }

    public void setGender( String gender ) {
        this.gender = gender;
    }

    public Avatar getAvatar() {
        initRelation( "avatar" );
        return avatar;
    }

    public void setAvatarId( Integer avatarId ) {
        this.avatarId = avatarId;
    }

    public int getAvatarId() {
        return this.avatarId;
    }


    public List<Peer> getMatchedPeers( String keyword ) {
        String sql =
                "SELECT * FROM `peers` WHERE `user_name` LIKE '%" + keyword + "%' OR `first_name` LIKE '%" + keyword +
                        "%' OR `last_name` LIKE '%" + keyword + "%'";
        List<Peer> peers = new Peer().queryAll( sql );
        return peers;
    }

    public List<String> getSuggestedPeers( String keyword, int limit ) {
        String sql = "SELECT `user_name` FROM `peers` WHERE `user_name` LIKE '%" + keyword + "%' LIMIT " + limit;
        List<Peer> peers = new Peer().queryAll( sql );
        List<String> suggestions = new ArrayList<String>();
        if ( peers.size() > 0 ) {
            for ( int i = 0; i < peers.size(); i++ ) {
                suggestions.add( peers.get( i ).getUserName() );
            }
        }
        return suggestions;
    }

    public static Peer isLoggedIn( HttpServletRequest httpRequest ) {
        Map<String, String> param = (Map<String, String>) httpRequest.getAttribute( "urlParameters" );
        if ( param != null ) {
        }

        return new Peer().find( 2 );
    }

    public static boolean isValidSession( Peer peer, HttpSession session ) {
        return true;
    }

    public static boolean isValidLogin( HttpServletRequest request )
            throws NotLoggedInException, OperationNotSupportedException, MissingArgumentException {
        InternalHttpServletRequest internalRequest = InternalHttpServletRequest.transform( request );
        internalRequest.expectPresenceOf( "username", "password" );
        Map<String, Object> m = (Map<String, Object>) internalRequest.getParameterMap();
        Map<String, Object> map = Maps.newHashMap();
        map.put( "userName", ((String[]) m.get( "username" ))[0] );
        map.put( "password", ((String[]) m.get( "password" ))[0] );

        logger.info( "Retrieving peer with " + map );

        Peer p = (new Peer()).find( map );
        if ( p == null ) {
            throw new NotLoggedInException( request.getSession() );
        }

        return true;
    }

    @Override
    public String toString() {
        return String.format( "[Peer][%s][%s] (Override me in %s)", this.firstName, this.lastName,
                this.getClass().toString() );
    }
}
