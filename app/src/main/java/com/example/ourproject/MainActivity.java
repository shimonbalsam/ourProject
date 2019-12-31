package com.example.ourproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String query = "select sqlite_version() AS sqlite_version";
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(":memory:", null);
        Cursor cursor = db.rawQuery(query, null);
        String sqliteVersion = "";
        if (cursor.moveToNext()) {
            sqliteVersion = cursor.getString(0);
        }

       /* final Button press = findViewById(R.id.pressButton);
        final TextView resultsTextView = findViewById(R.id.showInput);
        final EditText inputText = findViewById(R.id.editText);

        press.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                resultsTextView.setText("hello "+ inputText.getText());
            }
        });*/
    }
}