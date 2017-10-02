/*
 *Class Name: CountBook
 *
 * Version: Version 1.0
 *
 * Date :October 1, 2017
 *
 * Copyright(c) Taijie yang, CMPUT University of Alberta - All Rights Reserved. You may use, distribute, or modify this code under terms and conditions of the Code of Students Behavior at University Of Alberta
 */
package com.example.taijie_countbook;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by taijieyang on 2017/9/27.
 */

public class CountBook {
    private String name;
    private Date date;
    private int initial;
    private int value;
    private String comment;

    public CountBook(String name, int value, String comment) {
        this.setName(name);
        this.date = new Date();
        this.setInitialValue(value);
        this.setValue(value);
        this.setComment(comment);
    }

    /**
     * some methods of CountBook
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setInitialValue (int value) {
        this.initial = value;
    }

    public void setValue (int value) {
        if (value >= 0 ) {
            this.value = value;
            this.date = new Date();
        }
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getInitialValue() {
        return initial;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "item: " + name + " | amount: " + Integer.toString(value) + "\n" + dateFormat.format(date);
    }
}