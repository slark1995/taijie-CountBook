/*
 *Class Name: AddNewActivity
 *
 * Version: Version 1.0
 *
 * Date :October 1, 2017
 *
 * Copyright(c) Taijie yang, CMPUT University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University Of Alberta
 */
package com.example.taijie_countbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.provider.Telephony.Mms.Part.FILENAME;

/**
 * this activity is called when the user tap addnew button
 */
public class AddNewActivity extends AppCompatActivity {
    public static final String FILENAME = "file.sav";
    private EditText nameText;
    private EditText valueText;
    private EditText commentText;
    public ArrayList<CountBook> counterArrayList = new ArrayList<CountBook>();
    private ArrayAdapter<CountBook> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        loadFromFile();

    }

    /**
     * createCounter function
     * this function is called when user tap confirm button
     * then pass the value user entered to the counterArrayList
     * @param view
     */
    public void createCounter(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        nameText = (EditText) findViewById(R.id.nameText);
        valueText = (EditText) findViewById(R.id.valueText);
        commentText = (EditText) findViewById(R.id.commentText);

        String name = nameText.getText().toString();
        int value = Integer.parseInt(valueText.getText().toString());
        String comment = commentText.getText().toString();


        counterArrayList.add(new CountBook(name,value,comment));


        saveInFile();
        finish();


    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<CountBook>>() {}.getType();
            counterArrayList = gson.fromJson(in,listType);
            //https://github.com/google/gson/blob/master/UserGuide.md#TOC-Collections-Examples

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            counterArrayList = new ArrayList<CountBook>();
        }

    }
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(counterArrayList,writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
