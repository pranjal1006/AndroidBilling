package com.billingApp.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.billingApp.database.DatabaseOperations;
import com.example.ashirvadbilling.R;

public class MainActivity extends AppCompatActivity {

    DatabaseOperations databaseOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseOperations = new DatabaseOperations(this);
    }

    public DatabaseOperations getDatabaseOperations() {
        return databaseOperations;
    }
}