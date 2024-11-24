package com.activity.manager;

import com.activity.factory.ActivityFactory;
import com.activity.factory.DefaultActivityFactory;
import com.activity.interfaces.Activity;
import com.activity.iterator.ActivityCollection;
import com.activity.iterator.ActivityIterator;
import com.activity.observer.ActivityObserver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityManager {
    private ActivityCollection activities;
    private ActivityFactory factory;
    private List<ActivityObserver> observers;
    private static final String DATA_FILE = "activities.txt";

    public ActivityManager() {
        this.activities = new ActivityCollection();
        this.factory = new DefaultActivityFactory();
        this.observers = new ArrayList<>();
        loadActivities();
    }

    public void addObserver(ActivityObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ActivityObserver observer) {
        observers.remove(observer);
    }

    private void notifyActivityAdded(Activity activity) {
        for (ActivityObserver observer : observers) {
            observer.onActivityAdded(activity);
        }
    }

    private void notifyActivityRemoved(Activity activity) {
        for (ActivityObserver observer : observers) {
            observer.onActivityRemoved(activity);
        }
    }

    private void notifyActivityUpdated(Activity activity) {
        for (ActivityObserver observer : observers) {
            observer.onActivityUpdated(activity);
        }
    }

    public void addActivity(Activity activity) {
        activities.addActivity(activity);
        saveActivities();
        notifyActivityAdded(activity);
    }

    public void removeActivity(Activity activity) {
        activities.removeActivity(activity);
        saveActivities();
        notifyActivityRemoved(activity);
    }

    public void updateActivity(Activity oldActivity, Activity updatedActivity) {
        ActivityIterator iterator = activities.iterator();
        while (iterator.hasNext()) {
            Activity current = iterator.next();
            if (current.getTitle().equals(oldActivity.getTitle())) {
                // Update the activity
                activities.removeActivity(current);
                activities.addActivity(updatedActivity);
                saveActivities();
                notifyActivityUpdated(updatedActivity);
                return;
            }
        }
        throw new IllegalArgumentException("Activity not found: " + oldActivity.getTitle());
    }

    public Activity findActivityByTitle(String title) {
        ActivityIterator iterator = activities.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity.getTitle().equalsIgnoreCase(title)) {
                return activity;
            }
        }
        return null;
    }

    public List<Activity> findActivitiesByType(String type) {
        List<Activity> result = new ArrayList<>();
        ActivityIterator iterator = activities.iterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity.getType().equalsIgnoreCase(type)) {
                result.add(activity);
            }
        }
        return result;
    }

    private void loadActivities() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Activity activity = factory.createActivityFromFileString(line);
                activities.addActivity(activity);
            }
        } catch (IOException e) {
            System.err.println("Error loading activities: " + e.getMessage());
        }
    }

    private void saveActivities() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            ActivityIterator iterator = activities.iterator();
            while (iterator.hasNext()) {
                Activity activity = iterator.next();
                writer.write(activity.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving activities: " + e.getMessage());
        }
    }

    public ActivityIterator getIterator() {
        return activities.iterator();
    }
}
