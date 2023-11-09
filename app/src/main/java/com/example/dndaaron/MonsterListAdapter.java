package com.example.dndaaron;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dndaaron.API.Monster;

import java.util.List;

public class MonsterListAdapter extends ArrayAdapter<Monster> {
    public MonsterListAdapter(Context context, int resource, List<Monster> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Monster monster = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.monster_row, parent, false);
        }

        //poner el nombre del monstruo dentro de la textview
        TextView mName = convertView.findViewById(R.id.monsterName);
        mName.setText(monster.getName());

        return convertView;
    }
}
