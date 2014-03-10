package com.peerpen.helper;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: momoking
 * Date: 2014-03-09
 * Time: 11:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class AutocompleteTest {

    @Test
    public void testGetSuggestedDocuments() throws Exception {
        String json = Autocomplete.getSuggestedDocuments( "res", 100 );
        Assert.assertTrue( json.toLowerCase().contains( "res" ), "Check in result contains keyword" );
    }

    @Test
    public void testGetSuggestedPeers() throws Exception {
        String json = Autocomplete.getSuggestedPeers( "bo", 100 );
        Assert.assertTrue( json.toLowerCase().contains( "bo" ), "Check in result contains keyword" );
    }

    @Test
    public void testGetSuggestedGroups() throws Exception {
        String json = Autocomplete.getSuggestedGroups( "fina", 100 );
        Assert.assertTrue( json.toLowerCase().contains( "fina" ), "Check in result contains keyword" );
    }

    @Test
    public void testGetSuggestedTagDescriptors() throws Exception {

    }
}
