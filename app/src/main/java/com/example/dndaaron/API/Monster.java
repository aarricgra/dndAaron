package com.example.dndaaron.API;

import java.util.ArrayList;

public class Monster {
    String name;
    String size;
    int ac;
    int hp;
    int speed;
    String img;

    int con,str,dex,inte,wis,chari;

    ArrayList<Action> actions;
    ArrayList<SpecialAbility> specialAbilities;

    public Monster() {
    }

    public Monster(String name, String size, int ac, int hp, int speed, String img, int con, int str, int dex, int inte, int wis, int chari, ArrayList<Action> actions, ArrayList<SpecialAbility> specialAbilities) {
        this.name = name;
        this.size = size;
        this.ac = ac;
        this.hp = hp;
        this.speed = speed;
        this.img = img;
        this.con = con;
        this.str = str;
        this.dex = dex;
        this.inte = inte;
        this.wis = wis;
        this.chari = chari;
        this.actions = actions;
        this.specialAbilities = specialAbilities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

    public ArrayList<SpecialAbility> getSpecialAbilities() {
        return specialAbilities;
    }

    public void setSpecialAbilities(ArrayList<SpecialAbility> specialAbilities) {
        this.specialAbilities = specialAbilities;
    }

    public int getCon() {
        return con;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getDex() {
        return dex;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public int getInte() {
        return inte;
    }

    public void setInte(int inte) {
        this.inte = inte;
    }

    public int getWis() {
        return wis;
    }

    public void setWis(int wis) {
        this.wis = wis;
    }

    public int getChari() {
        return chari;
    }

    public void setChari(int chari) {
        this.chari = chari;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", ac=" + ac +
                ", hp=" + hp +
                ", speed=" + speed +
                ", img='" + img + '\'' +
                ", con=" + con +
                ", str=" + str +
                ", dex=" + dex +
                ", inte=" + inte +
                ", wis=" + wis +
                ", chari=" + chari +
                ", actions=" + actions +
                ", specialAbilities=" + specialAbilities +
                '}';
    }
}
