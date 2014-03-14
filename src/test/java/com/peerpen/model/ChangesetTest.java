package com.peerpen.model;

import com.google.common.collect.Maps;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class ChangesetTest
{
  Changeset changeset;
  private String randomString;

  @BeforeMethod
  public void setUp() throws Exception
  {
    Random dice = new Random();
    randomString = Double.toString(dice.nextGaussian());

    changeset = new Changeset().find(6);
    changeset.createComment("TestChangesetSetUp:" + randomString, (Peer) new Peer().find(2));
    changeset.update();
  }

  @AfterMethod
  public void tearDown() throws Exception
  {
    changeset = null;

    Map<String, Object> hm = Maps.newHashMap();
    hm.put("message", "TestChangesetSetUp:" + randomString);
    Comment comment2;

    if((comment2 = new Comment().find(hm))!=null)
    {
       comment2.destroy();
    }
  }

  @Test
  public void testGetOrderedComments() throws Exception
  {
    Assert.assertTrue(changeset.getOrderedComments() instanceof List<?>, "Check if return is type List");
    List<?> list = changeset.getOrderedComments();

    for (int i = 0; i < list.size(); i++)
    {
      Assert.assertTrue(list.get(i) instanceof Comment);
    }
  }

  @Test
  public void testCreateComment() throws Exception
  {
    changeset.createComment("TestChangesetCreateComment:" + randomString, (Peer) new Peer().find(3));

    Map<String, Object> hm = Maps.newHashMap();
    hm.put("message", "TestChangesetCreateComment:" + randomString);
    Comment comment2 = new Comment().find(hm);
    Assert.assertTrue(comment2.getMessage().contentEquals("TestChangesetCreateComment:" + randomString), "Create comment");
    comment2.destroy();
  }

  @Test
  public void testFindComments() throws Exception
  {
    Assert.assertTrue(changeset.findComments() instanceof List<?>, "Check if return object is type List");
    List<?> list = changeset.findComments();

    for (int i = 0; i < list.size(); i++)
    {
      Assert.assertTrue(list.get(i) instanceof Comment, "Check list if contains object type Comment");
    }
  }

  @Test
  public void testSave() throws Exception
  {
    Changeset changeset1 = new Changeset();
    changeset1.setPeerId(4);
//    changeset1.setType("Dunno");
    changeset1.setContent("ChangesetContent:" + randomString);
    changeset1.setHunkId(3);
    Assert.assertTrue(changeset1.save(), "Test save() method in Changeset");

    Map<String, Object> hm = Maps.newHashMap();
    hm.put("content", "ChangesetContent:" + randomString);
    Changeset changeset2 = new Changeset().find(hm);

    Assert.assertTrue(changeset2.getPeerId() == 4);
    Assert.assertEquals(changeset2.getContent(), "ChangesetContent:" + randomString);
    Assert.assertTrue(changeset2.getHunkId() == 3);
  }

  @Test
  public void testUpdate() throws Exception
  {
    Changeset changeset1 = new Changeset();
    changeset1.setPeerId(4);
//    changeset1.setType("Dunno");
    changeset1.setContent("ChangesetContent:" + randomString);
    changeset1.setHunkId(3);
    changeset1.setHunk((Hunk) new Hunk().find(3));
    Assert.assertTrue(changeset1.save(), "Test save() method in Changeset");

    Map<String, Object> hm = Maps.newHashMap();
    hm.put("content", "ChangesetContent:" + randomString);
    Changeset changeset2 = new Changeset().find(hm);
//    changeset2.setPeerId(3);
//    changeset2.setType("Dunno");
    changeset2.setContent("ChangesetContent2:" + randomString);
    changeset2.setHunkId(2);
    changeset2.setHunk((Hunk) new Hunk().find(2));

    changeset2.update();

    hm = null;
    hm = Maps.newHashMap();
    hm.put("content", "ChangesetContent2:" + randomString);
    Changeset changeset3 = new Changeset().find(hm);

//    Assert.assertTrue(changeset3.getPeerId() == 3);
    Assert.assertEquals(changeset3.getContent(), "ChangesetContent2:" + randomString);
    Assert.assertTrue(changeset3.getHunkId() == 2);
  }

  @Test
  public void testDestroy() throws Exception
  {
    Changeset changeset1 = new Changeset();
    changeset1.setPeerId(4);
//    changeset1.setType("Dunno");
    changeset1.setContent("ChangesetContent:" + randomString);
    changeset1.setHunkId(3);
    Assert.assertTrue(changeset1.save(), "Test save() method in Changeset");

    Map<String, Object> hm = Maps.newHashMap();
    hm.put("content", "ChangesetContent:" + randomString);
    Changeset changeset2 = new Changeset().find(hm);

    Assert.assertTrue(changeset2.destroy());
    Assert.assertNull(new Changeset().find(hm), "Test destroy() method in Changeset");
  }
}
