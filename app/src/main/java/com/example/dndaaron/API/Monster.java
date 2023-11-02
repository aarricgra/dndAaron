package com.example.dndaaron.API;

public class Monster {
    String name;
    String size;
    int ac;
    int hp;
    int speed;
    String img;

    public Monster() {
    }

    public Monster(String name, String size, int ac, int hp, int speed, String img) {
        this.name = name;
        this.size = size;
        this.ac = ac;
        this.hp = hp;
        this.speed = speed;
        this.img = img;
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
}
