package com.billingApp.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tblUnits")
public class UnitsTbl {

    @PrimaryKey
    @ColumnInfo(name = "PK_UnitKey")
    public @NonNull int unitKey;

    @ColumnInfo(name = "unit")
    public  @NonNull String unit;

}