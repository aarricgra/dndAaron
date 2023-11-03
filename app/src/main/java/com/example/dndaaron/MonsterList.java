package com.example.dndaaron;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dndaaron.API.DndAPI;
import com.example.dndaaron.API.Monster;
import com.example.dndaaron.databinding.MonsterListBinding;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MonsterList extends Fragment {

    private MonsterListBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = MonsterListBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            Log.d("aaaaa","Hola");
            DndAPI dndAPI = new DndAPI();
            ArrayList<Monster> monsters = dndAPI.getMonsters();
            for (Monster monster:monsters) {
                Log.d("aaaaa",monster.toString());
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}