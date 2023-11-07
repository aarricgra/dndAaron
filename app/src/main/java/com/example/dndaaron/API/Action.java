package com.example.dndaaron.API;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Action implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int idAction;
    String name;
    String desc;

    String type;
    int idMonster;

    public Action(String name, String desc,String type,int idMonster) {
        this.name = name;
        this.desc = desc;
        this.idMonster=idMonster;
    }

    public Action() {
    }

    @Override
    public String toString() {
        return "Action{" +
                "idAction=" + idAction +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", type='" + type + '\'' +
                ", idMonster=" + idMonster +
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

    public int getIdAction() {
        return idAction;
    }

    public void setIdAction(int idAction) {
        this.idAction = idAction;
    }

    public int getIdMonster() {
        return idMonster;
    }

    public void setIdMonster(int idMonster) {
        this.idMonster = idMonster;
    }
}
