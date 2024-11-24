package com.activity.impl;

import com.activity.interfaces.Activity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SeminarWorkshop implements Activity {
    private String title;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String venue;
    private String organizer;
    private int maxParticipants;
    private String[] speakers;
    private String[] workshopMaterials;
    private double registrationFee;

    public SeminarWorkshop() {
    }

    public SeminarWorkshop(String title, String description, LocalDateTime startDateTime,
                          LocalDateTime endDateTime, String venue, String organizer,
                          int maxParticipants, String[] speakers, String[] workshopMaterials,
                          double registrationFee) {
        this.title = title;
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.venue = venue;
        this.organizer = organizer;
        this.maxParticipants = maxParticipants;
        this.speakers = speakers;
        this.workshopMaterials = workshopMaterials;
        this.registrationFee = registrationFee;
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

    public String[] getSpeakers() { return speakers; }

    public void setSpeakers(String[] speakers) { this.speakers = speakers; }

    public String[] getWorkshopMaterials() { return workshopMaterials; }

    public void setWorkshopMaterials(String[] workshopMaterials) { this.workshopMaterials = workshopMaterials; }

    public double getRegistrationFee() { return registrationFee; }

    public void setRegistrationFee(double registrationFee) { this.registrationFee = registrationFee; }

    @Override
    public String getType() { return "SEMINAR_WORKSHOP"; }

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
                String.join(",", speakers),
                String.join(",", workshopMaterials),
                String.valueOf(registrationFee)
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
        this.speakers = parts[8].split(",");
        this.workshopMaterials = parts[9].split(",");
        this.registrationFee = Double.parseDouble(parts[10]);
    }
}
