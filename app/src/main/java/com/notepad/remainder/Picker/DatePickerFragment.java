package com.notepad.remainder.Picker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {

    private Calendar calendar;

    //TODO how to make the date set on given date
    public DatePickerFragment() {
        calendar = Calendar.getInstance();
    }

    public DatePickerFragment(Calendar calendar) {
        this.calendar = calendar;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(requireActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
    }
}
