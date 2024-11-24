package com.activity.iterator;

import com.activity.interfaces.Activity;
import java.util.ArrayList;
import java.util.List;

public class ActivityCollection {
    private List<Activity> activities;

    public ActivityCollection() {
        this.activities = new ArrayList<>();
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public ActivityIterator iterator() {
        return new ActivityListIterator(activities);
    }

    private class ActivityListIterator implements ActivityIterator {
        private List<Activity> activities;
        private int position;

        public ActivityListIterator(List<Activity> activities) {
            this.activities = activities;
            this.position = 0;
        }

        @Override
        public boolean hasNext() {
            return position < activities.size();
        }

        @Override
        public Activity next() {
            if (hasNext()) {
                return activities.get(position++);
            }
            return null;
        }

        @Override
        public void remove() {
            if (position > 0) {
                activities.remove(--position);
            }
        }
    }
}
