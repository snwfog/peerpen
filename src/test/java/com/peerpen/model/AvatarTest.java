package com.peerpen.model;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.peerpen.controller.AvatarController;
import com.peerpen.framework.InternalHttpServletRequest;
import com.sunnyd.database.Manager;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Zearf on 2014-03-11.
 */
public class AvatarTest {

    @Test
    public void avatarCreateTest() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put( "filename", "avatarTest.jpg" );
        attributes.put( "peerId", 1 );
        attributes.put( "x1", 0.0 );
        attributes.put( "x2", 256.0 );
        attributes.put( "y1", 0.0 );
        attributes.put( "y2", 256.0 );
        attributes.put( "originalWidth", 1024.0 );
        attributes.put( "originalHeight", 1024.0 );
        Avatar avatar = new Avatar( attributes );
    }

    @Test
    public void avatarMutatorTest() {
        Integer newID = (Integer) Manager.find(
                "SELECT Auto_increment FROM information_schema.tables WHERE table_schema = 'peerpendb' AND table_name='avatars'" )
                .get( "autoIncrement" );
        Avatar avatar = new Avatar();
        avatar.setFilename( "AvatarUnitTest.jpg" );
        avatar.setOriginalSize( 512, 512 );
        avatar.setPeerId( 3 );
        avatar.setX1( 0.0 );
        avatar.setX2( 256.0 );
        avatar.setY1( 0.0 );
        avatar.setY2( 256.0 );
        avatar.save();
        Integer highestID = (Integer) Manager.find( "SELECT max(id) as id FROM avatars" ).get( "id" );
        Avatar avatarTest = new Avatar().find( highestID );
        Peer peer = new Peer().find( 3 );
        peer.setAvatarId( avatar.getId() );
        Assert.assertNotNull( avatarTest );
        Assert.assertEquals( highestID, newID );
        Assert.assertEquals( avatarTest.getId(), peer.getAvatarId() );
        Assert.assertEquals( avatarTest.getFilename(), avatar.getFilename() );
        Assert.assertEquals( avatarTest.getOriginalHeight(), avatar.getOriginalHeight() );
        Assert.assertEquals( avatarTest.getOriginalWidth(), avatar.getOriginalWidth() );
        Assert.assertEquals( avatarTest.getX1(), avatar.getX1() );
        Assert.assertEquals( avatarTest.getX2(), avatar.getX2() );
        Assert.assertEquals( avatarTest.getY1(), avatar.getY1() );
        Assert.assertEquals( avatarTest.getY2(), avatar.getY2() );
        Assert.assertEquals( avatarTest.getPeerId(), avatar.getPeerId() );

        Assert.assertTrue( avatar.destroy() );
        avatarTest = new Avatar().find( highestID );
        Assert.assertNull( avatarTest );

        //Test Default Avatar
        Avatar defaultAvatar = new Avatar().getDefaultAvatar();
        Assert.assertEquals( defaultAvatar.getFilename(), "default-avatar.jpg" );
        Assert.assertEquals( defaultAvatar.getX1(), 0.0 );
        Assert.assertEquals( defaultAvatar.getY1(), 0.0 );
        Assert.assertEquals( defaultAvatar.getX2(), 256.0 );
        Assert.assertEquals( defaultAvatar.getY2(), 256.0 );
    }

    @Test
    public void avatarCropTest() throws Exception {
        InternalHttpServletRequest request = mock( InternalHttpServletRequest.class );
        HttpServletResponse response = mock( HttpServletResponse.class );
        ServletContext context = mock( ServletContext.class );
        HttpSession session = mock( HttpSession.class );
        Peer sessionUser = new Peer().find( 3 );
        sessionUser.setAvatarId( 7 );
        sessionUser.update();
        Avatar avatarTest = sessionUser.getAvatar();
        avatarTest.setOriginalHeight( 1024.0 );
        avatarTest.setOriginalWidth( 1024.0 );
        avatarTest.update();
        Map<String, Object> populate = new HashMap<>();
        populate.put( "x1", "0" );
        populate.put( "x2", "40" );
        populate.put( "y1", "0" );
        populate.put( "y2", "40" );
        when( (request).getParametersMap() ).thenReturn( populate );
        when( request.getSession() ).thenReturn( session );
        when( request.getSession().getServletContext() ).thenReturn( context );
        request.setAttribute( "sessionUser", sessionUser );
        new AvatarController().doPost( request, response );

        Peer peer = new Peer().find( 3 );
        avatarTest = peer.getAvatar();
        verify( request, atLeast( 1 ) ).getParametersMap(); // only if you want to verify populate was called...
        Assert.assertEquals( avatarTest.getX1(), 0.0 );
        Assert.assertEquals( avatarTest.getX2(), 40.0 );
        Assert.assertEquals( avatarTest.getY1(), 0.0 );
        Assert.assertEquals( avatarTest.getY2(), 40.0 );
        //For AvatarUpload
        //        String fileNameTest = peer.getId()+"-"+peer.getFirstName().toLowerCase();
        //        Assert.assertTrue(avatarTest.getFilename().startsWith(fileNameTest));
    }


}
