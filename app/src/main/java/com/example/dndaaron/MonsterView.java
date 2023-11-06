package com.example.dndaaron;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dndaaron.API.Monster;
import com.example.dndaaron.databinding.FragmentSecondBinding;
import com.example.dndaaron.databinding.MonsterViewBinding;

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
            Monster monster = (Monster) args.getSerializable("pokemon");

            if (monster != null) {
                updateUi(monster);
            }
        }
    }

    private void updateUi(Monster monster) {
        binding.INombre.setText(pokemon.getName());
        Picasso.get().load(pokemon.getImage()).into(binding.IFoto);
        binding.IPeso.setText(""+pokemon.getWeight());
        binding.IAltura.setText(""+pokemon.getHeight());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}