package com.cristianortega.excercise.application.domain;

public class Holiday {

    private String date;
    private String title;
    private String type;
    private boolean inalienable;
    private String extra;

    public Holiday() {
        super();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isInalienable() {
        return inalienable;
    }

    public void setInalienable(boolean inalienable) {
        this.inalienable = inalienable;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
