package com.peerpen.model;
import com.sunnyd.database.Manager;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class FeedableTest {

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
        Assert.assertTrue(c.destroy());

        f = new Feedable().find(currentHighestID);
        Assert.assertNull(f);
    }

    @Test
    public void broadcastCreateFeedableTest()
    {

    }

    @Test
    public void changesetCreateFeedableTest()
    {

    }

    @Test
    public void findFeedableTest(){

    }

}
