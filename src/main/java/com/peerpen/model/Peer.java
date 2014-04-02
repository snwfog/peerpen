package com.peerpen.model;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.peerpen.framework.InternalHttpServletRequest;
import com.peerpen.framework.exception.HttpSessionException;
import com.peerpen.framework.exception.MissingArgumentException;
import com.peerpen.framework.exception.PermissionDeniedException;
import com.peerpen.framework.exception.UserNotFoundException;
import com.peerpen.helper.Email;
import com.sunnyd.Base;
import com.sunnyd.annotations.ActiveRecordField;
import com.sunnyd.annotations.ActiveRelationHasMany;
import com.sunnyd.annotations.ActiveRelationHasOne;
import com.sunnyd.annotations.ActiveRelationManyToMany;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;
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
    @ActiveRecordField
    private String sessionId;
    @ActiveRecordField
    private Integer completeProfile;
    @ActiveRelationManyToMany(relationTable = "peers_groups")
    private List<Group> groups;

    public Peer() {
        super();
    }

    public Peer( Map<String, Object> HM ) {
        super( HM );
    }

    public void giveDefaultAvatar() {
        this.avatar = Avatar.getDefaultAvatar();
        this.avatar.setPeerId( this.getId() );
        this.avatar.save();
        this.setAvatarId( this.avatar.getId() );
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

    public String getSessionId() {
        if ( sessionId == null ) {
            return "";
        }
        return sessionId;
    }

    public void setSessionId( String sessionId ) {
        this.sessionId = sessionId;
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

    public List<Group> getGroups() {
        initRelation( "groups" );
        return this.groups;
    }

    public Peer setGroups( List<Group> groups ) {
        this.groups = groups;
        this.setUpdateFlag( true );
        return this;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth( Date dateOfBirth ) {
        this.dateOfBirth = dateOfBirth;
        setUpdateFlag( true );
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
        setUpdateFlag( true );
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
        setUpdateFlag( true );
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
        setUpdateFlag( true );
    }

    public String getGender() {
        return gender;
    }

    public void setGender( String gender ) {
        this.gender = gender;
        setUpdateFlag( true );
    }

    public boolean hasAvatar() {
        return this.avatar != null && !this.avatar.getFilename().equalsIgnoreCase( Avatar.DEFAULT_AVATAR_FILENAME );
    }

    public Avatar getAvatar() {
        initRelation( "avatar" );

        if ( this.avatar == null ) {
            this.avatar = Avatar.getDefaultAvatar();
            this.avatar.setPeerId( this.getId() );
            if ( this.avatar.save() ) {
                this.setAvatarId( this.avatar.getId() );
            }
        }

        return this.avatar;
    }

    public void setAvatarId( Integer avatarId ) {
        this.avatarId = avatarId;
        setUpdateFlag( true );
    }

    public Integer getAvatarId() {
        return this.avatarId;
    }

    public void setCompleteProfile( Integer completeProfile ) {
        this.completeProfile = completeProfile;
        setUpdateFlag( true );
    }

    public Integer getCompleteProfile() {
        return this.completeProfile;
    }

    public static Peer instantiateFromSessionId( HttpServletRequest httpRequest ) {
        Map<String, Object> cond = ImmutableMap.of( "sessionId", (Object) httpRequest.getSession().getId() );
        return (new Peer()).find( cond );
    }

    public static boolean isValidSession( Peer peer, HttpSession session ) {
        return true;
    }

    public static Peer isValidLogin( HttpServletRequest request )
            throws UserNotFoundException, OperationNotSupportedException, MissingArgumentException,
            HttpSessionException {
        InternalHttpServletRequest internalRequest = InternalHttpServletRequest.transform( request );
        internalRequest.expectPresenceOf( "username", "password" );
        Map<String, Object> m = (Map<String, Object>) internalRequest.getParameterMap();
        Map<String, Object> map = Maps.newHashMap();
        map.put( "userName", ((String[]) m.get( "username" ))[0] );
        map.put( "password", ((String[]) m.get( "password" ))[0] );

        logger.info( "Retrieving peer with " + map );

        Peer p = (new Peer()).find( map );
        if ( p == null ) {
            throw new UserNotFoundException(
                    "Could not find user with value of " + map + " (this message shall be kept secret..." );
        }

        p.setSessionId( request.getSession().getId() );
        if ( !p.update() ) {
            throw new HttpSessionException(
                    "Could not update peer " + p.toString() + " with sessionId of " + request.getSession().getId() );
        }
        return p;
    }

    @Override
    public String toString() {
        return String.format( "[Peer][%s][%s] (Override me in %s)", this.firstName, this.lastName,
                this.getClass().toString() );
    }

    public Document getDocument( int docId ) throws PermissionDeniedException {
        Map<String, Object> map = Maps.newHashMap();
        map.put( "id", docId );
        map.put( "peer_id", this.getId() );
        Document document = new Document().find( map );

        if ( document == null ) {
            throw new PermissionDeniedException(
                    "Permission denied, Peer " + this.getId() + " is not owner of Document " + docId );
        }

        return document;
    }


    public void resetPassword() {
        String password = getRandomPassword();
        setPassword( password );

        boolean status = Email.email( this.getEmail(), this.getFirstName(), password );

    }

    private String getRandomPassword() {
        return RandomStringUtils.randomAlphabetic( 2 ) + RandomStringUtils.randomAlphanumeric( 7 );
    }

    @Override
    public boolean equals( Object other ) {
        if ( other == null ) {
            return false;
        }
        if ( other == this ) {
            return true;
        }
        if ( !(other instanceof Peer) ) {
            return false;
        }
        Peer peerObj = (Peer) other;
        if ( this.getId() == peerObj.getId() ) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append( this.getId() ).toHashCode();
    }

}
