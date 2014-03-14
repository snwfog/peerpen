package com.peerpen.model;

import com.sunnyd.database.Manager;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: waisk
 * Date: 08/03/14
 * Time: 10:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class GroupTest {

    @Test
    public void TestSaveGroup() {

        Group group = new Group();
        Assert.assertNull(group.getId());
        Assert.assertNull(group.getAdminId());
        Assert.assertNull(group.getDescription());
        Assert.assertNull(group.getGroupName());
        group.setGroupName("fitness");
        group.setAdminId(3);
        group.setDescription("fitness");

        Broadcast broadcast = new Broadcast();
        broadcast.setMessage("new b");
        List<Broadcast> broadcasts = group.getBroadcasts();
        broadcasts.add(broadcast);
        Assert.assertTrue(broadcast.save());

        Peer peer = new Peer().find(7);
        group.addPeer(peer);
        Assert.assertTrue(group.save());
        Integer currentHighestID = (Integer) Manager.find("SELECT max(id) as id FROM groups").get("id");
        Assert.assertEquals(group.getId().intValue(), currentHighestID.intValue());

    }


    @Test (dependsOnMethods = { "TestSaveGroup" })
    public static void TestFindGroup(){
        Integer groupId = (Integer) Manager.find("SELECT max(id) as id FROM groups").get("id");
        Group group = new Group().find(groupId);
        Peer p = new Peer().find(7);
        Assert.assertTrue(group.getIsJoined(p.getId()));
        Assert.assertEquals("fitness", group.getGroupName());
        Assert.assertEquals("fitness", group.getDescription());
        Assert.assertEquals("fitness", group.getShortDescription());
        Assert.assertEquals(3, group.getAdminId().intValue());

        Integer a = 1;
        //List<Group> groups = group.getMatchedGroups("fitness");
        Assert.assertEquals(groupId.intValue(), group.getId().intValue());
        Assert.assertEquals(a.intValue(), group.getBroadcasts().size());
        Assert.assertEquals(a.intValue(),group.getPeers().size());
        //Assert.assertEquals(a.intValue(),groups.size());
        Assert.assertEquals(a.intValue(), group.getOrderedBroadcast().size());

    }

    @Test(dependsOnMethods = { "TestFindGroup" })
    public void TestUpdateGroup() {
        Integer gId = (Integer) Manager.find("SELECT max(id) as id FROM groups").get("id");

        Group group = new Group().find(gId);
        group.setGroupName("new fitness");

        Peer peer = new Peer().find(8);
        group.addPeer(peer);
        Assert.assertTrue(group.update());

    }

    @AfterClass
    public void TestDestroyGroup() {
        Integer groupId = (Integer) Manager.find("SELECT max(id) as id FROM groups").get("id");
        Group group = new Group().find(groupId);
        System.out.println("group name:" +group.getGroupName());
        List<Peer> peers = group.getPeers();
        System.out.println("peer size"+peers.size());
        for(Peer p: peers){
            group.removePeer(p);
            group.update();
        }
        List<Broadcast> broadcasts = group.getBroadcasts();
        for(Broadcast b:broadcasts){
            b.destroy();
        }

        Assert.assertTrue(group.destroy());
        Assert.assertNull(group.getId());
    }

    @Test(dependsOnMethods = { "TestUpdateGroup" })
    public void testGetSortedGroups() throws Exception {
        Peer p = new Peer().find(7);
        List<Group> groupList1 = new Group().getSortedGroups("az", p.getId());
        Assert.assertEquals(18,groupList1.size());
        List<Group> groupList2 = new Group().getSortedGroups("za", p.getId());
        Assert.assertEquals(18,groupList2.size());
        List<Group> groupList3 = new Group().getSortedGroups("fc", p.getId());
        Assert.assertEquals(18,groupList3.size());
        List<Group> groupList4 = new Group().getSortedGroups("lc", p.getId());
        Assert.assertEquals(18,groupList4.size());
        List<Group> groupList5 = new Group().getSortedGroups("mp", p.getId());
        Assert.assertEquals(18,groupList5.size());
        List<Group> groupList6 = new Group().getSortedGroups("lp", p.getId());
        Assert.assertEquals(18,groupList6.size());
        List<Group> groupList7 = new Group().getSortedGroups("pd", p.getId());
        Assert.assertEquals(0,groupList7.size());
        List<Group> groupList8 = new Group().getSortedGroups("" , p.getId());
        Assert.assertEquals(18,groupList8.size());

    }

    @Test(dependsOnMethods = { "TestFindGroup" })
    public void testEquals() throws Exception {
        Group g = new Group();
        Assert.assertFalse(g.equals(null));
        Assert.assertTrue(g.equals(g));
        Peer peer= new Peer();
        Assert.assertFalse(g.equals(peer));
    }

}
