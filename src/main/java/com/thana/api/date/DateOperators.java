package com.thana.api.date;

public enum DateOperators {
    PASS("pass"),
    NONE("");

    private final String name;

    DateOperators(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static DateOperators getOperatorFromName(String name) {
        for (DateOperators operator : values()) {
            if (operator.getName().equalsIgnoreCase(name)) {
                return operator;
            }
        }
        return NONE;
    }
}
