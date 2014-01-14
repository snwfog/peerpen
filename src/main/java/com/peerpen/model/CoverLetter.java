package com.peerpen.model;

import java.util.HashMap;
import java.util.Map;

import com.sunnyd.annotations.ActiveRecordInheritFrom;
import com.sunnyd.annotations.ActiveRecordField;

@ActiveRecordInheritFrom(childClassof = "Document")
public class CoverLetter extends Document {

    public static final String tableName = "cover_letters";

    public CoverLetter() {
        super();
    }

    public CoverLetter(Map<String, Object> HM) {
        super(HM);
    }

}
