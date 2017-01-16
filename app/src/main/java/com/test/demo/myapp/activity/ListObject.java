package com.test.demo.myapp.activity;

public class ListObject {
    private Type type;
    private String text;
    private String url;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    enum Type{
        HEADER, ITEM
    }

}
