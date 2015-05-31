package com.bonam.notepad;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class NoteListActivityMultiColumn extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list_activity_multi_column);

        ListView listView=(ListView)findViewById(R.id.lst_note_multi_column);

        ArrayList list=new ArrayList<HashMap<String,String>>();

        HashMap<String,String> temp=new HashMap<String, String>();
        temp.put(NoteListAdapter.FIRST_COLUMN, "Ankit Karia");
        temp.put(NoteListAdapter.SECOND_COLUMN, "Male");
        temp.put(NoteListAdapter.THIRD_COLUMN, "22");
        temp.put(NoteListAdapter.FOURTH_COLUMN, "Unmarried");
        list.add(temp);

        HashMap<String,String> temp2=new HashMap<String, String>();
        temp2.put(NoteListAdapter.FIRST_COLUMN, "Rajat Ghai");
        temp2.put(NoteListAdapter.SECOND_COLUMN, "Male");
        temp2.put(NoteListAdapter.THIRD_COLUMN, "25");
        temp2.put(NoteListAdapter.FOURTH_COLUMN, "Unmarried");
        list.add(temp2);

        HashMap<String,String> temp3=new HashMap<String, String>();
        temp3.put(NoteListAdapter.FIRST_COLUMN, "Karina Kaif");
        temp3.put(NoteListAdapter.SECOND_COLUMN, "Female");
        temp3.put(NoteListAdapter.THIRD_COLUMN, "31");
        temp3.put(NoteListAdapter.FOURTH_COLUMN, "Unmarried");
        list.add(temp3);

        NoteListAdapter adapter=new NoteListAdapter(this, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                int pos=position+1;
                Toast.makeText(NoteListActivityMultiColumn.this, Integer.toString(pos) + " Clicked", Toast.LENGTH_SHORT).show();
            }

        });
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
}
