package com.example.prototipe.entities.enums;

public enum Role {
    STUDENT((short) 1),
    HEADMAN((short) 2),
    CAHIRMAN((short) 3);

    private short value;

    Role(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    public static Role parse(short id) {
        Role right = null; // Default
        for (Role item : Role.values()) {
            if (item.getValue() == id) {
                right = item;
                break;
            }
        }
        return right;
    }
}
