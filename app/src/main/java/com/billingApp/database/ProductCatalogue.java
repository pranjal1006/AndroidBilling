package com.billingApp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {
            ProdTypesTbl.class,
            CategoriesTbl.class,
            SizesTbl.class,
            TallyItemsTbl.class,
            UnitsTbl.class,
            ProductsTable.class,
            ItemsTable.class,
        },
        version = 1, exportSchema = false)

abstract class ProductCatalogue extends RoomDatabase {
    public abstract ItemsTableDao itemsTableDao();
}
