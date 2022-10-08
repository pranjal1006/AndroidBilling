package com.billingApp.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tblSizes")
public class SizesTbl {

    @PrimaryKey
    @ColumnInfo(name = "PK_SizeKey")
    public @NonNull int sizeKey;

    @ColumnInfo(name = "size")
    public  @NonNull String size;

}
