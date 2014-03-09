package com.peerpen.model;
import com.sunnyd.database.Manager;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedableTest {


    @Test
    public void constructorTest(){
        Map<String, Object> populate = new HashMap<>();
        populate.put("type", "acorn");
        populate.put("status", "acorn");
        populate.put("notifyStatus", "acorn");
        populate.put("childId", 1);
        populate.put("userId", 1);
        populate.put("trueSelf", new Feedable());
        Feedable a = new Feedable(populate);
    }

    @Test
    public void commentCreateFeedableTest()
    {
        Integer nextId = (Integer) Manager.find("SELECT Auto_increment FROM information_schema.tables WHERE table_schema = 'peerpendb' AND table_name='feedables'").get("autoIncrement");
        Comment c = new Comment();
        c.setMessage("Unit Testing");
        c.setObjectId(1);
        c.setType("Document");
        c.save();
        c.setPosterPeerId(1);
        Integer currentHighestID = (Integer) Manager.find("SELECT max(id) as id FROM feedables").get("id");
        Assert.assertEquals(currentHighestID, nextId);

        Document d = new Document().find(1);
        Feedable f = new Feedable().find(currentHighestID);
        Assert.assertNotNull(f);
        Assert.assertEquals(f.getType(), "Comment");
        Assert.assertEquals(f.getChildId(), c.getId());
        Assert.assertEquals(f.getStatus(), "new");
        Assert.assertEquals(f.getUserId(), (Integer) d.getPeerId());
        Assert.assertEquals(f.getTrueSelf().getClass().getSimpleName(), "Comment");

        c.setMessage("Unit Testing two");
        c.update();
        f = new Feedable().find(currentHighestID);
        Assert.assertEquals(f.getStatus(), "update");

        Assert.assertTrue(c.destroy());
        f = new Feedable().find(currentHighestID);
        Assert.assertNull(f);
    }

    @Test
    public void broadcastCreateFeedableTest()
    {
        Broadcast b = new Broadcast();
        b.setMessage("Unit Testing");
        b.setPeerId(2);
        b.setGroupId(3);
        b.save();

        Group g = new Group();
        List<Peer> ps = g.getPeers();
        Integer nextId = (Integer) Manager.find("SELECT Auto_increment FROM information_schema.tables WHERE table_schema = 'peerpendb' AND table_name='feedables'").get("autoIncrement");
        Integer expectedId = nextId + g.getPeers().size() -1;
        Integer currentHighestID = (Integer) Manager.find("SELECT max(id) as id FROM feedables").get("id");
        Assert.assertEquals(currentHighestID, expectedId);

        for(Peer p: ps){
            Map<String, Object> conditions = new HashMap<>();
            conditions.put("userId", p.getId());
            conditions.put("childId", b.getId());
            Feedable a = new Feedable().find(conditions);
            Assert.assertNotNull(a);
            Assert.assertEquals(a.getType(), "BroadCast");
            Assert.assertTrue(   a.getId() <= expectedId && nextId <= a.getId());
            Assert.assertEquals(a.getTrueSelf().getClass().getSimpleName(), "BroadCast");
            Assert.assertEquals(((Broadcast)a.getTrueSelf()).getMessage(), "Unit Testing");
            Assert.assertTrue(a.destroy());
        }

        Assert.assertTrue(b.destroy());
    }

    @Test
    public void changesetCreateFeedableTest()
    {

    }

    @Test
    public void getPeerFeedableTest(){
        Comment c = new Comment();
        c.setMessage("Unit Testing");
        c.setObjectId(1);
        c.setType("Document");
        c.save();

        Comment c1 = new Comment();
        c1.setMessage("Unit Testing");
        c1.setObjectId(1);
        c1.setType("Document");
        c1.save();

        Comment c2 = new Comment();
        c2.setMessage("Unit Testing");
        c2.setObjectId(1);
        c2.setType("Document");
        c2.save();

        Comment c3 = new Comment();
        c3.setMessage("Unit Testing");
        c3.setObjectId(1);
        c3.setType("Document");
        c3.save();

          Peer a = new Peer().find(2);
          List<Feedable> fs = Feedable.getFeed(a);
          Assert.assertEquals(fs.size(), 4);

        Assert.assertTrue(c.destroy());
        Assert.assertTrue(c1.destroy());
        Assert.assertTrue(c2.destroy());
        Assert.assertTrue(c3.destroy());

    }

}
