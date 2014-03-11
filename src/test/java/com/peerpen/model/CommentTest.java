package com.peerpen.model;

import com.google.common.collect.Maps;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class CommentTest
{
  private Comment comment;
  private String randomString;

  @BeforeMethod
  public void setUp() throws Exception
  {
    comment = new Comment().find(4);

    Random dice = new Random();
    randomString = Double.toString(dice.nextGaussian());
  }

  @AfterMethod
  public void tearDown() throws Exception
  {
    comment = null;
    randomString = null;
  }

  @Test
  public void Comment() throws Exception
  {
    Peer peer = new Peer().find(4);
    Map<String, Object> hm = Maps.newHashMap();
    hm.put("message", "TestComment:" + randomString);
    hm.put("objectId", 3);
    hm.put("type", "Document");
    hm.put("posterPeerId", peer.getId());
    hm.put("posterPeer", peer);
    Comment newComment = new Comment(hm);
    newComment.save();

    Comment comment1 = new Comment().find(hm);
    Assert.assertEquals(comment1.getMessage(), "TestComment:" + randomString);
    Assert.assertTrue(comment1.getObjectId() == 3);
    Assert.assertEquals(comment1.getType(), "Document");
    Assert.assertEquals(comment1.getPosterPeerId(), peer.getId());

  }

  @Test
  public void testGetPosterPeerId() throws Exception
  {
    Comment c = new Comment();
    c.setMessage("TestComment:" + randomString);
    c.setType("Resume");
    c.setObjectId(3);
    c.setPosterPeerId(2);
    c.setPosterPeer((Peer) new Peer().find(2));
    c.save();

    Map<String, Object> hm = Maps.newHashMap();
    hm.put("message", "TestComment:" + randomString);

    Comment comment2 = new Comment().find(hm);
    Assert.assertTrue(comment2.getPosterPeerId() == 2, "Check the peer who posted the comment");
  }
  @Test
  public void testSave() throws Exception
  {
    Comment c = new Comment();
    c.setMessage("TestComment:" + randomString);
    c.setType("Resume");
    c.setObjectId(3);
    c.setPosterPeerId(2);
    c.setPosterPeer((Peer) new Peer().find(2));
    c.save();

    Map<String, Object> hm = Maps.newHashMap();
    hm.put("message", "TestComment:" + randomString);

    Comment comment2 = new Comment().find(hm);
    Assert.assertTrue(comment2.getPosterPeerId() == 2, "Check the peer id who posted the comment");
    Assert.assertTrue(comment2.getObjectId() == 3);
    Assert.assertEquals(comment2.getType(), "Resume");
    Assert.assertEquals(comment2.getMessage(), "TestComment:" + randomString);
  }

  @Test
  public void testCreateComment() throws Exception
  {
    Comment.createComment((Document) new Document().find(4), "TestComment:" + randomString, (Peer) new Peer().find(2));

    Map<String, Object> hm = Maps.newHashMap();
    hm.put("message", "TestComment:" + randomString);
    Comment comment1 = new Comment().find(hm);
    Assert.assertEquals(new Document().find(4), comment1.getPosterPeer());
    Assert.assertEquals("TestComment:" + randomString, comment1.getMessage());
  }

  @Test
  public void testDeleteComment() throws Exception
  {
    Comment c = new Comment();
    c.setMessage("TestComment:" + randomString);
    c.setType("Resume");
    c.setObjectId(3);
    c.setPosterPeerId(2);
    c.setPosterPeer((Peer) new Peer().find(2));
    c.save();

    Map<String, Object> hm = Maps.newHashMap();
    hm.put("message", "TestComment:" + randomString);

    Comment comment2 = new Comment().find(hm);

    Comment.deleteComment(comment2.getId());
    Assert.assertNull(new Comment().find(hm));
  }

  @Test
  public void testFindComments() throws Exception
  {
    Document document = new Document().find(4);
    Assert.assertTrue(Comment.findComments(document, document.getId()) instanceof List<?>);
    List<Comment> comment1 = Comment.findComments(document, document.getId());

    for (int i = 0; i < comment1.size(); i++)
    {
      Assert.assertTrue(comment1.get(i) instanceof Comment);
    }
  }
}
