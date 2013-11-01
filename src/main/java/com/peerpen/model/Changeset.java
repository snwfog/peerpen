package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.IModel;
import com.sunnyd.annotations.*;
import com.sunnyd.database.Manager;

import java.util.HashMap;

public class Changeset extends Base implements IModel {
    public static final String tableName = "changesets";

    @ActiveRelationHasOne
    private Integer documentId;
    private Document document;

    @ActiveRelationHasOne
    private Integer peerId;
    private Peer peer;

    @ActiveRelationHasOne
    private Integer hunkId;
    private Hunk hunk;


    public Changeset() {
        super();
    }

    public Changeset(HashMap<String, Object> HM) {
        super(HM);
    }

    public void setDocId(){
        this.documentId = document.getId();
    }

    public Integer getDocId(){
        return this.documentId;
    }

    public void setDocument(Document document){
        this.document = document;
    }

    public Document getDocument(){
        if(document == null){
            document = Document.find(this.getDocId());
        }
        return document;
    }

    public void setPeerId(Integer peerId){
        this.peerId = peerId;
    }

        public int getPeerId(){
            return this.peerId;
        }

    public Peer getPeer(){
        if(peer == null){
           // HashMap<String, Object> foundPeer = Manager.find(peerId, "peers");
           //this.peer = new Peer(foundPeer);
            peer = Peer.find(this.getPeerId());
        }
        return peer;
    }

    public void setHunkId(Integer hunkId){
        this.hunkId = hunkId;
    }

    public int getHunkId(){
        return this.hunkId;
    }

    public Hunk getHunk(){
        if(hunk == null){
            // HashMap<String, Object> foundPeer = Manager.find(peerId, "peers");
            //this.peer = new Peer(foundPeer);
            hunk = Changeset.find(this.getHunkId());
        }
        return hunk;
    }

}
