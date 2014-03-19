package com.peerpen.model;

import static org.testng.AssertJUnit.assertEquals;
import com.sunnyd.database.Manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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

    private TagDescriptor td1;
    private TagDescriptor td2;

    @BeforeMethod
    public void setUp() throws Exception {
        td1 = new TagDescriptor().getTagDescriptor( "concordia" );
        td2 = new TagDescriptor().getTagDescriptor( "CONCORDIA" );
    }

    @AfterMethod
    public void tearDown() throws Exception {
        td1.destroy();
        td2.destroy();
    }

    @Test
    public void constructorTest() {
        Map<String, Object> populate = new HashMap<>();
        populate.put( "tagName", "testtag" );
        TagDescriptor t = new TagDescriptor( populate );
    }

    @Test
    public void createTagDescriptorTest() {
        assertEquals( td1.getTagName(), "concordia" );
    }

    @Test
    public void getTagDescriptorIfExistsTest() {
        assertEquals( td1.getTagDescriptorIfExists( td1.getTagName() ).getTagName(), td1.getTagName() );
        TagDescriptor td2 = new TagDescriptor();
        assertEquals( td2.getTagDescriptorIfExists( "nonexistingtag" ), null );
    }

    @Test
    public void getTagDescriptorTest() {
        assertEquals( td2.getTagName(), "concordia" );
    }

    @Test
    public void getTagCloudTest() {
        List<TagDescriptor> actual = TagDescriptor.getTagCloud();
        List<TagDescriptor> expected = new TagDescriptor().queryAll( "SELECT * FROM tag_descriptors" );
        assertEquals( actual.size(), expected.size() );
    }

    @Test
    public void equalsTest() {
        Integer id = (Integer) Manager.find( "SELECT max(id) as id FROM tag_descriptors" ).get( "id" );
        TagDescriptor td = new TagDescriptor().find( id );
        assertEquals( td.getId(), id );
        assertEquals( td1.getId(), id );
        assertEquals( td2.getId(), id );
    }

}
