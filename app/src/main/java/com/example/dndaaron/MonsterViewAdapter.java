package com.example.dndaaron;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.example.dndaaron.API.AbilitiesActions;
import com.example.dndaaron.API.Monster;

import java.util.ArrayList;
import java.util.List;

public class MonsterViewAdapter extends ArrayAdapter<AbilitiesActions> {

    public MonsterViewAdapter(Context context, int resource, ArrayList<AbilitiesActions> objects) {
        super(context, resource, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AbilitiesActions action = getItem(position);


            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.action_row, parent, false);
            }

            TextView aName = convertView.findViewById(R.id.aName);
            TextView aDesc = convertView.findViewById(R.id.aDesc);

            aName.setText(action.getName());
            aDesc.setText(action.getDesc());



        return convertView;
    }
}
