package com.peerpen.model;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
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
    public void setUp() throws Exception
    {
        td1 = new TagDescriptor(  ).getTagDescriptor( "concordia" );
        td2 = new TagDescriptor(  ).getTagDescriptor( "CONCORDIA" );
    }

    @AfterMethod
    public void tearDown() throws Exception
    {
        td1.destroy();
        td2.destroy();
    }

    @Test
    public void equalsTest(){
        assertEquals( td1.equals( td2), true );
    }

    @Test
    public void createTagDescriptorTest(){
        assertEquals(td1.getTagName(), "concordia");
    }

    @Test
    public void getTagDescriptorIfExistsTest(){
        assertEquals( td1.getTagDescriptorIfExists( td1.getTagName() ).getTagName(), td1.getTagName() );

        TagDescriptor td2 = new TagDescriptor(  );
        assertEquals( td2.getTagDescriptorIfExists( "nonexistingtag" ), null );
    }

    @Test
    public void getTagDescriptorTest(){
        assertEquals( td2.getTagName(), "concordia" );
    }

}
