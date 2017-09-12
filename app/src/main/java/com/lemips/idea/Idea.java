package com.lemips.idea;

/**
 * Created by desk86 on 9/13/2017.
 */

public class Idea {
    private int id;
    private String title;
    private String content;

    public Idea(){}

    public Idea(String title, String content){
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
