package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.IModel;
import com.sunnyd.annotations.*;
import java.util.HashMap;

public class Changeset extends Base implements IModel {

    public Changeset() {
        super();
    }

    public Changeset(HashMap<String, Object> HM) {
        super(HM);
    }

}
