package com.example.dndaaron;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
    private ArrayList<Monster> monsters;
    private MonsterAdapter adapter;
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
            DndAPI api = new DndAPI();
            monsters = api.getMonsters();
            adapter=new MonsterAdapter(this.getContext(),R.layout.monster_row,monsters);
        });


        binding.list.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}