package com.peerpen.controller;

import com.peerpen.framework.GenericApplicationServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class IndexController extends GenericApplicationServlet {

    static final Logger logger = LoggerFactory.getLogger( IndexController.class );

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        try {
            // Get tweets
            Query query = new Query( "peerpen" );
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled( true )
                    .setOAuthConsumerKey( "DODZc22d25ltNKkol0aIw" )
                    .setOAuthConsumerSecret( "OAQuye6CBJYV6J1PzBlYh2fM0UbcEEoljuFLTIO67mo" )
                    .setOAuthAccessToken( "16729242-XOkBd0V2wVYiqIu4WkAkQLE0GcXC49z1TaaLhPWTV" )
                    .setOAuthAccessTokenSecret( "FqyeAcrur1SOLLbRZmth52Hy0NYXBS1rwB08fyVfuT8Bn" );
            TwitterFactory tf = new TwitterFactory( cb.build() );
            Twitter twitter = tf.getInstance();
            QueryResult result = twitter.search( query );
            List<Status> tweets = result.getTweets();
            //twitter.verifyCredentials();
            //List<Status> tweets = twitter.getMentionsTimeline();
            request.setAttribute( "tweets", tweets );
        } catch ( TwitterException e ) {
            logger.warn( "Cannot fetch tweets for Peerpen" );
        }

        request.getRequestDispatcher( "/view/index2.jsp" ).forward( request, response );
    }
}
