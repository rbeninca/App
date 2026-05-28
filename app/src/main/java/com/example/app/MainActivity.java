package com.example.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    FragmentoA fragmentoA;
    FragmentoB fragmentoB;
    FragmentoC fragmentoC;
    Button buttonA,buttonB,buttonC;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonA=findViewById(R.id.buttonA);
        buttonB=findViewById(R.id.button4);
        buttonC=findViewById(R.id.buttonC);

        //Inicialização Gerenciador de Fragmentos;
        FragmentManager fragmentManager =getSupportFragmentManager();
        //Inicia uma transação com FragmentMangater
        FragmentTransaction  transaction=fragmentManager.beginTransaction();
        //Adicionamos o container e o objeto do nosso fragmentoA para exibir.
        transaction.add(R.id.frameLayout,new FragmentoA());
        //Finalizamos a transação do fragment com commit.
        transaction.commit();

        buttonA.setOnClickListener(v -> {
             FragmentTransaction ft =fragmentManager.beginTransaction();
             ft.replace(R.id.frameLayout,new FragmentoA());
             ft.commit();
        });
        buttonB.setOnClickListener(v -> {
            FragmentTransaction ft =fragmentManager.beginTransaction();
            ft.replace(R.id.frameLayout,new FragmentoB());
            ft.commit();
        });
        buttonC.setOnClickListener(v -> {
            FragmentTransaction ft =fragmentManager.beginTransaction();
            ft.replace(R.id.frameLayout,new FragmentoC());
            ft.commit();
        });

    }


}