package com.activity.impl;

import com.activity.interfaces.Activity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Webinar implements Activity {
    private String title;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String venue; // Platform/URL
    private String organizer;
    private int maxParticipants;
    private String platformLink;
    private String[] technicalRequirements;
    private boolean recordingAvailable;

    public Webinar() {
    }

    public Webinar(String title, String description, LocalDateTime startDateTime,
                  LocalDateTime endDateTime, String venue, String organizer,
                  int maxParticipants, String platformLink, String[] technicalRequirements,
                  boolean recordingAvailable) {
        this.title = title;
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.venue = venue;
        this.organizer = organizer;
        this.maxParticipants = maxParticipants;
        this.platformLink = platformLink;
        this.technicalRequirements = technicalRequirements;
        this.recordingAvailable = recordingAvailable;
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

    public String getPlatformLink() { return platformLink; }

    public void setPlatformLink(String platformLink) { this.platformLink = platformLink; }

    public String[] getTechnicalRequirements() { return technicalRequirements; }

    public void setTechnicalRequirements(String[] technicalRequirements) { this.technicalRequirements = technicalRequirements; }

    public boolean isRecordingAvailable() { return recordingAvailable; }

    public void setRecordingAvailable(boolean recordingAvailable) { this.recordingAvailable = recordingAvailable; }

    @Override
    public String getType() { return "WEBINAR"; }

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
                platformLink,
                String.join(",", technicalRequirements),
                String.valueOf(recordingAvailable)
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
        this.platformLink = parts[8];
        this.technicalRequirements = parts[9].split(",");
        this.recordingAvailable = Boolean.parseBoolean(parts[10]);
    }
}
