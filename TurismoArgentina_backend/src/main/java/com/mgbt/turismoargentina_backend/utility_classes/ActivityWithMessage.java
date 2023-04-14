package com.mgbt.turismoargentina_backend.utility_classes;

import com.mgbt.turismoargentina_backend.model.entity.Activity;

public class ActivityWithMessage {
    private Activity activity;
    private String message;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
