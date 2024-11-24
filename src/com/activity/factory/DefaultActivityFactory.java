package com.activity.factory;

import com.activity.impl.*;
import com.activity.interfaces.Activity;

public class DefaultActivityFactory implements ActivityFactory {
    @Override
    public Activity createActivity(String type) {
        switch (type.toUpperCase()) {
            case "MEETING":
                return new Meeting();
            case "SEMINAR_WORKSHOP":
                return new SeminarWorkshop();
            case "WEBINAR":
                return new Webinar();
            case "TRAINING":
                return new Training();
            case "COMMUNITY_OUTREACH":
                return new CommunityOutreach();
            case "EXHIBIT":
                return new Exhibit();
            default:
                throw new IllegalArgumentException("Unknown activity type: " + type);
        }
    }

    @Override
    public Activity createActivityFromFileString(String data) {
        String type = data.split("\\|")[0];
        Activity activity = createActivity(type);
        activity.fromFileString(data);
        return activity;
    }
}
