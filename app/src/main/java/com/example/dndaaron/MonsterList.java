package com.example.dndaaron;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.dndaaron.API.DndAPI;
import com.example.dndaaron.API.Monster;
import com.example.dndaaron.databinding.MonsterListBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MonsterList extends Fragment {

    private MonsterListBinding binding;
    private ArrayList<Monster> monsters;
    private MonsterListAdapter adapter;
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

        adapter = new MonsterListAdapter(getContext(),R.layout.monster_row,new ArrayList<Monster>());

        binding.list.setAdapter(adapter);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            DndAPI api = new DndAPI();
            monsters = api.getMonsters();
            handler.post(() -> {

                adapter.addAll(monsters);
            });
        });


        binding.list.setOnItemClickListener((adapterView, view1, i, l) -> {
            Monster monster = (Monster) adapterView.getItemAtPosition(i);

            Bundle datos = new Bundle();
            datos.putSerializable("monster", monster);


            NavHostFragment.findNavController(this).navigate(R.id.action_MonsterList_to_MonsterView, datos);


        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}