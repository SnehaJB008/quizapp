package com.example.root.quizapp1;


import android.support.v7.widget.RecyclerView;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by root on 8/5/18.
 */

public class Questions {
    public ArrayList<Questiondetail> getResults() {
        return results;
    }

    public void setResults(ArrayList<Questiondetail> results) {
        this.results = results;
    }

    private ArrayList<Questiondetail> results;

}
