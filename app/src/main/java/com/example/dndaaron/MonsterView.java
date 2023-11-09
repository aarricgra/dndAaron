package com.example.dndaaron;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.dndaaron.API.AbilitiesActions;
import com.example.dndaaron.API.Monster;
import com.example.dndaaron.Data.MonsterViewModel;
import com.example.dndaaron.databinding.MonsterViewBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

public class MonsterView extends Fragment {

    private MonsterViewBinding binding;
    private MonsterViewAdapter adapter1;
    private MonsterViewAdapter adapter2;

    private MonsterViewModel model;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = MonsterViewBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();

        if(args != null) {
            //Pilla el monstruo que se le ha enviado
            Monster monster = (Monster) args.getSerializable("monster");

            //Si lo tiene llama a la funcion que mete los datos en lo campos
            if (monster != null) {
                updateUi(monster);
            }
        }
    }

    private void updateUi(Monster monster) {
        //Aqui va cogiendo la info y metiendola en los campos
        binding.mName.setText(monster.getName());
        Picasso.get().load(monster.getImg()).into(binding.mImage);

        binding.mCa.setText(""+monster.getAc());
        binding.mHp.setText(""+monster.getHp());

        //En los atributos les mete un onclick para hacer la tirada de dados y sacarlo en popup
        Random random = new Random();
        binding.mCon.setText(""+monster.getCon());
        binding.mCon.setOnClickListener(
                v -> rollDice(monster.getCon())
        );
        binding.tCon.setOnClickListener(
                v -> rollDice(monster.getCon())
        );
        binding.mStr.setText(""+monster.getStr());
        binding.mStr.setOnClickListener(
                v -> rollDice(monster.getStr())
        );
        binding.tStr.setOnClickListener(
                v -> rollDice(monster.getStr())
        );
        binding.mDex.setText(""+monster.getDex());
        binding.mDex.setOnClickListener(
                v -> rollDice(monster.getDex())
        );
        binding.tDex.setOnClickListener(
                v -> rollDice(monster.getDex())
        );
        binding.mInt.setText(""+monster.getInte());
        binding.mInt.setOnClickListener(
                v -> rollDice(monster.getInte())
        );
        binding.tInt.setOnClickListener(
                v -> rollDice(monster.getInte())
        );
        binding.mWis.setText(""+monster.getWis());
        binding.mWis.setOnClickListener(
                v -> rollDice(monster.getWis())
        );
        binding.tWis.setOnClickListener(
                v -> rollDice(monster.getWis())
        );
        binding.mCha.setText(""+monster.getChari());
        binding.mCha.setOnClickListener(
                v -> rollDice(monster.getChari())
        );
        binding.tChar.setOnClickListener(
                v -> rollDice(monster.getChari())
        );


        //Crear adapter para las acciones vacio y ponerselo a la lista de acciones
        adapter1 = new MonsterViewAdapter(getContext(),R.layout.action_row,new ArrayList<AbilitiesActions>());
        binding.actionList.setAdapter(adapter1);

        //Pillar la info de las acciones
        model= new ViewModelProvider(this).get(MonsterViewModel.class);
        model.getActionsFrom(monster.getName(),"Action").observe(
                getViewLifecycleOwner(),actions -> {
                    adapter1.clear();
                    adapter1.addAll(actions);
                    Log.d("eeaaee",actions.toString());
                }
        );

        //Crear adapter para las habilidades vacio y ponerselo a la lista de acciones
        adapter2 = new MonsterViewAdapter(getContext(),R.layout.action_row,new ArrayList<AbilitiesActions>());
        binding.abilitiesList.setAdapter(adapter2);

        //Pillar la info de las acciones
        model= new ViewModelProvider(this).get(MonsterViewModel.class);
        model.getActionsFrom(monster.getName(),"SpecialAbility").observe(
                getViewLifecycleOwner(),actions -> {
                    adapter2.clear();
                    adapter2.addAll(actions);
                }
        );

    }

    private void rollDice(int value) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Rolled dice");
        Random random = new Random();
        int r=random.nextInt(20)+1;
        if (value<10){
            value--;
        }
        int m=(value-10)/2;
        if(m<0){
            builder.setMessage(r+"-"+(m*-1)+"="+(r+m));
        }else {
            builder.setMessage(r+"+"+m+"="+(r+m));
        }

        builder.setNegativeButton("Close", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}