package com.example.brunos.homemqttapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String fileName = "myFile.txt";
    Button button;
    TextView textView, testStore;
    MQTT mqtt;
    FileIO fileIO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mqtt = new MQTT(this);
        fileIO = new FileIO(this);

    }

}