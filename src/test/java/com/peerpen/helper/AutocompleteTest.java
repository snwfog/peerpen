package com.peerpen.helper;

import com.peerpen.model.Document;
import com.peerpen.model.Group;
import com.peerpen.model.TagDescriptor;
import com.peerpen.model.Peer;
import com.sunnyd.database.Manager;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 2014-03-09
 * Time: 11:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class AutocompleteTest {

    private Peer p;
    private Document d;
    private Group g;
    private TagDescriptor td;

    @BeforeMethod
    public void setUp() throws Exception
    {
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

        g = new Group(  );
        g.setGroupName( "harry" );
        g.save();

        td = new TagDescriptor(  ).getTagDescriptor( "concordia" );
    }

    @AfterMethod
    public void tearDown() throws Exception
    {
        d.destroy();
        g.destroy();
        p.destroy();
        td.destroy();
    }

    @Test
    public void testGetSuggestedDocuments() throws Exception {
        String json = Autocomplete.getSuggestedDocuments( "har", 100 );
        Assert.assertTrue( json.toLowerCase().contains( "harry" ), "Check in result contains keyword" );
    }

    @Test
    public void testGetSuggestedPeers() throws Exception {
        String json = Autocomplete.getSuggestedPeers( "hyz", 100 );
        Assert.assertTrue( json.toLowerCase().contains( "hyz" ), "Check in result contains keyword" );
    }

    @Test
    public void testGetSuggestedGroups() throws Exception {
        String json = Autocomplete.getSuggestedGroups( "rry", 100 );
        Assert.assertTrue( json.toLowerCase().contains( "harry" ), "Check in result contains keyword" );
    }

    @Test
    public void testGetSuggestedTagDescriptors() throws Exception {
        List<String> list = Autocomplete.getSuggestedTagDescriptors( "concor", 100 );
        Assert.assertTrue( list.contains( "concordia" ), "Check in result contains keyword" );
    }
}
