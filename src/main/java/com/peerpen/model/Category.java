package com.peerpen.model;

import com.sunnyd.Base;
import com.sunnyd.IModel;
import com.sunnyd.annotations.ActiveRecordField;
import com.sunnyd.annotations.ActiveRecordModel;

import java.util.Map;

@ActiveRecordModel
public class Category extends Base implements IModel {

    public static final String tableName = "categories";

    @ActiveRecordField
    private String categoryName;

    @ActiveRecordField
    private String description;

    public Category() {
        super();
    }

    public Category( Map<String, Object> HM ) {
        super( HM );
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName( String categoryName ) {
        this.categoryName = categoryName;
        setUpdateFlag( true );
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
        setUpdateFlag( true );
    }

}
