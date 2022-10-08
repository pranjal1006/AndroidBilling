package com.billingApp.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tblProducts")
public class ProductsTable {
    @PrimaryKey
    @ColumnInfo(name = "PK_ProductKey")
    public @NonNull int prodKey;

    @ColumnInfo(name = "productName")
    public  @NonNull String prodName;

    @ColumnInfo(name = "HSNcode")
    public @NonNull String HSN;

}
