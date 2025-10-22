package com.example.app;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
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
 //   https://github.com/rbeninca/App

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

        ArrayList<ApplicationInfo> apps =new ArrayList<>();// = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        Intent iquery = new Intent(Intent.ACTION_MAIN, null);
        iquery.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> listResolvInfo=pm.queryIntentActivities(iquery,PackageManager.GET_META_DATA);
        for (ResolveInfo resolveInfo : listResolvInfo) {
            apps.add(resolveInfo.activityInfo.applicationInfo);
        }
        AppAdapter adapter = new AppAdapter(this, R.layout.app, apps);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(
                (parent, view, position, id) -> {
                    ApplicationInfo appInfo = (ApplicationInfo) parent.getItemAtPosition(position);
                    Intent launchIntent = pm.getLaunchIntentForPackage(appInfo.packageName);
                    if (launchIntent != null) {
                        startActivity(launchIntent);
                    } else {
                        Log.e("MainActivity", "Não foi possível iniciar o aplicativo: " + appInfo.packageName);
                    }
                }
        );

    }
}