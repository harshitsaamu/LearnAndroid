package com.hgc.learnandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Notes extends AppCompatActivity {
     notes_adapter adapter_notes;
    RecyclerView mNotes;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    SharedPreferences sharedpreferences;
    List<String> nootes=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editText=new Intent(Notes.this,edit_note.class);
                startActivity(editText);
            }
        });
        setNotesLayout();
    }
    void setNotesLayout()
    {
       mNotes=(RecyclerView)findViewById(R.id.recycler_view_notes);
        nootes.add("a la fac jai'm appel HARSHIT GUPTA");
        nootes.add("Gupta");
        nootes.add("hey there iam using android");
        nootes.add("hey there iam using android thr djfhf gfdjgg gdhjgd hfstyr retrewyr etftet tgewutew");
        nootes.add("hey there iam using android");
        adapter_notes=new notes_adapter(Notes.this,nootes);
        mNotes.setAdapter(adapter_notes);
        mNotes.setLayoutManager(new StaggeredGridLayoutManager(2,1));
    }
}
