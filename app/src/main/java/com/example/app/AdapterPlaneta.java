package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdapterPlaneta extends ArrayAdapter<Planeta> {
    Context lcontext;

    public AdapterPlaneta(@NonNull Context context, int resource, @NonNull List<Planeta> objects) {
        super(context, resource, objects);
        lcontext=context;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Inflar layout xml
        LayoutInflater inflater = LayoutInflater.from(lcontext);

        View itemView= inflater.inflate(R.layout.item_lista,parent);


        return super.getView(position, convertView, parent);
    }
}
