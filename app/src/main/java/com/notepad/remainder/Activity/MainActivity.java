package com.notepad.remainder.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.notepad.remainder.Adapter.NoteListAdapter;
import com.notepad.remainder.DB.DatabaseHelper;
import com.notepad.remainder.DB.Note;
import com.notepad.remainder.Utility.Utility;
import com.notepad.remainder.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayList<Note> noteArrayList;
    NoteListAdapter adapter;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utility.StutasBar(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Log.d("lifecycle", "onCreate invoked");
        db = DatabaseHelper.getInstance(this);

        binding.newNotesAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.GotoNext(MainActivity.this, NewNoteActivity.class);
            }
        });

        binding.Tvsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setFilterData(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setFilterData(String text) {
        ArrayList<Note> newList = new ArrayList<>();
        for (Note note : (ArrayList<Note>) db.noteDao().getAll()) {
            if (note.getTitle().toLowerCase().contains(text.toLowerCase())) {
                newList.add(note);
            }
        }
        adapter.setFilter(newList);
    }

    private void SetRecyclview() {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new NoteListAdapter(noteArrayList, this, new NoteListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Note note, int po) {
                Toast.makeText(MainActivity.this, "Data Update ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDelete(Note note, int po) {
                db.noteDao().delete(note);
                noteArrayList.remove(po);
                adapter.notifyDataSetChanged();
                DatanotFound();
                Toast.makeText(MainActivity.this, "Notes Deleted Succesfully", Toast.LENGTH_SHORT).show();
            }
        });
        binding.recyclerView.setAdapter(adapter);
    }

    private void DatanotFound() {
        if (noteArrayList.size() == 0) {
            binding.recyclerView.setVisibility(View.GONE);
            binding.dataNotFound.setVisibility(View.VISIBLE);
        } else binding.recyclerView.setVisibility(View.VISIBLE);
        binding.dataNotFound.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifecycle", "onStart invoked");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //    binding.Tvsearch.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        noteArrayList = (ArrayList<Note>) db.noteDao().getAll();
        SetRecyclview();
        DatanotFound();

        Log.d("lifecycle", "onResume invoked");
    }
}