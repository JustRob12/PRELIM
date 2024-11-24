package com.activity.observer;

import com.activity.interfaces.Activity;

public interface ActivityObserver {
    void onActivityAdded(Activity activity);
    void onActivityRemoved(Activity activity);
    void onActivityUpdated(Activity activity);
}
