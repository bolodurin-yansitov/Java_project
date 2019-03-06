package com.example.prototipe.entities.enums;

public enum ReasonOfRequesting {
    HEATH((short) 1),
    FINANCIAL_SITUATION((short) 2),
    TICKET_HOME((short) 3),
    ANOTHER_REASON((short) 4);

    private short value;

    ReasonOfRequesting(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    public static ReasonOfRequesting parse(short id) {
        ReasonOfRequesting right = null; // Default
        for (ReasonOfRequesting item : ReasonOfRequesting.values()) {
            if (item.getValue() == id) {
                right = item;
                break;
            }
        }
        return right;
    }
}
