package com.example.ourproject;

import androidx.appcompat.app.AppCompatActivity;

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