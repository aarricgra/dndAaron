package com.example.dndaaron.API;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DndAPI {
    private final String BASE_URL = "https://www.dnd5eapi.co";

    public ArrayList<Monster> getMonsters() {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("api/monsters")
                .build();
        String url = builtUri.toString();

        return doCall(url);
    }

    Monster getMonstersInfo(String link) {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath(link)
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
        }
        return null;
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
        for (Monster monster :monsters) {
            monster=getMonstersInfo(monster.img);
        }

        return monsters;
    }



    private Monster procesMonstersInfo(String jsonResponse) {
        Monster monster= new Monster();
        try {
            JSONObject data = new JSONObject(jsonResponse);

            // Obtener info general
            monster.setName(data.getString("name"));
            monster.setImg(data.getString("image"));
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
            JSONObject armorClassObject = data.getJSONObject("armor_class");
            int armorClass = armorClassObject.getInt("value");
            monster.setAc(armorClass);

            // Procesar las acciones si hay
            if (data.has("actions")) {
                JSONArray actionsArray = data.getJSONArray("actions");
                ArrayList<Action> actionsList = new ArrayList<>();
                for (int i = 0; i < actionsArray.length(); i++) {
                    JSONObject actionObject = actionsArray.getJSONObject(i);
                    Action action = new Action();
                    action.setName(actionObject.getString("name"));
                    action.setDesc(actionObject.getString("desc"));
                    actionsList.add(action);
                }
                monster.setActions(actionsList);
            }

            // Procesar las habilidades especiales si hay
            if (data.has("special_abilities")) {
                JSONArray specialAbilitiesArray = data.getJSONArray("special_abilities");
                ArrayList<SpecialAbility> specialAbilitiesList = new ArrayList<>();
                for (int i = 0; i < specialAbilitiesArray.length(); i++) {
                    JSONObject specialAbilityObject = specialAbilitiesArray.getJSONObject(i);
                    SpecialAbility specialAbility = new SpecialAbility();
                    specialAbility.setName(specialAbilityObject.getString("name"));
                    specialAbility.setDesc(specialAbilityObject.getString("desc"));
                    specialAbilitiesList.add(specialAbility);
                }
                monster.setSpecialAbilities(specialAbilitiesList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return monster;
    }
}
