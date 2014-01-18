package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.annotations.ActiveRecordField;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Mike
 * Date: 1/10/14
 * Time: 1:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class Feedable extends Base {
    @ActiveRecordField
    private String type;

    @ActiveRecordField
    private String status;

    @ActiveRecordField
    private Integer childId;

    @ActiveRecordField
    private Integer userId;

    private Feedable trueSelf;

    private String modelPath = "com.peerpen.model.";


    public Feedable() {
        super();
    }

    public Feedable(Map<String, Object> HM) {
        super(HM);
    }

    @Override
    public boolean save(){
        Feedable a = new Feedable();
        super.save();
        System.out.println(this.getId());
        if(this.getId() != null){
           a.setType(this.getClass().getSimpleName());
           a.setChildId(this.getId());
           a.setStatus("new");

           if(this instanceof Changeset){
               Changeset ch = (Changeset) this;
               if(ch.getDocument() != null){
                   a.setUserId(ch.getDocument().getPeerId());
               }
           }

            if(this instanceof Comment){
                Comment com = (Comment) this;
                if(com.getDocumentId() != 0){
                    if(com.getDocument() != null){
                        a.setUserId(com.getDocument().getPeerId());
                    }
                }else{
                    if(com.getChangeset() != null){
                        a.setUserId(com.getChangeset().getPeerId());
                    }
                }
            }
           return a.saveFeedable();
        }else{
            return false;
        }
    }

    private boolean saveFeedable(){
        return super.save();
    }

    @Override
    public boolean update(){
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("childId", this.getId());
        Feedable a = new Feedable().find(m);
        super.update();
        if(a == null){
            System.out.println("NO FEEDABLE FOR OBJECT!!!!");
            return false;
        }else{
           System.out.println("FOUND FEEDABLE");
           a.setStatus("update");
           if(this instanceof Changeset){
               Changeset ch = (Changeset) this;
               if(ch.getDocument() != null){
                   a.setUserId(ch.getDocument().getPeerId());
               }
           }
            if(this instanceof Comment){
                Comment com = (Comment) this;
                if(com.getDocumentId() != 0){
                    if(com.getDocument() != null){
                        a.setUserId(com.getDocument().getPeerId());
                    }
                }else{
                    if(com.getChangeset() != null){
                        a.setUserId(com.getChangeset().getPeerId());
                    }
                }
            }
           return a.updateFeedable();
        }
    }

    @Override
    public boolean destroy(){
        Feedable a = new Feedable().find(this.getId());
        super.destroy();
        if(this.getId() == null){
            if(a != null){
                return a.destroyFeedable();
            }else{
                System.out.println("NO FEEDABLE FOR OBJECT!!!!");
                return false;
            }
        }
        return false;
    }

    public boolean destroyFeedable(){
        return super.destroy();
    }

    public boolean updateFeedable(){
        System.out.println(this.getStatus());
        System.out.println("updating feedable");
        return super.update();
    }


    public Feedable reveal(){
        if(trueSelf == null){
            try {
                Constructor cons = Class.forName(modelPath+this.getType()).getConstructor();
                trueSelf = (Feedable) Class.forName(modelPath+this.getType()).getMethod("find", int.class).invoke(cons.newInstance(),this.getChildId());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (ClassNotFoundException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (InvocationTargetException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IllegalAccessException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (InstantiationException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return trueSelf;
    }

    public static void revealList(List<Feedable> feedables){
        for( Feedable f : feedables){
             Integer index = feedables.indexOf(f);
             feedables.add(index, f.reveal());
        }
    }

    public static List<Feedable> getFeed(Peer p){
      return getFeed(p.getId());
    }

    public static List<Feedable> getFeed(int id){
        Map<String, Object> m = new HashMap<String, Object>();
        return new Feedable().findAll(m);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        setUpdateFlag(true);
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
        setUpdateFlag(true);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        setUpdateFlag(true);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
        setUpdateFlag(true);
    }
}
