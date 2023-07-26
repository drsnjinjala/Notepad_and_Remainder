package com.notepad.remainder.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.allcast.apps.reciever.screen.mirroring.app.pojo.FolderModel;
import com.allcast.apps.reciever.screen.mirroring.app.pojo.MediaPojo;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class DataUtil {

    public static ArrayList<FolderModel> getVideoFolder(Context context) {
        ArrayList<FolderModel> videoFolderList = null;
        if (videoFolderList != null) {
            videoFolderList.clear();
        }
        videoFolderList = new ArrayList<>();
        Cursor query = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"bucket_display_name", "bucket_id"}, null, null, MediaStore.Video.Media.DATE_MODIFIED + " DESC");
        if (query != null) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            LinkedHashSet linkedHashSet2 = new LinkedHashSet();
            linkedHashSet.clear();
            linkedHashSet2.clear();
            while (query.moveToNext()) {
                linkedHashSet.add(query.getString(0));
                linkedHashSet2.add(query.getString(1));
            }
            query.close();
            ArrayList arrayList = new ArrayList(linkedHashSet);
            ArrayList arrayList2 = new ArrayList(linkedHashSet2);
            int count = 0;
            int fid;
            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i) != null) {
                    String name = arrayList.get(i).toString();
                    fid = Integer.parseInt(arrayList2.get(i).toString());
                    Cursor query2 = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_data"}, "bucket_display_name =?", new String[]{name}, null);
                    if (query2 != null) {
                        count = query2.getCount();
                    }
                    videoFolderList.add(new FolderModel(fid, name, count));
                }
            }
        }
        return videoFolderList;
    }

    public static ArrayList<FolderModel> getImageFolder(Context context) {
        ArrayList<FolderModel> folderList = null;
        if (folderList != null) {
            folderList.clear();
        }
        folderList = new ArrayList<>();
        Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"bucket_display_name", "bucket_id"}, null, null, MediaStore.Images.Media.DATE_MODIFIED + " DESC");
        if (query != null) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            LinkedHashSet linkedHashSet2 = new LinkedHashSet();
            linkedHashSet.clear();
            linkedHashSet2.clear();
            while (query.moveToNext()) {
                linkedHashSet.add(query.getString(0));
                linkedHashSet2.add(query.getString(1));
            }
            query.close();
            ArrayList arrayList = new ArrayList(linkedHashSet);
            ArrayList arrayList2 = new ArrayList(linkedHashSet2);
            int count = 0;
            int fid;
            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i) != null) {
                    String name = arrayList.get(i).toString();
                    fid = Integer.parseInt(arrayList2.get(i).toString());
                    Cursor query2 = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_data"}, "bucket_display_name =?", new String[]{name}, null);
                    if (query2 != null) {
                        count = query2.getCount();
                    }
                    folderList.add(new FolderModel(fid, name, count));
                }
            }
        }
        return folderList;
    }


    public static ArrayList<MediaPojo> getAllImage(Context context) {
        ArrayList<MediaPojo> imageList = new ArrayList<>();
        String[] projection = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
        while (cursor.moveToNext()) {
            MediaPojo ImageModel = new MediaPojo(cursor.getInt(0),cursor.getString(1));
            imageList.add(ImageModel);
        }
        cursor.close();
        return imageList;
    }

    public static Uri getUri(ContentResolver cr, String path){
        Uri mediaUri = MediaStore.Files.getContentUri("external");
        Cursor ca = cr.query(mediaUri, new String[] { MediaStore.MediaColumns._ID }, MediaStore.MediaColumns.DATA + "=?", new String[] {path}, null);
        if (ca != null && ca.moveToFirst()) {
            int id = ca.getInt(0);
            ca.close();
            return  MediaStore.Files.getContentUri("external",id);
        }
        if(ca != null) {
            ca.close();
        }
        return null;
    }

    public static ArrayList<MediaPojo> getVideos(Context context,String bucket) {
        ArrayList<MediaPojo> videoList = new ArrayList<>();
        String[] projection = {MediaStore.Video.Media._ID,MediaStore.Video.Media.DATA};
        Cursor cursor = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, "bucket_display_name =?", new String[]{bucket}, MediaStore.Video.Media.DATE_MODIFIED + " DESC");
        while (cursor.moveToNext()) {
            MediaPojo ImageModel = new MediaPojo(cursor.getInt(0),cursor.getString(1));
            videoList.add(ImageModel);
        }
        cursor.close();
        return videoList;
    }

    public static ArrayList<MediaPojo> getImages(Context context,String bucket) {
        ArrayList<MediaPojo> videoList = new ArrayList<>();
        String[] projection = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, "bucket_display_name =?", new String[]{bucket}, MediaStore.Images.Media.DATE_MODIFIED + " DESC");
        Log.e("count", "getImages: " + cursor.getCount() );
        while (cursor.moveToNext()) {
            MediaPojo ImageModel = new MediaPojo(cursor.getInt(0),cursor.getString(1));
            videoList.add(ImageModel);
        }
        cursor.close();
        return videoList;
    }

    public static ArrayList<MediaPojo> getAllVideo(Context context) {
        ArrayList<MediaPojo> videoList = new ArrayList<>();
        String[] projection = {MediaStore.Video.Media._ID,MediaStore.Video.Media.DATA};
        Cursor cursor = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
        while (cursor.moveToNext()) {
            MediaPojo ImageModel = new MediaPojo(cursor.getInt(0),cursor.getString(1));
            videoList.add(ImageModel);
        }
        cursor.close();

        return videoList;
    }
}
