package com.example.app;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    PackageManager pm;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        listView =findViewById(R.id.listView);

        //Recupera  gerenciador de pacotes
        pm=getPackageManager();


        List<ApplicationInfo> apps = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        AppAdapter adapter = new AppAdapter(this, R.layout.app, apps);

        listView.setAdapter(adapter);

    }
}