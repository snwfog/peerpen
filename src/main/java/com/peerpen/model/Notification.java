package com.peerpen.model;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zearf on 2/15/2014.
 */
public class Notification {

    //CONSIDER making this static method
    public List<Feedable> getNotification(Peer peer){
        String pid = peer.getId().toString();
        String query = "SELECT * FROM `feedables` WHERE `user_id` = " + pid +" AND `notify_status` = 'UNSEND'";
        return new Feedable().queryAll(query);
    }

    public void updateNotification(Peer peer){
        String pid = peer.getId().toString();

        Map<String, Object> m = new HashMap<>();
        m.put("userId", pid);
        List<Feedable> feedables = new Feedable().findAll(m);

        for(Feedable f : feedables){
            f.setNotifyStatus("SENT");
            f.update();
        }

//        new Feedable().queryAll(query);
    }

//    public static void main(String[] args){
//        Notification n = new Notification();
//        Peer p = new Peer().find(2);
////        String json = new Gson().toJson(n.getNotification(p));
//        n.updateNotification(p);
////        System.out.println(json);
//    }
}
