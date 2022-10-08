package com.billingApp.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tblProductTypes")
public class ProdTypesTbl {

    @PrimaryKey
    @ColumnInfo(name = "PK_ProductTypeID")
    public @NonNull int prodTypeID;

    @ColumnInfo(name = "productType")
    public  @NonNull String prodType;

}
