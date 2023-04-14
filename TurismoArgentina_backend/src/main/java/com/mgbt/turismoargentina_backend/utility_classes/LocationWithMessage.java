package com.mgbt.turismoargentina_backend.utility_classes;

import com.mgbt.turismoargentina_backend.model.entity.Location;

public class LocationWithMessage {
    private Location location;
    private String message;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
