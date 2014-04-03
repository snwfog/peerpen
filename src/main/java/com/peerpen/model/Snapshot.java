package com.peerpen.model;

import com.sunnyd.Base;

import java.util.Map;

public class Snapshot extends Base {

    public static final String tableName = "snapshots";

    public Snapshot() {
        super();
    }

    public Snapshot( Map<String, Object> HM ) {
        super( HM );
    }
}
