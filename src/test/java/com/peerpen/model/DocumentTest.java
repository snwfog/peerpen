package com.peerpen.model;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: bobbyyit
 * Date: 2014-03-08
 * Time: 5:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class DocumentTest
{
  private Document document;

  @BeforeMethod
  public void setUp() throws Exception
  {
    document = new Document().find(3);
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

  }

  @Test
  public void testGetComments() throws Exception
  {
//     dfsdf
  }

  @Test
  public void testGetMatchedDocuments() throws Exception
  {
      //     fgdfg
  }

  @Test
  public void testGetSuggestions() throws Exception
  {

  }

  @Test
  public void testCreateComment() throws Exception
  {

  }

  @Test
  public void testFindComments() throws Exception
  {

  }

  @Test
  public void testGetChangeset() throws Exception
  {

  }
}
