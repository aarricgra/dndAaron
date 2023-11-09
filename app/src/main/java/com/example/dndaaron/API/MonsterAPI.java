package com.example.dndaaron.API;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MonsterAPI {
    private final String BASE_URL = "https://www.dnd5eapi.co";

    public ArrayList<Monster> getMonsters() {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("api")
                .appendPath("monsters")
                .build();
        String url = builtUri.toString();
        return doCall(url);
    }

    Monster getMonstersInfo(String link) {
        String[] parts = link.split("/");
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath(parts[1])
                .appendPath(parts[2])
                .appendPath(parts[3])
                .build();
        String url = builtUri.toString();
        return doCallMonster(url);
    }

    private ArrayList<Monster> doCall(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);

            return procesMonsters(JsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private Monster doCallMonster(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);
            return procesMonstersInfo(JsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Monster> procesMonsters(String jsonResponse) {
        ArrayList<Monster> monsters = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonMonstersList = data.getJSONArray("results");
            for (int i = 0; i < jsonMonstersList.length(); i++) {
                JSONObject jsonMonster = jsonMonstersList.getJSONObject(i);

                Monster monster = new Monster();


                monster.setImg(jsonMonster.getString("url"));
                monsters.add(monster);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i=0;i<monsters.size();i++){
            monsters.set(i,getMonstersInfo(monsters.get(i).getImg()));
        }
        return monsters;
    }



    private Monster procesMonstersInfo(String jsonResponse) {
        Monster monster= new Monster();
        try {
            JSONObject data = new JSONObject(jsonResponse);

            // Obtener info general
            monster.setName(data.getString("name"));
            if (data.has("image")) {
                monster.setImg(BASE_URL+data.getString("image"));
            }
            monster.setSize(data.getString("size"));
            monster.setHp(data.getInt("hit_points"));

            // Obtener Atributos
            monster.setCon(data.getInt("constitution"));
            monster.setStr(data.getInt("strength"));
            monster.setDex(data.getInt("dexterity"));
            monster.setInte(data.getInt("intelligence"));
            monster.setWis(data.getInt("wisdom"));
            monster.setChari(data.getInt("charisma"));

            // Obtener la "armor_class"
            JSONArray armorClassArray = data.getJSONArray("armor_class");
            if (armorClassArray.length() > 0) {
                JSONObject armorClassObject = armorClassArray.getJSONObject(0);
                int armorClass = armorClassObject.getInt("value");
                monster.setAc(armorClass);
            }
        } catch (Exception e) {
            Log.d("aaaa",e.getMessage());
        }
        return monster;
    }
}
