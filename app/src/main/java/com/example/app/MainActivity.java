package com.example.app;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("ciclo_vida","Oncreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ciclo_vida","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ciclo_vida","OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ciclo_vida","onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("ciclo_vida","onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("ciclo_vida","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ciclo_vida","onDestroy");
    }
}