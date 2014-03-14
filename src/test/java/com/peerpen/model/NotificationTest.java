package com.peerpen.model;

import com.sunnyd.database.Manager;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: MP
 * Date: 08/03/14
 * Time: 10:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationTest {
    @Test
    public void relationTest(){
        Feedable f1 = new Feedable().find(1);
        Feedable f11 = new Feedable().find(11);

        f1.setNotifyStatus("UNSEND");
        f11.setNotifyStatus("UNSEND");
        Assert.assertTrue(f1.updateFeedable());
        Assert.assertTrue(f11.updateFeedable());

        Notification nf = new Notification();
        Peer p = new Peer().find(2);

        List<Feedable> fs = nf.getNotification(p);
        Assert.assertEquals(fs.size(), 2);

        nf.updateNotification(p);

        fs = nf.getNotification(p);
        Assert.assertEquals(fs.size(), 0);

        f1 = new Feedable().find(1);
        f11 = new Feedable().find(11);

        f1.setNotifyStatus("UNSEND");
        f11.setNotifyStatus("UNSEND");
        Assert.assertTrue(f1.updateFeedable());
        Assert.assertTrue(f11.updateFeedable());

    }
}
