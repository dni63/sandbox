package com.bonam.notepad;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bonam.notepad.entity.Note;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Daniel on 31/05/2015.
 */


public class NoteListAdapter extends BaseAdapter {

    public static final String FIRST_COLUMN = "Title";
    public static final String SECOND_COLUMN = "LastModified";

    public ArrayList<Note> list;
    Activity activity;
    TextView txtTitle;
    TextView txtLastModified;

    public NoteListAdapter(Activity activity, ArrayList<Note> list) {
        super();
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.note_row, null);

            txtTitle = (TextView) convertView.findViewById(R.id.row_title);
            txtLastModified = (TextView) convertView.findViewById(R.id.row_last_modified);
        }

        Note note = list.get(position);
        txtTitle.setText(note.getTitle());
        txtLastModified.setText(note.getLastModifiedStr());

        return convertView;
    }
}