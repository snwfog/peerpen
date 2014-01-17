package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.annotations.ActiveRecordField;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
        Feedable a = new Feedable().find(this.getId());
        super.update();
        if(a == null){
            System.out.println("NO FEEDABLE FOR OBJECT!!!!");
            return false;
        }else{
           a.setStatus("update");
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
        return super.save();
    }

    public boolean updateFeedable(){
        return super.save();
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
    }
}
