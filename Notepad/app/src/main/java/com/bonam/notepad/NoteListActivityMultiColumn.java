package com.bonam.notepad;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.bonam.notepad.entity.Note;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;


public class NoteListActivityMultiColumn extends ActionBarActivity {

    public static final int CREATE_REQUEST_INT = 1;
    public static final int UPDATE_REQUEST_INT = 2;
    private Note selectedNote;
    private ArrayList<Note> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list_activity_multi_column);

        refreshList();
        setClickListener();
    }

    private void setClickListener() {
        ListView listView=(ListView)findViewById(R.id.lst_note_multi_column);
        final Toast toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                selectedNote = noteList.get(position);

                Intent intent = new Intent(NoteListActivityMultiColumn.this, CreateNoteActivity.class);
                intent.putExtra(getString(R.string.key_selected_note), selectedNote);
                startActivityForResult(intent, UPDATE_REQUEST_INT);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    private void refreshList() {
        ListView listView = (ListView)findViewById(R.id.lst_note_multi_column);

        DBHelper dbHelper = new DBHelper(this);
        noteList = dbHelper.getAllNotes();

        NoteListAdapter adapter=new NoteListAdapter(this, noteList);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note_list_activity_multi_column, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void btn_add_click(View view) {
        Intent intent = new Intent(this, CreateNoteActivity.class);
        startActivityForResult(intent, CREATE_REQUEST_INT);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (CREATE_REQUEST_INT) :
            case (UPDATE_REQUEST_INT) : {
                if (resultCode == Activity.RESULT_OK) {
                    refreshList();
                }
                break;
            }
        }
    }
}
