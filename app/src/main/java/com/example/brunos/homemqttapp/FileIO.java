package com.example.brunos.homemqttapp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.content.Context.MODE_PRIVATE;

public class FileIO {
    private static final String TAGfileIO = "TAGfileIO";
    private static final int READ_BLOCK_SIZE = 100;
    Context context;

    public FileIO(Context context){
        this.context = context;
    }


    public void Writefile (String fileName, String fileInput){
        // add-write text into file
        try {
            FileOutputStream fileout = context.openFileOutput(fileName, MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(fileInput);
            outputWriter.close();
            Log.d(TAGfileIO, "Saved Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read text from file
    public String ReadFile (String fileName) {
        //reading text from file
        try {
            FileInputStream fileIn = context.openFileInput(fileName);
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";
            int charRead;

            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            InputRead.close();
            return s;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }


}
