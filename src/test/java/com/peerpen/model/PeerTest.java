package com.peerpen.model;

import com.sunnyd.database.Manager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: waisk
 * Date: 08/03/14
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class PeerTest {

    @Test
    public void TestSave() {

        Peer a = new Peer();
        Assert.assertNull(a.getId());
        Assert.assertNull(a.getCreationDate());
        Assert.assertNull(a.getLastModifiedDate());
        Assert.assertNull(a.getFirstName());
        Assert.assertNull(a.getLastName());
        Assert.assertFalse(a.getUpdateFlag());
        a.setFirstName("a");
        a.setLastName("b");
        a.setCompleteProfile(0);


        Document d = new Document();
        d.setDocName("aiodjoadjoia");
        List<Document> docArray = a.getDocuments();
        docArray.add(d);
        a.setDocuments(docArray);
        Assert.assertTrue(d.save());

//        Changeset ch = new Changeset();
//        ch.setContent("changeset");
//        ch.setHunkId(3);
//        List<Changeset> changesets = a.getChangesets();
//        changesets.add(ch);
//        a.setChangesets(changesets);
//        Assert.assertTrue(ch.save());

        Group group = new Group();
        group.setGroupName("new group");
        Assert.assertTrue(group.save());
        System.out.println("id: "+group.getId());
        List<Group> groups = a.getGroups();
        groups.add(group);
        a.setGroups(groups);


        Assert.assertTrue(a.save());
        Integer currentHighestID = (Integer) Manager.find("SELECT max(id) as id FROM peers").get("id");
        Assert.assertEquals(a.getId().intValue(), currentHighestID.intValue());

    }


    @Test (dependsOnMethods = { "TestSave" })
    public static void TestFind(){
        Integer peerId = (Integer) Manager.find("SELECT max(id) as id FROM peers").get("id");
        Peer peer = new Peer().find(peerId);
        Assert.assertEquals("a", peer.getFirstName());
        Assert.assertEquals("b", peer.getLastName());
        Integer a = 1;
        Integer b = 1;
        Integer c = 1;

        Assert.assertEquals(peerId.intValue(), peer.getId().intValue());
        Assert.assertEquals(a.intValue(),peer.getDocuments().size());
//        Assert.assertEquals(b.intValue(),peer.getChangesets().size());
        Assert.assertEquals(c.intValue(),peer.getGroups().size());


    }

    @Test(dependsOnMethods = { "TestFind" })
    public void TestUpdate() {
        Integer peerId = (Integer) Manager.find("SELECT max(id) as id FROM peers").get("id");
        Integer docId = (Integer) Manager.find("SELECT max(id) as id FROM documents").get("id");
        Integer chId = (Integer) Manager.find("SELECT max(id) as id FROM changesets").get("id");
        Integer gId = (Integer) Manager.find("SELECT max(id) as id FROM groups").get("id");

        Peer p = new Peer().find(peerId);
        Assert.assertEquals("a", p.getFirstName());
        Assert.assertEquals("b", p.getLastName());

        p.setFirstName("john");
        p.setLastName("malkovich");

        Document d = new Document().find(docId);
        d.setDocName("new doc");
        List<Document> docArray = p.getDocuments();
        docArray.add(d);
        p.setDocuments(docArray);
        Assert.assertTrue(d.update());

//              Changeset changeset = new Changeset().find(chId);
//        changeset.setContent("new ch");
//        List<Changeset> changesets = p.getChangesets();
//        changesets.add(changeset);
//        p.setChangesets(changesets);
//        Assert.assertTrue(changeset.update());

        Group group = new Group().find(gId);
        group.setGroupName("new group");
        List<Group> groups = p.getGroups();
        groups.add(group);
        p.setGroups(groups);
        Assert.assertTrue(group.update());

        Assert.assertTrue(p.update());

        Assert.assertEquals(peerId.intValue(), p.getId().intValue());

    }


    @Test(dependsOnMethods = { "TestUpdate" })
    public void TestDestroy() {
        Integer peerId = (Integer) Manager.find("SELECT max(id) as id FROM peers").get("id");
        Peer p = new Peer().find(peerId);
        Assert.assertEquals("john", p.getFirstName());
        Assert.assertEquals("malkovich", p.getLastName());
        Assert.assertTrue(p.destroy());
        Assert.assertNull(p.getId());
    }


    @Test(dependsOnMethods = { "TestFind"})
    public void testGetDocuments() throws Exception {
        Integer peerId = (Integer) Manager.find("SELECT max(id) as id FROM peers").get("id");

        Peer p = new Peer().find(peerId);
        Integer length = 1;
        Assert.assertEquals(length.intValue(),p.getDocuments().size());


    }

    @Test
    public void testSetDocuments() throws Exception {

    }

    @Test
    public void testGetChangesets() throws Exception {

    }

    @Test
    public void testSetChangesets() throws Exception {

    }

    @Test
    public void testGetGroups() throws Exception {

    }

    @Test
    public void testSetGroups() throws Exception {

    }

    @Test
    public void testHasAvatar() throws Exception {

    }

    @Test
    public void testGetAvatar() throws Exception {

    }

    @Test
    public void testGetMatchedPeers() throws Exception {

    }

    @Test
    public void testGetSuggestions() throws Exception {

    }

    @Test
    public void testIsValidSession() throws Exception {

    }

    @Test
    public void testIsValidLogin() throws Exception {

    }

    @Test
    public void testEquals() throws Exception {

    }
}
