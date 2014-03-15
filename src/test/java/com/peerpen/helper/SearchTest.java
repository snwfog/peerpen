package com.peerpen.helper;

import com.peerpen.model.Document;
import com.peerpen.model.Group;
import com.peerpen.model.Hunk;
import com.peerpen.model.TagDescriptor;
import com.peerpen.model.Peer;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 2014-03-09
 * Time: 11:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchTest {

    private Peer p;
    private Document d;
    private Hunk h;
    private Group g;
    private TagDescriptor td1;
    private TagDescriptor td2;
    private List<TagDescriptor> list;

    @BeforeMethod
    public void setUp() throws Exception
    {
        td1 = new TagDescriptor(  ).getTagDescriptor( "testtag1" );
        td2 = new TagDescriptor(  ).getTagDescriptor( "testtag2" );
        list = new ArrayList<>(  );
        list.add( td1 );
        list.add( td2 );

        p = new Peer(  );
        p.setFirstName( "harry" );
        p.setLastName( "zhang" );
        p.setUserName( "hyz" );
        p.setCompleteProfile( 0 );
        p.save();

        d = new Document( );
        d.setDocName( "harry" );
        d.setPeerId( p.getId() );
        d.setThumbnailPath( "none" );
        d.setType( "resume" );
        d.save();
        d.updateTags( list );

        h = new Hunk( );
        h.setHunkName( "test" );
        h.setContent( "zhang hai yue" );
        h.setDocument( d );
        h.save();


        g = new Group(  );
        g.setGroupName( "harry" );
        g.save();
        g.updateTags( list );

    }

    @AfterMethod
    public void tearDown() throws Exception
    {
        list = new ArrayList<>(  );
        d.updateTags( list );
        g.updateTags( list );

        d.destroy();
        h.destroy();
        g.destroy();
        p.destroy();

        td1.destroy();
        td2.destroy();
    }

    @Test
    public void testGetMatchedPeers() throws Exception {
        Assert.assertTrue( Search.getMatchedPeers( p.getFirstName() ).size() > 0);
        Assert.assertTrue( Search.getMatchedPeers( p.getFirstName() ) instanceof List<?> );
        Assert.assertTrue( Search.getMatchedPeers( p.getUserName() ).contains( p ) );
    }

    @Test
    public void testGetMatchedDocuments() throws Exception {
        Assert.assertTrue( Search.getMatchedDocuments( d.getDocName() ).size() > 0 );
        Assert.assertTrue( Search.getMatchedDocuments( d.getDocName() ) instanceof List<?> );
        Assert.assertTrue( Search.getMatchedDocuments( d.getDocName() ).contains( d ) );
    }

    @Test
    public void testGetMatchedGroups() throws Exception {
        Assert.assertTrue( Search.getMatchedGroups( g.getGroupName() ).size() > 0 );
        Assert.assertTrue( Search.getMatchedGroups( g.getGroupName() ) instanceof List<?> );
        Assert.assertTrue( Search.getMatchedGroups( g.getGroupName() ).contains( g ) );
    }

    @Test
    public void testGetMatchedDocumentContent() throws Exception {
        Assert.assertTrue( Search.getMatchedDocumentsContents( h.getContent() ).size() > 0 );
        Assert.assertTrue( Search.getMatchedDocumentsContents( h.getContent() ) instanceof List<?> );
        Assert.assertTrue( Search.getMatchedDocumentsContents( h.getContent() ).contains( d ) );
    }

    @Test
    public void testGetMatchedDocumentsAdv() throws Exception {
        Assert.assertTrue( Search.getMatchedDocuments( list ).size() > 0 );
        Assert.assertTrue( Search.getMatchedDocuments( list ) instanceof List<?> );
        Assert.assertTrue( Search.getMatchedDocuments( list ).contains( d ) );
    }

    @Test
    public void testGetMatchedGroupsAdv() throws Exception {
        Assert.assertTrue( Search.getMatchedGroups( list ).size() > 0 );
        Assert.assertTrue( Search.getMatchedGroups( list ) instanceof List<?> );
        Assert.assertTrue( Search.getMatchedGroups( list ).contains( g ) );
    }
}
