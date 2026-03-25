package com.example.meuapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button bntAvancar,bntVoltar;
    ImageView imageView;

    Integer imagens[] = new Integer[]{
            R.drawable.cachorro,
            R.drawable.gardem,
            R.drawable.patinho,
            R.drawable.happy,
            R.drawable.porquinho
    };
    int posicao=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bntVoltar=findViewById(R.id.buttonVoltar);
        bntAvancar=findViewById(R.id.buttonAvancar);
        imageView=findViewById(R.id.imageView);
        bntAvancar.setOnClickListener(v ->{
          imageView.setImageResource(imagens[posicao]);
        } );


    }
}