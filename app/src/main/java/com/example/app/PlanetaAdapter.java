package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PlanetaAdapter extends ArrayAdapter<Planeta> {
    Context mContext;
    int layoutResourceId;

    public PlanetaAdapter(@NonNull Context context, int resource, @NonNull List<Planeta> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.layoutResourceId = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Inflar o layout XML
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(layoutResourceId, parent, false);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView  textView = view.findViewById(R.id.tvNome);

        Planeta planeta = getItem(position);
        imageView.setImageResource(planeta.foto);
        textView.setText(planeta.nome);

        return view;
    }
}
