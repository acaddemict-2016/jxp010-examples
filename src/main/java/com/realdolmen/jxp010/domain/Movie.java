package com.realdolmen.jxp010.domain;

public class Movie {
    private String title;
    private String format;
    private String type;

    /**
     * Meh...
     */
    public Movie() {
    }

    public Movie(String title, String format, String type) {
        this.title = title;
        this.format = format;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", format='" + format + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
