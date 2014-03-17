package com.peerpen.model;

import com.sunnyd.database.Manager;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: MP
 * Date: 08/03/14
 * Time: 10:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class JoingroupTest {

    Integer joinGroupId;



    @Test
    public void relationTest() {
        Integer nextId = (Integer) Manager.find(
                "SELECT Auto_increment FROM information_schema.tables WHERE table_schema = 'peerpendb' AND table_name='joingroups'" )
                .get( "autoIncrement" );
        Group g = new Group().find( 3 );
        Peer p = new Peer().find( 2 );
        Joingroup a = new Joingroup();

        a.setGroup( g );
        a.setPeer( p );

        a.save();
        Assert.assertEquals( a.getGroup(), g );
        Assert.assertEquals( a.getPeer(), p );
        Assert.assertEquals( a.getId(), nextId );

        joinGroupId = a.getId();

    }


    @Test(dependsOnMethods = { "relationTest" })
    public void constructorTest() {
        Map<String, Object> populate = new HashMap<>();

        populate.put( "id", joinGroupId );
        Joingroup a = new Joingroup( populate );
        a.setPeerId( 2 );
        a.setGroupId( 3 );
        Group g = a.getGroup();
        Peer p = a.getPeer();

        Assert.assertEquals( p.getId().intValue(), 2 );
        Assert.assertEquals( g.getId().intValue(), 3 );

        Assert.assertTrue( a.destroy() );

    }
}
