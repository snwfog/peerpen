package com.peerpen.model;

import com.sunnyd.annotations.ActiveRecordInheritFrom;

import java.util.Map;

@ActiveRecordInheritFrom(childClassof = "Document")
public class CoverLetter extends Document {

    public static final String tableName = "cover_letters";

    public CoverLetter() {
        super();
    }

    public CoverLetter( Map<String, Object> HM ) {
        super( HM );
    }

}
