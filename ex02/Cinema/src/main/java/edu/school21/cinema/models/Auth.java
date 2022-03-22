package edu.school21.cinema.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Auth {

    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd, yyyy");

    private LocalDateTime   dateTime;
    private String          ip;

    public Auth() {}

    public Auth(LocalDateTime dateTime, String ip) {
        this.dateTime = dateTime;
        this.ip = ip;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDate() {
        return dateTime.getMonth() + " " + dateTime.format(dateFormatter);
    }

    public String getTime() {
        return dateTime.format(timeFormatter);
    }

}
