package com.notepad.remainder.Activity;


import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import static com.notepad.remainder.Utility.Utility.TASK_Description;
import static com.notepad.remainder.Utility.Utility.TASK_ID;
import static com.notepad.remainder.Utility.Utility.TASK_TIME;
import static com.notepad.remainder.Utility.Utility.TASK_TITLE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.notepad.remainder.Adapter.ColorListAdapter;
import com.notepad.remainder.DB.Converters;
import com.notepad.remainder.DB.DatabaseHelper;
import com.notepad.remainder.DB.Note;
import com.notepad.remainder.Picker.DatePickerFragment;
import com.notepad.remainder.Picker.TimePickerFragment;
import com.notepad.remainder.Utility.Utility;
import com.notepad.remainder.databinding.ActivityNewNoteBinding;
import com.notepad.remainder.receiver.AlarmReceiver;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@SuppressWarnings("all")
/*https://dribbble.com/shots/11875872-A-simple-and-lightweight-note-app*/

public class NewNoteActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    ActivityNewNoteBinding binding;
    final String TAG = "NewNoteActivity";
    private String mDefaultColor = "#FFFFFF";
    boolean tvColor = false;
    int SELECT_PICTURE = 200;
    int PICK_IMAGE_MULTIPLE = 1;
    private String Image_attached = "";
    Note task;
    private Calendar calendar;
    ArrayList<Uri> mArrayUri;
    ArrayList<String> imgArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utility.StutasBar(this);
        binding = ActivityNewNoteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        SetRecyclview();
        calendar = Calendar.getInstance();

        mArrayUri = new ArrayList<Uri>();
        imgArray = new ArrayList<String>();


        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this);

        binding.btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker();
            }
        });


        binding.btnDone.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if (binding.noteTitleEditText.getText().toString().equals("")) {
                    Toast.makeText(NewNoteActivity.this, "Title can not be empty..", Toast.LENGTH_SHORT).show();
                } else if (binding.noteBodyEditText.getText().toString().equals("")) {
                    Toast.makeText(NewNoteActivity.this, "Description can not be empty..", Toast.LENGTH_SHORT).show();
                } else {
                    DateFormat df = new SimpleDateFormat("h:mm a ,EEE  d MMM yy");
                    String date = df.format(Calendar.getInstance().getTime());
                    if (DatabaseHelper.getInstance(NewNoteActivity.this).noteDao().isDataExist(binding.noteTitleEditText.getText().toString())) {
                        Toast.makeText(NewNoteActivity.this, "Data already exited", Toast.LENGTH_SHORT).show();
                    } else {
                        binding.noteTitleEditText.requestFocus();
                        hideKeyboard(NewNoteActivity.this);
                        databaseHelper.noteDao().insert(new Note(binding.noteTitleEditText.getText().toString(), binding.noteBodyEditText.getText().toString(), mDefaultColor, DateFormat.getDateInstance(Utility.DATE_FORMAT).format(calendar.getTime()) + " " + Utility.getTime(Utility.TIME_24_FORMAT, calendar), tvColor, Converters.fromArrayList(imgArray)));
                        binding.noteTitleEditText.setText("");
                        binding.noteBodyEditText.setText("");
                        Image_attached = "";
                        binding.cardView.setBackgroundColor(Color.parseColor("#000000"));
                        binding.colorSeekBar.setVisibility(GONE);
                        //
                        Toast.makeText(NewNoteActivity.this, "Data Successfully Saved", Toast.LENGTH_SHORT).show();
                        setAlarm(databaseHelper.noteDao().getLastRecord());
                        //  finish();
                    }
                }
            }
        });

        binding.selectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChooser();
            }
        });

        binding.seekbarRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(NewNoteActivity.this);
                if (binding.colorSeekBar.getVisibility() == GONE) {
                    binding.colorSeekBar.setVisibility(VISIBLE);
                    binding.ArrowTop.setVisibility(VISIBLE);
                    binding.ArrowBottom.setVisibility(GONE);
                } else {
                    binding.colorSeekBar.setVisibility(GONE);
                    binding.ArrowTop.setVisibility(View.GONE);
                    binding.ArrowBottom.setVisibility(VISIBLE);
                }
            }
        });
    }

    public void setAlarm(Note lastRecord) {
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra(TASK_ID, lastRecord.getNote_id());
        intent.putExtra(TASK_TITLE, lastRecord.getTitle());
        intent.putExtra(TASK_Description, lastRecord.getDes());
        intent.putExtra(TASK_TIME, calendar.getTimeInMillis());
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getBroadcast(this, lastRecord.getNote_id(), intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        if (calendar.after(Calendar.getInstance())) {
            Log.d(TAG, "setAlarm: in alaaaarrrrrmmmmm");
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis()/*-30*60000*/, pendingIntent);
        } else Toast.makeText(this, "The time you select has expired", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onBackPressed() {
        Utility.GotoBack(this);
    }

    private void openDatePicker() {
        DialogFragment datePicker = new DatePickerFragment(calendar);
        datePicker.show(getSupportFragmentManager(), "date picker");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

    void imageChooser() {
        // initialising intent
        Intent intent = new Intent();

        // setting type to select to be image
        intent.setType("image/*");

        // allowing multiple image to be selected
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);
        /*
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.EXTRA_ALLOW_MULTIPLE);
        startActivityForResult(Intent.createChooser(intent, "select a picture"), SELECT_PICTURE);
*/
    }

    private void SetRecyclview() {
        binding.colorrecyclerView.setHasFixedSize(true);
        binding.colorrecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        ColorListAdapter adapter = new ColorListAdapter(this, new ColorListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int item, String color, boolean b) {
                binding.cardView.setBackgroundColor(Color.parseColor(color));
                mDefaultColor = color;
                tvColor = b;
            }
        });
        binding.colorrecyclerView.setAdapter(adapter);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // When an Image is picked
        if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == RESULT_OK && null != data) {
            imgArray.clear();
            if (data.getClipData() != null) {
                ClipData mClipData = data.getClipData();
                int cout = data.getClipData().getItemCount();
                for (int i = 0; i < cout; i++) {
                    // adding imageuri in array
                    Uri imageurl = data.getClipData().getItemAt(i).getUri();
                    //   mArrayUri.add(imageurl);
                    imgArray.add(imageurl.toString());
                }
                // setting 1st selected image into image switcher
            } else {
                Uri imageurl = data.getData();
                imgArray.add(imageurl.toString());
                //    mArrayUri.add(imageurl);
            }
        } else {
            // show this if no image is selected
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }

    /*    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_PICTURE) {
            if (resultCode == RESULT_OK) {
                Uri selectedImageUri = data.getData();
                if (!selectedImageUri.toString().isEmpty()) {
                    File bitmapFile = new File(Environment.getExternalStorageDirectory() + "/" + selectedImageUri);
                    Bitmap bitmap = BitmapFactory.decodeFile(bitmapFile.toString());

                    binding.imageView.setImageURI(selectedImageUri);
                    Image_attached = selectedImageUri.toString();
                } else {
                    Toast.makeText(this, "Image not Selected Perfectly try again", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }*/

    private void openTimePicker() {
        DialogFragment timePicker = new TimePickerFragment(calendar);
        timePicker.show(getSupportFragmentManager(), "time picker");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        calendar.set(Calendar.YEAR, i);
        calendar.set(Calendar.MONTH, i1);
        calendar.set(Calendar.DAY_OF_MONTH, i2);
        String date = DateFormat.getDateInstance(Utility.DATE_FORMAT).format(calendar.getTime());
        binding.TvDate.setText(date);

        Log.d(TAG, "onDateSet: " + date);
        openTimePicker();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        calendar.set(Calendar.HOUR_OF_DAY, i);
        calendar.set(Calendar.MINUTE, i1);
        calendar.set(Calendar.SECOND, 0);
        String time = Utility.getTimeFormated(calendar, this);
        binding.TvTime.setText(time);
        Log.d(TAG, "onTimeSet: " + time);

      /*  addNewTask();
        updateTextViewReminder();*/
    }


}
