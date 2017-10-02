/*
 *Class Name: Edit
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
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * This activity is called when user tap an item in list
 */
public class Edit extends AppCompatActivity {
    public static final String FILENAME = "file.sav";
    public ArrayList<CountBook> counterArrayList = new ArrayList<CountBook>();
    private CountBook CountBook;
    private int position;

    /**
     * show the interface of this activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        loadFromFile();
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        CountBook = counterArrayList.get(position);
        /**
         * set the value of textview by getting from CountBook
         */
        TextView name = (TextView) findViewById(R.id.item_name);
        name.setText(CountBook.getName());
        TextView value= (TextView) findViewById(R.id.item_value);
        value.setText(Integer.toString(CountBook.getValue()));
        TextView comment = (TextView) findViewById(R.id.item_comment);
        comment.setText(CountBook.getComment());

    }

    /**
     * delete function
     * called when user tap delete
     * delete the item from CountBook array list
     * @param view
     */
    public void delete(View view){
        counterArrayList.remove(position);
        saveInFile();
        finish();
    }

    /**
     * increase function
     * called when user tap +1 button
     * +1 in item value
     * @param view
     */
    public void increase(View view){
        CountBook.setValue(CountBook.getValue()+1);
        counterArrayList.set(position,CountBook);
        TextView value = (TextView) findViewById(R.id.item_value);
        value.setText(Integer.toString(CountBook.getValue()));
        saveInFile();
    }

    /**
     * decrease function
     * called when user tap -1 button
     * -1 in item value
     * @param view
     */

    public void decrease(View view){
        CountBook.setValue(CountBook.getValue()-1);
        counterArrayList.set(position,CountBook);
        TextView textView = (TextView) findViewById(R.id.item_value);
        textView.setText(Integer.toString(CountBook.getValue()));
        saveInFile();
    }

    /**
     * reset function
     * called when user tap reset button
     * make the value become initialvalue
     * @param view
     */
    public void reset(View view){
        CountBook.setValue(CountBook.getInitialValue());
        counterArrayList.set(position, CountBook);
        TextView textView = (TextView) findViewById(R.id.item_value);
        textView.setText(Integer.toString(CountBook.getValue()));
        saveInFile();
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
