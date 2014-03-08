package com.peerpen.model;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotSame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 1/28/2014
 * Time: 10:59 PM
 * To change this template use File | Settings | File Templates.
 */

public class TagDescriptorTest {

    @Test
    public void equalsTest(){
        TagDescriptor td1 = new TagDescriptor(  );
        TagDescriptor td2 = new TagDescriptor(  );
        td1.setTagName( "concordia" );
        td2.setTagName( "CONCORDIA" );
        assertEquals( td1.equals( td2), true );

        td1.setTagName( "concordia " );
        //System.out.println(td1.equals( td2 ));
        assertEquals( td1.equals( td2 ), false );
    }

    @Test
    public void createTagDescriptorTest(){
        String tagName = "commerce";
        TagDescriptor td = new TagDescriptor(  );
        td.setTagName( tagName );
        assertEquals(td.getTagName(), "commerce");
    }

    @Test
    public void getTagDescriptorIfExistsTest(){
        TagDescriptor td1 = new TagDescriptor(  );
        assertEquals( td1.getTagDescriptorIfExists( "ccc" ).getTagName(), "ccc" );

        TagDescriptor td2 = new TagDescriptor(  );
        assertEquals( td2.getTagDescriptorIfExists( "nonexistingtag" ), null );
    }

    @Test
    public void getTagDescriptorTest(){
        TagDescriptor td = new TagDescriptor( ).getTagDescriptor( "1234567890" );
        assertEquals( td.getTagName(), "1234567890" );
    }

}
