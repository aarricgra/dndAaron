package com.example.dndaaron.API;

public class SpecialAbility {
    String name;
    String desc;

    public SpecialAbility(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public SpecialAbility() {
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
