package com.example.dndaaron;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.dndaaron.API.Monster;
import com.example.dndaaron.Data.MonsterViewModel;
import com.example.dndaaron.databinding.MonsterListBinding;

import java.util.ArrayList;


public class MonsterList extends Fragment {

    private MonsterListBinding binding;
    private ArrayList<Monster> monsters;
    private MonsterListAdapter adapter;
    private MonsterViewModel model;
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

        model= new ViewModelProvider(this).get(MonsterViewModel.class);

        model.getMonsters().observe(
                getViewLifecycleOwner(),pokemons -> {
                    adapter.clear();
                    adapter.addAll(pokemons);
                }
        );

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


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.btRefresh) {
            model.refresh();
        }

        return super.onOptionsItemSelected(item);
    }

}