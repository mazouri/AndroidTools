package com.mazouri.tools.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mazouri.tools.Tools;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Tools.string().isBlank(" ");
    }
}
