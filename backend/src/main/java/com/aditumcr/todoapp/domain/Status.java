package com.aditumcr.todoapp.domain;

import java.util.HashMap;
import java.util.Map;

public enum Status {
    PENDING, COMPLETED;

    private static final Map<String, Status> map = new HashMap<>();
    static {
        for (Status status : Status.values()) {
            map.put(status.name(), status);
        }
    }

    public static Status getByName(String name) {
        return map.get(name);
    }
}