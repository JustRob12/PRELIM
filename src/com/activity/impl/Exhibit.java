package com.activity.impl;

import com.activity.interfaces.Activity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Exhibit implements Activity {
    private String title;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String venue;
    private String organizer;
    private int maxParticipants;
    private String theme;
    private String[] exhibitors;
    private String[] exhibits;
    private boolean isInteractive;
    private double entryFee;

    public Exhibit() {
    }

    public Exhibit(String title, String description, LocalDateTime startDateTime,
                  LocalDateTime endDateTime, String venue, String organizer,
                  int maxParticipants, String theme, String[] exhibitors,
                  String[] exhibits, boolean isInteractive, double entryFee) {
        this.title = title;
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.venue = venue;
        this.organizer = organizer;
        this.maxParticipants = maxParticipants;
        this.theme = theme;
        this.exhibitors = exhibitors;
        this.exhibits = exhibits;
        this.isInteractive = isInteractive;
        this.entryFee = entryFee;
    }

    @Override
    public String getTitle() { return title; }

    @Override
    public void setTitle(String title) { this.title = title; }

    @Override
    public String getDescription() { return description; }

    @Override
    public void setDescription(String description) { this.description = description; }

    @Override
    public LocalDateTime getStartDateTime() { return startDateTime; }

    @Override
    public void setStartDateTime(LocalDateTime startDateTime) { this.startDateTime = startDateTime; }

    @Override
    public LocalDateTime getEndDateTime() { return endDateTime; }

    @Override
    public void setEndDateTime(LocalDateTime endDateTime) { this.endDateTime = endDateTime; }

    @Override
    public String getVenue() { return venue; }

    @Override
    public void setVenue(String venue) { this.venue = venue; }

    @Override
    public String getOrganizer() { return organizer; }

    @Override
    public void setOrganizer(String organizer) { this.organizer = organizer; }

    @Override
    public int getMaxParticipants() { return maxParticipants; }

    @Override
    public void setMaxParticipants(int maxParticipants) { this.maxParticipants = maxParticipants; }

    public String getTheme() { return theme; }

    public void setTheme(String theme) { this.theme = theme; }

    public String[] getExhibitors() { return exhibitors; }

    public void setExhibitors(String[] exhibitors) { this.exhibitors = exhibitors; }

    public String[] getExhibits() { return exhibits; }

    public void setExhibits(String[] exhibits) { this.exhibits = exhibits; }

    public boolean isInteractive() { return isInteractive; }

    public void setInteractive(boolean interactive) { isInteractive = interactive; }

    public double getEntryFee() { return entryFee; }

    public void setEntryFee(double entryFee) { this.entryFee = entryFee; }

    @Override
    public String getType() { return "EXHIBIT"; }

    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return String.join("|",
                getType(),
                title,
                description,
                startDateTime.format(formatter),
                endDateTime.format(formatter),
                venue,
                organizer,
                String.valueOf(maxParticipants),
                theme,
                String.join(",", exhibitors),
                String.join(",", exhibits),
                String.valueOf(isInteractive),
                String.valueOf(entryFee)
        );
    }

    @Override
    public void fromFileString(String data) {
        String[] parts = data.split("\\|");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        
        this.title = parts[1];
        this.description = parts[2];
        this.startDateTime = LocalDateTime.parse(parts[3], formatter);
        this.endDateTime = LocalDateTime.parse(parts[4], formatter);
        this.venue = parts[5];
        this.organizer = parts[6];
        this.maxParticipants = Integer.parseInt(parts[7]);
        this.theme = parts[8];
        this.exhibitors = parts[9].split(",");
        this.exhibits = parts[10].split(",");
        this.isInteractive = Boolean.parseBoolean(parts[11]);
        this.entryFee = Double.parseDouble(parts[12]);
    }
}
