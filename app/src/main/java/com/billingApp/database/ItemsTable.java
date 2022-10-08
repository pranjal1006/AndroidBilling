package com.billingApp.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.util.TableInfo;

import static androidx.room.ForeignKey.CASCADE;
import static androidx.room.ForeignKey.RESTRICT;

@Entity(tableName = "tblItems", foreignKeys = {
        @ForeignKey(
                entity = UnitsTbl.class,
                parentColumns =  "PK_UnitKey",
                childColumns =  "firstPackUnit",
                onUpdate = CASCADE,
                onDelete = RESTRICT
                ),
        @ForeignKey(
                entity = UnitsTbl.class,
                parentColumns =  "PK_UnitKey",
                childColumns = "secondPackUnit",
                onUpdate = CASCADE,
                onDelete = RESTRICT
        ),
        @ForeignKey(
                entity = UnitsTbl.class,
                parentColumns =  "PK_UnitKey",
                childColumns =  "mainUnit",
                onUpdate = CASCADE,
                onDelete = RESTRICT
        ),
        @ForeignKey(
                entity = ProductsTable.class,
                parentColumns = "PK_ProductKey",
                childColumns = "product",
                onUpdate = CASCADE,
                onDelete = RESTRICT
            ),
        @ForeignKey(
                entity = ProdTypesTbl.class,
                parentColumns = "PK_ProductTypeID",
                childColumns = "productType",
                onUpdate = CASCADE,
                onDelete = RESTRICT
            ),
        @ForeignKey(
                entity = CategoriesTbl.class,
                parentColumns = "PK_CategoryKey",
                childColumns = "category",
                onUpdate = CASCADE,
                onDelete = RESTRICT
            ),
        @ForeignKey(
        entity = SizesTbl.class,
        parentColumns =  "PK_SizeKey",
        childColumns =  "pSize",
                onUpdate = CASCADE,
                onDelete = RESTRICT
            ),
        @ForeignKey(
                entity = SizesTbl.class,
                parentColumns =  "PK_SizeKey",
                childColumns =  "rdSize",
                onUpdate = CASCADE,
                onDelete = RESTRICT
        )
    })
public class  ItemsTable{
    @PrimaryKey
    @ColumnInfo(name = "PK_PartNo")
    public @NonNull
    long partNo;

    @ColumnInfo(name = "itemCode")
    public  @NonNull long itemCode;

    @ColumnInfo(name = "itemName")
    public  @NonNull String itemName;

    @ColumnInfo(name = "firstPackUnit", index = true)
    public  @NonNull int fpu;

    @ColumnInfo(name = "firstPackQty")
    public  @NonNull int fpq;

    @ColumnInfo(name = "secondPackUnit", index = true)
    public  @NonNull int spu;

    @ColumnInfo(name = "secondPackQty")
    public  @NonNull int spq;

    @ColumnInfo(name = "mainUnit", index = true)
    public  @NonNull int mainUnit;

    @ColumnInfo(name = "MRP")
    public  @NonNull double mrp;

    @ColumnInfo(name = "product", index = true)
    public  @NonNull int product;

    @ColumnInfo(name = "productType", index = true)
    public  @NonNull int prodType;

    @ColumnInfo(name = "category", index = true)
    public  @NonNull int category;

    @ColumnInfo(name = "pSize", index = true)
    public  @NonNull int pSize;

    @ColumnInfo(name = "rdSize", index = true)
    public  @NonNull int rdSize;
}
