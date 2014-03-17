package com.peerpen.model;

import static org.testng.AssertJUnit.assertEquals;

import com.sunnyd.database.Manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 2014-03-08
 * Time: 7:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class TaggableTest {

    private TagDescriptor td1;
    private TagDescriptor td2;
    private Group g;

    @BeforeMethod
    public void setUp() throws Exception {
        td1 = new TagDescriptor().getTagDescriptor( "test1" );
        td2 = new TagDescriptor().getTagDescriptor( "test2" );

        g = new Group();
        g.setGroupName( "test_group" );
        g.save();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        g.destroy();
        td1.destroy();
        td2.destroy();
    }

    @Test
    public void constructorTest() {
        Map<String, Object> populate = new HashMap<>();
        populate.put( "childId", 1 );
        populate.put( "type", "Group" );
        Taggable t = new Taggable( populate );
    }

    @Test
    public void testGetTagDescriptors() throws Exception {
        List<TagDescriptor> expected = new ArrayList<>();
        expected.add( td1 );
        expected.add( td2 );
        g.updateTags( expected );
        List<TagDescriptor> actual = g.getTagDescriptors();
        assertEquals( expected, actual );
    }

    @Test
    public void testUpdateTags() throws Exception {
        List<TagDescriptor> expected = new ArrayList<>();
        expected.add( td1 );
        expected.add( td2 );
        g.updateTags( expected );
        List<TagDescriptor> actual = g.getTagDescriptors();
        System.out.println( "expected:" );
        for ( TagDescriptor t : expected ) {
            System.out.println( t.getTagName() );
        }
        System.out.println( "actual:" );
        for ( TagDescriptor t : actual ) {
            System.out.println( t.getTagName() );
        }
        assertEquals( expected, actual );
    }

    @Test
    public void testGetTaggableId() throws Exception {
        Integer actual = g.getTaggableId();
        Integer expected = (Integer) Manager.find( "SELECT max(id) as id FROM taggables" ).get( "id" );
        assertEquals( expected, actual );
    }
}
