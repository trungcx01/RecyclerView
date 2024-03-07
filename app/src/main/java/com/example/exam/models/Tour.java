package com.example.exam.models;

public class Tour {
    private int imgTransport;
    private String name;
    private String time;

    public Tour() {
    }

    public Tour(int imgTransport, String name, String time) {
        this.imgTransport = imgTransport;
        this.name = name;
        this.time = time;
    }

    public int getImgTransport() {
        return imgTransport;
    }

    public void setImgTransport(int imgTransport) {
        this.imgTransport = imgTransport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
