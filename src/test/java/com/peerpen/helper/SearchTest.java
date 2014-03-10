package com.peerpen.helper;

import com.sunnyd.database.Manager;
import com.peerpen.model.Peer;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 2014-03-09
 * Time: 11:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchTest {

    @Test
    public void testGetMatchedPeers() throws Exception {
        Integer peerId = (Integer) Manager.find( "SELECT max(id) as id FROM peers" ).get("id");
        Peer p = new Peer().find(peerId);
        Assert.assertTrue( Search.getMatchedPeers( p.getFirstName() ).size() > 0);
        Assert.assertTrue( Search.getMatchedPeers( p.getFirstName() ) instanceof List<?> );
    }

    @Test
    public void testGetMatchedDocuments() throws Exception {

    }

    @Test
    public void testGetMatchedGroups() throws Exception {

    }

    @Test
    public void testGetMatchedDocumentsAdv() throws Exception {

    }

    @Test
    public void testGetMatchedGroupsAdv() throws Exception {

    }
}
