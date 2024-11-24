package com.activity.impl;

import com.activity.interfaces.Activity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Training implements Activity {
    private String title;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String venue;
    private String organizer;
    private int maxParticipants;
    private String[] trainers;
    private String[] prerequisites;
    private String[] learningObjectives;
    private boolean certificationOffered;

    public Training() {
    }

    public Training(String title, String description, LocalDateTime startDateTime,
                   LocalDateTime endDateTime, String venue, String organizer,
                   int maxParticipants, String[] trainers, String[] prerequisites,
                   String[] learningObjectives, boolean certificationOffered) {
        this.title = title;
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.venue = venue;
        this.organizer = organizer;
        this.maxParticipants = maxParticipants;
        this.trainers = trainers;
        this.prerequisites = prerequisites;
        this.learningObjectives = learningObjectives;
        this.certificationOffered = certificationOffered;
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

    public String[] getTrainers() { return trainers; }

    public void setTrainers(String[] trainers) { this.trainers = trainers; }

    public String[] getPrerequisites() { return prerequisites; }

    public void setPrerequisites(String[] prerequisites) { this.prerequisites = prerequisites; }

    public String[] getLearningObjectives() { return learningObjectives; }

    public void setLearningObjectives(String[] learningObjectives) { this.learningObjectives = learningObjectives; }

    public boolean isCertificationOffered() { return certificationOffered; }

    public void setCertificationOffered(boolean certificationOffered) { this.certificationOffered = certificationOffered; }

    @Override
    public String getType() { return "TRAINING"; }

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
                String.join(",", trainers),
                String.join(",", prerequisites),
                String.join(",", learningObjectives),
                String.valueOf(certificationOffered)
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
        this.trainers = parts[8].split(",");
        this.prerequisites = parts[9].split(",");
        this.learningObjectives = parts[10].split(",");
        this.certificationOffered = Boolean.parseBoolean(parts[11]);
    }
}
