package com.peerpen.model;

import java.util.List;

public interface Commentable {

    public void createComment( String Message, Peer peer );

    public List<Comment> findComments();
}
