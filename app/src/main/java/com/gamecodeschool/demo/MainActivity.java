package com.gamecodeschool.demo;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.gamecodeschool.demo.adapters.NotesAdapter;
import com.gamecodeschool.demo.database.DatabaseHelper;
import com.gamecodeschool.demo.database.Notes;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ListView noteslist;
    List<Notes> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),AddNoteActivity.class));
            }
        });

        listView=findViewById(R.id.lvlistview);
    }

    private void displayNotes(){
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext(),"notes",null,1);
        noteList=new ArrayList<Notes>();

        noteList = databaseHelper.getNotes();

        Log.d("mynotes", "My database" + noteList.size()+"notes");
        //NotesAdapter notesAdapter = new NotesAdapter(getBaseContext(), 0, noteslist);
        NotesAdapter notesAdapter =new NotesAdapter(getBaseContext(),0,noteList);
        listView.setAdapter(notesAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note clickedNote=noteList.get(position);
                Intent intent=new Intent(getBaseContext(),ViewNoteActivity.class);
                intent.putExtra("NOTE_ID",clickedNote.getId());
                startActivity(intent);
            }
        });

    }

    private void displayNames(){
        DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext(), "names", null, 1);
        List<String> namesList=new ArrayList<String>();
        namesList.add("Purity Mbugua");
        namesList.add("Pauline Broen");
        namesList.add("Emma Mbugua");
        namesList.add("Joy Wanja");
        namesList.add("Leslie Muchiri");
        namesList.add("Lucy Mbugua");
        namesList.add("Leah Kamere");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,namesList);
        listView.setAdapter(arrayAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        displayNotes();
//        displayNames();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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