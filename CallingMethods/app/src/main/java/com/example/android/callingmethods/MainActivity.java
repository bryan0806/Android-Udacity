package com.example.android.callingmethods;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView text = new TextView(this);
        text.setText("wow!");
        text.setTextColor(Color.BLUE);
        text.setTextSize(55);
        setContentView(text);
    }
}
