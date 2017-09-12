package com.lemips.idea;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by desk86 on 9/13/2017.
 */

public class IdeaFactory extends SQLiteOpenHelper {
    private static final String DB_NAME = "idea.db";
    private static final int DB_VERSION = 1;

    public IdeaFactory(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public synchronized void add(Idea idea){
        ContentValues values = new ContentValues();
        values.put("title", idea.getTitle());
        values.put("content", idea.getContent());
        SQLiteDatabase db = getWritableDatabase();
        db.insert("idea", null, values);
        db.close();
    }

    public synchronized Idea get(int ideaId){
        String query = "select * from idea where id = ?";
        String[] selectedArgs = {String.valueOf(ideaId)};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, selectedArgs);
        Idea idea = null;
        if (cursor.moveToFirst()){
            idea = new Idea(
                    cursor.getString(cursor.getColumnIndex("title")),
                    cursor.getString(cursor.getColumnIndex("content")));
            idea.setId(ideaId);
        }
        cursor.close();
        db.close();
        return idea;
    }

    public synchronized void update(Idea idea){
        ContentValues values = new ContentValues();
        values.put("title", idea.getTitle());
        values.put("content", idea.getContent());
        SQLiteDatabase db = getWritableDatabase();
        db.update("idea", values, "id=?",
                new String[]{String.valueOf(idea.getId())});
        db.close();
    }

    public synchronized void delete(Idea idea){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("idea","id=?",
                new String[]{String.valueOf(idea.getId())});
    }

    public synchronized List<Idea> getAllIdea(){
        List<Idea> ideaList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from idea", null);

        if (cursor.moveToFirst()){
            do {
                Idea idea = new Idea(
                        cursor.getString(cursor.getColumnIndex("title")),
                        cursor.getString(cursor.getColumnIndex("content")));
                idea.setId(cursor.getInt(cursor.getColumnIndex("id")));
                ideaList.add(idea);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ideaList;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists idea(" +
                "id integer not null primary key," +
                "title text," +
                "content text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
