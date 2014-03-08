package com.peerpen.model;
import com.sunnyd.database.Manager;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class FeedableTest {

    @Test
    public void commentCreateFeedableTest()
    {
        Integer currentHighestID = (Integer) Manager.find("SELECT max(id) as id FROM feedables").get("id");
        Comment c = new Comment("")
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
