package com.activity.iterator;

import com.activity.interfaces.Activity;

public interface ActivityIterator {
    boolean hasNext();
    Activity next();
    void remove();
}
