package com.billingApp.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tblTallyItems")
public class TallyItemsTbl {

    @PrimaryKey
    @ColumnInfo(name = "PK_PartNo")
    public @NonNull long partNo;

    @ColumnInfo(name = "itemName")
    public  @NonNull String itemName;

}
