package com.bonam.notepad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bonam.notepad.entity.Note;

import java.util.ArrayList;
import java.util.GregorianCalendar;
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
    public static final String NOTE_COLUMN_LAST_MODIFIED = "last_modified";
    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table note " +
                        "(id integer primary key, title text, note text, last_modified date)"
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
        contentValues.put("last_modified", Note.getDefaultLastModified());
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
        contentValues.put("note", Note.getDefaultLastModified());
        contentValues.put("last_modified", "datetime()");
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

    public ArrayList<Note> getAllNotes()
    {
        ArrayList<Note> array_list = new ArrayList<Note>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from note order by last_modified desc", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Note note = new Note();
            note.setTitle(res.getString(res.getColumnIndex(NOTE_COLUMN_TITLE)));
            note.setLastModified(res.getString(res.getColumnIndex(NOTE_COLUMN_LAST_MODIFIED)));
            note.setNote(res.getString(res.getColumnIndex(NOTE_COLUMN_NOTE)));
            System.out.println("Note = " +note.getNote());
            array_list.add(note);
            res.moveToNext();
        }

        System.out.println("size=" + array_list.size());

        return array_list;
    }
}