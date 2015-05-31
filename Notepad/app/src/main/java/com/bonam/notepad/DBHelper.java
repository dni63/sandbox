package com.bonam.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Daniel on 31/05/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyNotepad.db";
    public static final String NOTE_TABLE_NAME = "note";
    public static final String NOTE_COLUMN_ID = "id";
    public static final String NOTE_COLUMN_TITLE = "title";
    public static final String NOTE_COLUMN_NOTE = "note";
    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table note " +
                        "(id integer primary key, title text, note text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS note");
        onCreate(db);
    }

    public boolean insertNote  (String title, String note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("note", note);
        db.insert("note", null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from note where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, NOTE_TABLE_NAME);
        return numRows;
    }

    public boolean updateNote (Integer id, String title, String note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("note", note);
        db.update("note", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteNote (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("note",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllNotes()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from note", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(NOTE_COLUMN_TITLE)));
            res.moveToNext();
        }
        return array_list;
    }
}