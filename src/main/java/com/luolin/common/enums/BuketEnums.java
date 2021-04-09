package com.luolin.common.enums;

public enum  BuketEnums {
    /**
     * 桶名
     */
    A2x3Z("a2x3z");



    BuketEnums(String name) {
        this.name = name;
    }
    private String name;

    public String getName() {
        return name;
    }
}