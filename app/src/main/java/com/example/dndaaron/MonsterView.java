package com.example.dndaaron;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dndaaron.API.Monster;
import com.example.dndaaron.databinding.MonsterViewBinding;
import com.squareup.picasso.Picasso;

public class MonsterView extends Fragment {

    private MonsterViewBinding binding;

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

        binding.mCon.setText(""+monster.getCon());
        binding.mStr.setText(""+monster.getStr());
        binding.mDex.setText(""+monster.getDex());
        binding.mInt.setText(""+monster.getInte());
        binding.mWis.setText(""+monster.getWis());
        binding.mCha.setText(""+monster.getChari());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}