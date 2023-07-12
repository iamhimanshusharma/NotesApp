package com.example.notesappprojectnew;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.notesappprojectnew.Models.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesTakerActivity extends AppCompatActivity {

    EditText notesTitle, notesNote;
    Button saveBtn;
    Notes notes;
    boolean isOldNote = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taker);

        notesTitle = findViewById(R.id.enterTitle);
        notesNote = findViewById(R.id.enterNote);
        saveBtn = findViewById(R.id.saveNote);

        notes = new Notes();

        try{
            notes = (Notes) getIntent().getSerializableExtra("notes");
            notesTitle.setText(notes.getTitle());
            notesNote.setText(notes.getNotes());
            isOldNote = true;
        }catch (Exception e){
            e.printStackTrace();
        }


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nTitle = notesTitle.getText().toString();
                String nNote = notesNote.getText().toString();

                SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM YYYY HH:mm");
                Date date = new Date();

                if(!isOldNote){
                    notes = new Notes();
                }

                notes.setTitle(nTitle);
                notes.setNotes(nNote);
                notes.setDate(formatter.format(date));

                Intent intent = new Intent();
                intent.putExtra("notes", notes);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}