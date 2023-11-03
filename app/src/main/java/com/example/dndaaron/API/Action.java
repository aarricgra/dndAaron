package com.example.dndaaron.API;

public class Action {
    String name;
    String desc;

    public Action(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public Action() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
