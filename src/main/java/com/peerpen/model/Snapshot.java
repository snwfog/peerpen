package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.IModel;
import com.sunnyd.annotations.*;
import java.util.HashMap;
import java.util.Map;

public class Snapshot extends Base implements IModel {

    public static final String tableName = "snapshots";

    public Snapshot() {
        super();
    }

    public Snapshot(Map<String, Object> HM) {
        super(HM);
    }
}
