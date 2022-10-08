package com.billingApp.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tblSpecialCategories")
public class CategoriesTbl {

    @PrimaryKey
    @ColumnInfo(name = "PK_CategoryKey")
    public @NonNull int categoryKey;

    @ColumnInfo(name = "category")
    public  @NonNull String category;

}