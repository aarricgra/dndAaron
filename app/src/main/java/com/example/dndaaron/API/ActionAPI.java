package com.example.dndaaron.API;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class ActionAPI {
    private final String BASE_URL = "https://www.dnd5eapi.co";

    public ArrayList<AbilitiesActions> getActions() {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("api")
                .appendPath("monsters")
                .build();
        String url = builtUri.toString();
        return doCall(url);
    }

    void getActionsInfo(ArrayList<AbilitiesActions> actions, String link, int idMonster) {
        String[] parts = link.split("/");
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath(parts[1])
                .appendPath(parts[2])
                .appendPath(parts[3])
                .build();
        String url = builtUri.toString();
        doCallMonster(url,idMonster,actions);
    }

    private ArrayList<AbilitiesActions> doCall(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);

            return procesMonsters(JsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void doCallMonster(String url, int idMonster, ArrayList<AbilitiesActions> actions) {
        try {
            String JsonResponse = HttpUtils.get(url);
            procesActionsInfo(JsonResponse,idMonster,actions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<AbilitiesActions> procesMonsters(String jsonResponse) {
        ArrayList<AbilitiesActions> actions = new ArrayList<>();
        ArrayList<String> urls = new ArrayList<>();
        try {
            JSONObject monster = new JSONObject(jsonResponse);
            JSONArray jsonMonstersList = monster.getJSONArray("results");
            for (int i = 0; i < 10/*jsonMonstersList.length()*/; i++) {
                JSONObject jsonMonster = jsonMonstersList.getJSONObject(i);

                AbilitiesActions action = new AbilitiesActions();

                urls.add(jsonMonster.getString("url"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i=0;i<urls.size();i++){
            getActionsInfo(actions,urls.get(i),i);
        }
        return actions;
    }



    private void procesActionsInfo(String jsonResponse, int idMonster, ArrayList<AbilitiesActions> actions) {

        try {
            JSONObject data = new JSONObject(jsonResponse);
            String mname= data.getString("name");
            // Procesar las acciones si hay
            if (data.has("actions")) {
                JSONArray actionsArray = data.getJSONArray("actions");

                for (int i = 0; i < actionsArray.length(); i++) {
                    JSONObject actionObject = actionsArray.getJSONObject(i);
                    AbilitiesActions action = new AbilitiesActions();
                    action.setName(actionObject.getString("name"));
                    action.setDesc(actionObject.getString("desc"));
                    action.setNameMonster(mname);
                    action.setType("Action");
                    actions.add(action);
                }
            }
            // Procesar las habilidades especiales si hay
            if (data.has("special_abilities")) {
                JSONArray specialAbilitiesArray = data.getJSONArray("special_abilities");
                for (int i = 0; i < specialAbilitiesArray.length(); i++) {
                    JSONObject specialAbilityObject = specialAbilitiesArray.getJSONObject(i);
                    AbilitiesActions specialAbility = new AbilitiesActions();
                    specialAbility.setName(specialAbilityObject.getString("name"));
                    specialAbility.setDesc(specialAbilityObject.getString("desc"));
                    specialAbility.setNameMonster(mname.toString());
                    specialAbility.setType("SpecialAbility");
                    actions.add(specialAbility);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
