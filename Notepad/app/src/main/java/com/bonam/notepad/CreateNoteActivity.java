package com.bonam.notepad;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class CreateNoteActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_note, menu);

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

    public void btn_save_click(View view) {
        EditText txtTitle = (EditText) findViewById(R.id.txt_title);
        EditText txtNote = (EditText) findViewById(R.id.txt_note);
        if(validateNote(txtTitle.toString(), txtNote.toString())) {
            DBHelper dbHelper = new DBHelper(view.getContext());
            dbHelper.insertNote(txtTitle.getText().toString(), txtNote.getText().toString());
        }
    }

    public boolean validateNote(String title, String note) {
        if(title.isEmpty() || note.isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "Please insert title and note", Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        return true;
    }
}