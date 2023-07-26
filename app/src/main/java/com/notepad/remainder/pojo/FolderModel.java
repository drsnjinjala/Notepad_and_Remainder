package com.notepad.remainder.pojo;


public class FolderModel {

    int id;
    String name;
    String path;
    int count;

    public FolderModel(int id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}
