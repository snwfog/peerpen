package com.peerpen.model;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: waisk
 * Date: 15/02/14
 * Time: 7:55 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Commentable{

    public void createComment(String Message, Peer peer);
    public List<Comment> findComments();
}
