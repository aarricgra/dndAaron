package com.example.dndaaron.API;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class DndAPI {
    private final String BASE_URL = "https://www.dnd5eapi.co";

    ArrayList<Monster> getMonsters() {
        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath("api/monsters")
                .build();
        String url = builtUri.toString();

        return doCall(url);
    }

    private ArrayList<Monster> doCall(String url) {
        try {
            String JsonResponse = HttpUtils.get(url);
            return procesJson(JsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Monster> procesJson(String jsonResponse) {
        ArrayList<Monster> monsters = new ArrayList<>();
        try {
            JSONObject data = new JSONObject(jsonResponse);
            JSONArray jsonMonsters = data.getJSONArray("results");
            for (int i = 0; i < jsonMonsters.length(); i++) {
                JSONObject jsonMonster = jsonMonsters.getJSONObject(i);

                Monster monster = new Monster();
                monster.setName(jsonMonster.getString("name"));


                monsters.add(monster);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return monsters;
    }
}
