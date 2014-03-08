package com.peerpen.model;
import com.sunnyd.database.Manager;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class FeedableTest {

    @Test
    public void commentCreateFeedableTest()
    {
        Integer oldHighestID = (Integer) Manager.find("SELECT max(id) as id FROM feedables").get("id");
        Comment c = new Comment();
        c.setMessage("Unit Testing");
        c.setObjectId(1);
        c.setType("Document");
        c.save();
        c.setPosterPeerId(1);
        Integer currentHighestID = (Integer) Manager.find("SELECT max(id) as id FROM feedables").get("id");
        Integer ExpectedId = oldHighestID+1;
        Assert.assertEquals(currentHighestID, ExpectedId);

        Feedable f = new Feedable().find(currentHighestID);
        Assert.assertNotNull(f);
        Assert.assertEquals(f.getType(), "Comment");
        Assert.assertEquals(f.getChildId(), c.getId());
        Assert.assertTrue(c.destroy());
        Assert.assertTrue(f.destroy());
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
