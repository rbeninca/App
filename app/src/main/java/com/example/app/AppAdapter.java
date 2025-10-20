package com.example.app;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
public class AppAdapter extends ArrayAdapter<ApplicationInfo> {
    Context mContenxt;
    int mResourceLayout;
    public AppAdapter(@NonNull Context context, int resource, @NonNull List<ApplicationInfo> objects) {
        super(context, resource, objects);
        this.mContenxt = context;
        this.mResourceLayout = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContenxt);
        convertView = inflater.inflate(mResourceLayout, parent, false);
        //associar os componentes visuais com variaveis locais
        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView tvAppname= convertView.findViewById(R.id.tvNome);
        ApplicationInfo appInfo = getItem(position);
        if (appInfo==null) {
            return convertView;
        }
        imageView.setImageDrawable(  appInfo.loadIcon(mContenxt.getPackageManager()));
        tvAppname.setText(  appInfo.loadLabel(mContenxt.getPackageManager()).toString());

        return convertView;
    }
}
