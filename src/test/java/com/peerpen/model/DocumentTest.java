package com.peerpen.model;

import com.google.common.collect.Maps;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class DocumentTest
{
  private Document document;

  @BeforeMethod
  public void setUp() throws Exception
  {
    document = new Document().find(2);
  }

  @AfterMethod
  public void tearDown() throws Exception
  {
    document = null;
  }

  @Test
  public void testGetDocName() throws Exception
  {

  }

  @Test
  public void testGetPeer() throws Exception
  {

  }

  @Test
  public void testSetPeerId() throws Exception
  {

  }

  @Test
  public void testGetPeerId() throws Exception
  {

  }

  @Test
  public void testSetDocName() throws Exception
  {

  }

  @Test
  public void testGetThumbnailPath() throws Exception
  {

  }

  @Test
  public void testSetThumbnailPath() throws Exception
  {

  }

  @Test
  public void testSetLastModifiedDate() throws Exception
  {

  }

  @Test
  public void testGetCreationDate() throws Exception
  {

  }

  @Test
  public void testSetCreationDate() throws Exception
  {

  }

  @Test
  public void testGetHunks() throws Exception
  {
    Assert.assertTrue(document.getHunks() instanceof List<?>, "Check if return object is type List");
    List<?> list = document.getHunks();
    Assert.assertTrue(list.get(1) instanceof Hunk, "Check list if contains object type Hunk");
  }

  @Test
  public void testGetComments() throws Exception
  {
    Assert.assertTrue(document.getComments() instanceof List<?>, "Check if return object is type List");
    List<?> list = document.getComments();
    Assert.assertTrue(list.get(1) instanceof Comment, "Check list if contains object type Hunk");
  }

  @Test
  public void testGetMatchedDocuments() throws Exception
  {
    Assert.assertTrue(document.getMatchedDocuments("resume") instanceof List<?>, "Check if return object is type List");
    List<?> list = document.getMatchedDocuments("resume");
    Assert.assertTrue(list.get(1) instanceof Document, "Check list if contains object type Document");
    Assert.assertTrue(document.getMatchedDocuments("dsfgsadfdsfdsfwef").isEmpty(), "Check list if contains object type Document");
  }

  @Test
  public void testGetSuggestions() throws Exception
  {
    Assert.assertTrue(document.getSuggestions("res", 100) instanceof List<?>, "Check if return object is type List");
    List<Document> list = document.getSuggestions("res", 100);
    for (Document document1 : list)
    {
      Assert.assertTrue(document1.getDocName().toLowerCase().contains("res"), "Check in result contains keyword");
    }
    Assert.assertTrue(list.get(1) instanceof Document, "Check list if contains object type Document");
    Assert.assertTrue(document.getSuggestions("dsfgsadfdsfdsfwef", 3).isEmpty(), "Check list if contains object type Document");
  }

  @Test
  public void testCreateComment() throws Exception
  {
    Random dice = new Random();
    Peer peer = new Peer().find(2);
    String d = Double.toString(dice.nextGaussian());
    Comment comment = new Comment();
    comment.setName("comment:" + d);
    comment.setMessage(d);

    document.createComment(d, peer);
    document.update();

    Map<String, Object> hm = Maps.newHashMap();
    hm.put("message", d);
    Comment comment2 = new Comment().find(hm);
    Assert.assertTrue(comment2.getMessage().contentEquals(d), "Create comment");


  }

  @Test(dependsOnMethods = {"testCreateComment"})
  public void testFindComments() throws Exception
  {
    Assert.assertTrue(document.findComments() instanceof List<?>, "Check if return object is type List");
    List<?> list = document.findComments();

    for (int i = 0; i < list.size(); i++)
    {
      Assert.assertTrue(list.get(i) instanceof Comment, "Check list if contains object type Comment");
    }
  }

  @Test
  public void testGetChangeset() throws Exception
  {
    Assert.assertTrue(document.getChangeset() instanceof List<?>, "Check if return object is type List");
    List<?> list = document.getChangeset();

    for (int i = 0; i < list.size(); i++)
    {
      Assert.assertTrue(list.get(i) instanceof Changeset, "Check list if contains object type Changest");
    }
  }
}
