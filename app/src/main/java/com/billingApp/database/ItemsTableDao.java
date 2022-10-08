package com.billingApp.database;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemsTableDao {
    @Query("SELECT itemName FROM tblItems WHERE itemCode = :itemCode")
    String findItemByItemCode(long itemCode);

    @Query("SELECT itemName FROM tblItems WHERE PK_PartNo = :partNo")
    String findItemByPartNo(long partNo);

}
