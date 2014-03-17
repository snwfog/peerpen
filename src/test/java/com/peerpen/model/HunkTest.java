package com.peerpen.model;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 2014-03-14
 * Time: 1:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class HunkTest {

    private Peer p;
    private Hunk h;
    private Document d;

    @BeforeMethod
    public void setUp() throws Exception {
        p = new Peer();
        p.setFirstName( "testhunk" );
        p.setLastName( "testhunk" );
        p.setUserName( "testhunk" );
        p.setCompleteProfile( 0 );
        p.setPoint( 0 );
        p.setEmail( "testhunk" );
        p.setPassword( "testhunk" );
        p.setExperience( 3 );
        p.save();

        d = new Document();
        d.setDocName( "testhunk" );
        d.setPeerId( p.getId() );
        d.setType( "resume" );
        d.setThumbnailPath( "asd" );
        d.save();

        h = new Hunk();
        h.setContent( "testhunk" );
        h.setDocumentId( d.getId() );
        h.setHunkName( "testhunk" );
        h.setIdView( "testhunk" );
        h.save();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        h.destroy();
        d.destroy();
        p.destroy();
    }

    @Test
    public void testGetDocument() throws Exception {
        Document actual = h.getDocument();
        Assert.assertEquals( actual, d );
    }
}
