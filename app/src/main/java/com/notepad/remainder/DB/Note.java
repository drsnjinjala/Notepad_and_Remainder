package com.notepad.remainder.DB;

import android.net.Uri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "notepad")

public class Note {
    @PrimaryKey(autoGenerate = true)
    private int note_id;

    @ColumnInfo(name = "note_des")
    private String des;
    @ColumnInfo(name = "note_title")
    private String title;

    @ColumnInfo(name = "note_colorCode")
    private String colorCode;

    @ColumnInfo(name = "note_date_time")
    private String dataAndTime;

    public Boolean getTvColor() {
        return tvColor;
    }

    public void setTvColor(Boolean tvColor) {
        this.tvColor = tvColor;
    }

    @ColumnInfo(name = "tv_color")
    private Boolean tvColor;

    @ColumnInfo(name = "mylist_array")
    private String myImageList;
    
    public String getMyImageList() {
        return myImageList;
    }

    public void setMyImageList(String myImageList) {
        this.myImageList = myImageList;
    }

    public String getDataAndTime() {
        return dataAndTime;
    }


    public void setDataAndTime(String dataAndTime) {
        this.dataAndTime = dataAndTime;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public Note(String title, String des, String colorCode, String dataAndTime, Boolean tvColor, String myImageList) {
        this.des = des;
        this.colorCode = colorCode;
        this.dataAndTime = dataAndTime;
        this.title = title;
        this.tvColor = tvColor;
        this.myImageList = myImageList;

    }

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Note() {
    }
}
