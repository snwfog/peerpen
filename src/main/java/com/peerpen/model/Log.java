package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.annotations.ActiveRecordField;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Wais
 * Date: 11/15/13
 * Time: 8:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class Log extends Base {

    public static final String tableName = "logs";
    @ActiveRecordField
    private String etag;

    public Log()
    {
        super();
    }

    // TODO: We need to add a parameterized constructor here
    public Log(Map<String, Object> HM)
    {
        super(HM);
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }
}
