//package com.example.firstapp;
//
//import android.content.Context;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteException;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import androidx.annotation.Nullable;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//public class DatabaseOperations extends SQLiteOpenHelper {
//    private Context mycontext;
//
//    private static String DB_NAME = "testdb.db";
//    public SQLiteDatabase myDataBase;
//    private String DB_PATH;
//
//    public DatabaseOperations(Context context) throws IOException {
//
//        super(context,DB_NAME,null,1);
//        this.mycontext=context;
//        DB_PATH = "/data/data/"
//                + mycontext.getApplicationContext().getPackageName()
//                + "/assets/";
//        boolean dbexist = checkdatabase();
//        if (dbexist) {
//            //System.out.println("Database exists");
//            opendatabase();
//        } else {
//            System.out.println("Database doesn't exist");
//            createdatabase();
//        }
//    }
//
//    public void createdatabase() throws IOException {
//        boolean dbexist = checkdatabase();
//        if(dbexist) {
//            //System.out.println(" Database exists.");
//        } else {
//            this.getReadableDatabase();
//            try {
//                copydatabase();
//            } catch(IOException e) {
//                throw new Error("Error copying database");
//            }
//        }
//    }
//
//    private boolean checkdatabase() {
//        //SQLiteDatabase checkdb = null;
//        boolean checkdb = false;
//        try {
//            String myPath = DB_PATH + DB_NAME;
//            File dbfile = new File(myPath);
//            //checkdb = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
//            checkdb = dbfile.exists();
//        } catch(SQLiteException e) {
//            System.out.println("Database doesn't exist");
//        }
//        return checkdb;
//    }
//
//    private void copydatabase() throws IOException {
//        //Open your local db as the input stream
//        InputStream myinput = mycontext.getAssets().open(DB_NAME);
//
//        // Path to the just created empty db
//        String outfilename = DB_PATH + DB_NAME;
//
//        //Open the empty db as the output stream
//        OutputStream myoutput = new FileOutputStream("/data/data/(packagename)/databases   /(datbasename).sqlite");
//
//        // transfer byte to inputfile to outputfile
//        byte[] buffer = new byte[1024];
//        int length;
//        while ((length = myinput.read(buffer))>0) {
//            myoutput.write(buffer,0,length);
//        }
//
//        //Close the streams
//        myoutput.flush();
//        myoutput.close();
//        myinput.close();
//    }
//
//
//    public void opendatabase() throws SQLException {
//        //Open the database
//        String mypath = DB_PATH + DB_NAME;
//        myDataBase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
//    }
//
//    public synchronized void close() {
//        if(myDataBase != null) {
//            myDataBase.close();
//        }
//        super.close();
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//
//}

package com.billingApp.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseOperations {
    ProductCatalogue productCatalogue;

    public DatabaseOperations(Context context){
        openDatabase(context);
    }

    public void openDatabase(Context context) {

        productCatalogue = Room.databaseBuilder(context,
                ProductCatalogue.class, "ashirvadtest5.db")
                .createFromAsset("ashirvadtest5.db")
                .allowMainThreadQueries()
                .build();
    }

    public String findItemByItemCode(long itemCode) {

        return productCatalogue.itemsTableDao().findItemByItemCode(itemCode);
    }

    public String findItemByPartNo(long partNo) {

        return productCatalogue.itemsTableDao().findItemByPartNo(partNo);
    }
}