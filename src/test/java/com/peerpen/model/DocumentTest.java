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
  private String randomString;

  @BeforeMethod
  public void setUp() throws Exception
  {
    document = new Document().find(2);

    Random dice = new Random();
    randomString = Double.toString(dice.nextGaussian());
  }

  @AfterMethod
  public void tearDown() throws Exception
  {
    document = null;
    randomString = null;
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
    Assert.assertTrue(list.get(1) instanceof Comment, "Check list if contains object type Comment");
  }

  @Test
  public void testCreateComment() throws Exception
  {
    Peer peer = new Peer().find(2);

    document.createComment("Test:" + randomString, peer);
    document.update();

    Map<String, Object> hm = Maps.newHashMap();
    hm.put("message", "Test:" + randomString);
    Comment comment2 = new Comment().find(hm);
    Assert.assertTrue(comment2.getMessage().contentEquals("Test:" + randomString), "Create comment");
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

  @Test
  public void testSave() throws Exception
  {
    Document newDocument = new Document();
    newDocument.setPeerId(2);
    newDocument.setThumbnailPath("none");
    newDocument.setDocName("TestDocument:" + randomString);
//    newDocument.setType("Document");

    Assert.assertTrue(newDocument.save());

    Map<String, Object> hm = Maps.newHashMap();
    hm.put("docName", "TestDocument:" + randomString);
    Document document1 = new Document().find(hm);

    Assert.assertEquals(document1.getDocName(), "TestDocument:" + randomString, "Test save() method in Document");
    Assert.assertEquals(document1.getPeerId(), 2);
    Assert.assertEquals(document1.getThumbnailPath(), "none");
//    Assert.assertEquals(document1.getType(), "Document");
  }

  @Test
  public void testUpdate() throws Exception
  {
    Document newDocument = new Document();
    newDocument.setPeerId(2);
    newDocument.setThumbnailPath("none");
    newDocument.setDocName("TestDocument:" + randomString);
//    newDocument.setType("Document");
    newDocument.save();

    Map<String, Object> hm = Maps.newHashMap();
    hm.put("docName", "TestDocument:" + randomString);
    Document document1 = new Document().find(hm);
    document1.setPeerId(3);
    document1.setThumbnailPath("none2");
    document1.setDocName("TestDocument2:" + randomString);
    document1.update();

    Map<String, Object> hm2 = Maps.newHashMap();
    hm2.put("docName", "TestDocument2:" + randomString);
    Document document2 = new Document().find(hm2);

    Assert.assertEquals(document2.getDocName(), "TestDocument2:" + randomString, "Test save() method in Document");
    Assert.assertEquals(document2.getPeerId(), 3);
    Assert.assertEquals(document2.getThumbnailPath(), "none2");
//    Assert.assertEquals(document1.getType(), "Document");
  }

  @Test
  public void testDestroy() throws Exception
  {
    Document newDocument = new Document();
    newDocument.setPeerId(2);
    newDocument.setThumbnailPath("none");
    newDocument.setDocName("TestDocument:" + randomString);
//    newDocument.setType("Document");

    newDocument.save();

    Map<String, Object> hm = Maps.newHashMap();
    hm.put("docName", "TestDocument:" + randomString);
    Document document1 = new Document().find(hm);

    document1.destroy();

    Assert.assertNull(new Document().find(hm), "Test destroy() method in Document");

  }
}
