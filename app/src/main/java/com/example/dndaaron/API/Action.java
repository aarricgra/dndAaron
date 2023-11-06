package com.example.dndaaron.API;

public class Action {
    String name;
    String desc;

    String type;

    public Action(String name, String desc,String type) {
        this.name = name;
        this.desc = desc;
    }

    public Action() {
    }

    @Override
    public String toString() {
        return "Action{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", type='" + type + '\'' +
                '}';
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
