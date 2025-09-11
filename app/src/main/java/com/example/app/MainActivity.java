package com.example.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    Button buttonA, buttonB;
    FragmentoA fragmentoA ;
    FragmentoB fragmentoB ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonA=findViewById(R.id.buttonA);
        buttonB=findViewById(R.id.buttonB);
        frameLayout=findViewById(R.id.frameLayout);



        buttonA.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if (fragmentoA ==null){
                fragmentoA =new FragmentoA();
            }

            fragmentTransaction.replace(R.id.frameLayout,fragmentoA);
            fragmentTransaction.commit();

        });
        buttonB.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if (fragmentoB==null){
                fragmentoB=new FragmentoB();
            }
            Bundle bundle = new Bundle();
            bundle.putString("msg","Olá ");
            fragmentoB.setArguments(bundle);
            fragmentTransaction.replace(R.id.frameLayout,fragmentoB);
            fragmentTransaction.commit();
        });







    }
}