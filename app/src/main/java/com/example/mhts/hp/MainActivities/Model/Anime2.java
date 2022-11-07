package com.example.mhts.hp.MainActivities.Model;

public class Anime2 {

    private String Announcement_title;
    private String Announcement_details;

    public Anime2() {
    }

    public Anime2(String announcement_title, String announcement_details) {
        Announcement_title = announcement_title;
        Announcement_details = announcement_details;
    }

    public String getAnnouncement_title() {
        return Announcement_title;
    }

    public String getAnnouncement_details() {
        return Announcement_details;
    }

    public void setAnnouncement_title(String announcement_title) {
        Announcement_title = announcement_title;
    }

    public void setAnnouncement_details(String announcement_details) {
        Announcement_details = announcement_details;
    }


}
