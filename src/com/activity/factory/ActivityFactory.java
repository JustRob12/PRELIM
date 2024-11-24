package com.activity.factory;

import com.activity.interfaces.Activity;

public interface ActivityFactory {
    Activity createActivity(String type);
    Activity createActivityFromFileString(String data);
}
