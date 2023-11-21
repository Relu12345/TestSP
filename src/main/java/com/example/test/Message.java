package com.example.test;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Message {
    private Date date;
    private String message;
    private String house;

    public Message() {
    }

    public Message(@JsonProperty("date") Date date, @JsonProperty("message") String message, @JsonProperty("house") String house) {
        this.date = date;
        this.message = message;
        this.house = house;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getHouse() {
        return house;
    }

    public void print() {
        System.out.println("Message{" +
                "date=" + date +
                ", message='" + message + '\'' +
                ", house='" + house + '\'' +
                '}');
    }
}
