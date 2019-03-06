package com.example.prototipe.entities.enums;

public enum StatusOfRequest {
    SENT((short) 1),
    APPROVED_BY_HEADMAN((short) 2),
    APPROVED_BY_CHAIRMAN((short) 3);

    private short value;

    StatusOfRequest(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    public static StatusOfRequest parse(short id) {
        StatusOfRequest right = null; // Default
        for (StatusOfRequest item : StatusOfRequest.values()) {
            if (item.getValue() == id) {
                right = item;
                break;
            }
        }
        return right;
    }
}
