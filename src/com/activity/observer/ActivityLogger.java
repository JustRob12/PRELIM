package com.activity.observer;

import com.activity.interfaces.Activity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class ActivityLogger implements ActivityObserver {
    private static final String LOG_FILE = "activity_log.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void onActivityAdded(Activity activity) {
        logActivity("ADDED", activity);
    }

    @Override
    public void onActivityRemoved(Activity activity) {
        logActivity("REMOVED", activity);
    }

    @Override
    public void onActivityUpdated(Activity activity) {
        logActivity("UPDATED", activity);
    }

    private void logActivity(String action, Activity activity) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logMessage = String.format("[%s] %s - %s: %s (Type: %s)",
            timestamp, action, activity.getTitle(), activity.getDescription(), activity.getType());
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println(logMessage);
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
