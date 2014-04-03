package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.annotations.ActiveRecordField;

import java.util.Map;

public class Rank extends Base {

    public static final String tableName = "ranks";

    @ActiveRecordField
    private String rankName;

    @ActiveRecordField
    private Integer minPoint;

    public Rank() {
        super();
    }

    public Rank( Map<String, Object> HM ) {
        super( HM );
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName( String rankName ) {
        this.rankName = rankName;
        setUpdateFlag( true );
    }

    public Integer getMinPoint() {
        return minPoint;
    }

    public void setMinPoint( Integer minPoint ) {
        this.minPoint = minPoint;
        setUpdateFlag( true );
    }

}
