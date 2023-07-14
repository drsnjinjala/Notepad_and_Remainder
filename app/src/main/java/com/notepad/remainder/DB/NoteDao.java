package com.notepad.remainder.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;

@Dao
@SuppressWarnings("ALL")
public interface NoteDao {
    @Query("SELECT * FROM notepad order by note_id desc")
    List<Note> getAll();

    @Insert
    void insert(Note task);

    @Delete
    void delete(Note task);

    @Update
    void update(Note task);

    @Query("SELECT * FROM notepad WHERE note_title = :userName")
    boolean isDataExist(String userName);

    @Query("SELECT * FROM notepad ORDER BY note_id DESC LIMIT 1")
    Note getLastRecord();


/**
    athor calss  Query


    @Query("SELECT * FROM Notification_List ORDER BY time DESC LIMIT 1")
    NotificationModel getLastEntry();

    @Query("SELECT * FROM Notification_List ORDER BY 1 DESC LIMIT 1")
    NotificationModel getLastMgs();

    @Query("SELECT * FROM Notification_List WHERE notification_id = :id ORDER BY text DESC LIMIT 1")
    NotificationModel getLastEntryById(int id);


    @Query("UPDATE Notification_List SET mgs_list=:text WHERE notification_id = :id")
    void update(String text, int id);

    */

}
