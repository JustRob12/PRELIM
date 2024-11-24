package com.activity.interfaces;

import java.time.LocalDateTime;

public interface Activity {
    String getTitle();
    void setTitle(String title);
    String getDescription();
    void setDescription(String description);
    LocalDateTime getStartDateTime();
    void setStartDateTime(LocalDateTime startDateTime);
    LocalDateTime getEndDateTime();
    void setEndDateTime(LocalDateTime endDateTime);
    String getVenue();
    void setVenue(String venue);
    String getOrganizer();
    void setOrganizer(String organizer);
    int getMaxParticipants();
    void setMaxParticipants(int maxParticipants);
    String getType();
    String toFileString();
    void fromFileString(String data);
}
