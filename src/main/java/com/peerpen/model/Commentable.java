package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.annotations.ActiveRecordField;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: waisk
 * Date: 15/02/14
 * Time: 7:55 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Commentable{

    public void createComment(String Message, Peer peer);
    public void findComments();
}
