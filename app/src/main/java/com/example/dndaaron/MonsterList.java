package com.example.dndaaron;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
        setHasOptionsMenu(true);
        binding = MonsterListBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Crear adapter de la lista vacio
        adapter = new MonsterListAdapter(getContext(),R.layout.monster_row,new ArrayList<Monster>());

        //Ponerle a la lista el adapter
        binding.list.setAdapter(adapter);

        //Coger info de la base de datos y meterla en el adapter
        model=new ViewModelProvider(this).get(MonsterViewModel.class);
        binding.filterInput.setText("");
        model.getMonstersFilteredBy(binding.filterInput.getText().toString()).observe(
                getViewLifecycleOwner(),monsters -> {
                    adapter.clear();
                    adapter.addAll(monsters);
                }
        );

        binding.filterInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                model.getMonstersFilteredBy(s.toString()).observe(
                        getViewLifecycleOwner(),monsters -> {
                            adapter.clear();
                            adapter.addAll(monsters);
                        }
                );
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Onclick para ir a el fragmento con la info del monstruo.
        binding.list.setOnItemClickListener((adapterView, view1, i, l) -> {
            //Cojo el monstruo al que le han dado click
            Monster monster = (Monster) adapterView.getItemAtPosition(i);

            //Guardo el monstruo en un bundle para enviarlo al otro fragmento
            Bundle datos = new Bundle();
            datos.putSerializable("monster", monster);

            //Cargo el otro fragmento con la info del monstruo
            NavHostFragment.findNavController(this).navigate(R.id.action_MonsterList_to_MonsterView, datos);
        });

        binding.emptyButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        binding.filterInput.setText("");
                    }
                }
        );

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
        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);
        dialog.setMessage("Updating");
        dialog.show();
        //Boton para actualizar la base de datos
        if (id == R.id.btRefresh) {
            model.refresh(dialog);
        }
        return super.onOptionsItemSelected(item);
    }

}