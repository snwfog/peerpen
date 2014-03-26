package com.peerpen.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zearf on 2/15/2014.
 */
public class Notification {

    public static List<Feedable> getNotification( Peer peer ) {
        String pid = peer.getId().toString();
        String query = "SELECT * FROM `feedables` WHERE `user_id` = " + pid + " AND `notify_status` = 'UNSEND'";
        return new Feedable().queryAll( query );
    }
    public void updateNotification( Peer peer ) {
        String pid = peer.getId().toString();

        Map<String, Object> m = new HashMap<>();
        m.put( "userId", pid );
        List<Feedable> feedables = new Feedable().findAll( m );

        for ( Feedable f : feedables ) {
            f.setNotifyStatus( "SENT" );
            f.update();
        }
    }
}
