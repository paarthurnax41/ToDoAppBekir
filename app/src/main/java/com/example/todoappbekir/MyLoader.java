package com.example.todoappbekir;

import android.content.Context;
import android.database.Cursor;

import androidx.loader.content.CursorLoader;

public class MyLoader extends CursorLoader {
    DataBaseHelper hilfMirDB;

    public MyLoader (Context context, DataBaseHelper db){
        super(context);
        hilfMirDB = db;
    }

    @Override
    public Cursor loadInBackground(){
        return hilfMirDB.getAlltoDos();
    }
}
