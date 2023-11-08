package com.example.dndaaron;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.dndaaron.Data.ActionViewModel;
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
            Monster monster = (Monster) args.getSerializable("monster");

            if (monster != null) {
                updateUi(monster);
            }
        }
    }

    private void updateUi(Monster monster) {
        binding.mName.setText(monster.getName());
        Picasso.get().load(monster.getImg()).into(binding.mImage);

        binding.mCa.setText(""+monster.getAc());
        binding.mHp.setText(""+monster.getHp());

        Random random = new Random();
        binding.mCon.setText(""+monster.getCon());
        binding.mCon.setOnClickListener(
                v -> rollDice(monster.getCon())
        );
        binding.mStr.setText(""+monster.getStr());
        binding.mStr.setOnClickListener(
                v -> rollDice(monster.getStr())
        );
        binding.mDex.setText(""+monster.getDex());
        binding.mDex.setOnClickListener(
                v -> rollDice(monster.getDex())
        );
        binding.mInt.setText(""+monster.getInte());
        binding.mInt.setOnClickListener(
                v -> rollDice(monster.getInte())
        );
        binding.mWis.setText(""+monster.getWis());
        binding.mWis.setOnClickListener(
                v -> rollDice(monster.getWis())
        );
        binding.mCha.setText(""+monster.getChari());
        binding.mCha.setOnClickListener(
                v -> rollDice(monster.getChari())
        );


        adapter1 = new MonsterViewAdapter(getContext(),R.layout.action_row,new ArrayList<AbilitiesActions>());
        binding.actionList.setAdapter(adapter1);

        model= new ViewModelProvider(this).get(MonsterViewModel.class);

        model.getActionsFrom(monster.getName(),"Action").observe(
                getViewLifecycleOwner(),actions -> {
                    adapter1.clear();
                    adapter1.addAll(actions);
                    Log.d("eeaaee",actions.toString());
                }
        );
//
        adapter2 = new MonsterViewAdapter(getContext(),R.layout.action_row,new ArrayList<AbilitiesActions>());
        binding.abilitiesList.setAdapter(adapter2);

        model= new ViewModelProvider(this).get(MonsterViewModel.class);

        model.getActionsFrom(monster.getName(),"SpecialAbility").observe(
                getViewLifecycleOwner(),actions -> {
                    adapter2.clear();
                    adapter2.addAll(actions);
                    Log.d("eeaaee",actions.toString());
                }
        );

    }

    private void rollDice(int value) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Rolled dice");
        Random random = new Random();
        int r=random.nextInt(20)+1;
        int m=(value-10)/2;
        builder.setMessage(r+"+"+m+"="+(r+m));

        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}