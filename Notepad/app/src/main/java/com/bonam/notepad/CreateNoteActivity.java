package com.bonam.notepad;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.bonam.notepad.entity.Note;


public class CreateNoteActivity extends ActionBarActivity {

    private Note selectedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        selectedNote = (Note) getIntent().getParcelableExtra(getString(R.string.key_selected_note));

        if(selectedNote != null) {
            //edit mode
            EditText txtNote = (EditText) findViewById(R.id.txt_note);
            EditText txtTitle = (EditText) findViewById(R.id.txt_title);

            txtNote.setText(selectedNote.getNote());
            txtTitle.setText(selectedNote.getTitle());

            setTitle(getString(R.string.title_activity_edit_note));

            txtTitle.setSelection(selectedNote.getTitle().length());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_note, menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void btn_save_click(View view) {
        EditText txtTitle = (EditText) findViewById(R.id.txt_title);
        EditText txtNote = (EditText) findViewById(R.id.txt_note);
        if(validateNote(txtTitle.getText().toString(), txtNote.getText().toString())) {
            if(selectedNote == null) {
                //add mode
                saveNote(view, txtTitle.getText().toString(), txtNote.getText().toString());
            } else {
                editNote(view, selectedNote.getId(), txtTitle.getText().toString(), txtNote.getText().toString());
            }

            Intent resultIntent = new Intent();
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }
    }

    private void saveNote(View view, String title, String note) {
        DBHelper dbHelper = new DBHelper(view.getContext());
        dbHelper.insertNote(title, note);
        Toast.makeText(this, getString(R.string.msg_save_successful), Toast.LENGTH_SHORT).show();
    }

    private void editNote(View view, int id, String title, String note) {
        DBHelper dbHelper = new DBHelper(view.getContext());
        dbHelper.updateNote(id, title, note);
        Toast.makeText(this, getString(R.string.msg_edit_successful), Toast.LENGTH_SHORT).show();
    }

    public boolean validateNote(String title, String note) {
        if(title.isEmpty() || note.isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.msg_title_note_required), Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        return true;
    }
}
