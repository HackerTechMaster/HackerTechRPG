package com.github.hackertechmaster.hackertechrpg.objects;

public enum Area {
    CYBERCAFE("健民网吧"),
    DORMITORY("三里宿舍"),
    GATE("同大门口"),
    SUPERMARKET("黎明超市"),
    LIBRARY("湛星图书馆");

    private final String areaName;
    Area(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaName() {
        return areaName;
    }
}
