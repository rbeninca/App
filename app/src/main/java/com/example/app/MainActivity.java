package com.example.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText editTextMin,editTextMax;
    TextView textViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //Associar os objetos declarados e inicializados apatir do XML com variaveis locais.
        button=findViewById(R.id.button);
        editTextMin=findViewById(R.id.edMin);
        editTextMax=findViewById(R.id.edMax);
        textViewResultado=findViewById(R.id.tvResult);

        button.setOnClickListener((v) -> {
            int min=Integer.parseInt(  editTextMin.getText().toString() );
            int max=Integer.parseInt(  editTextMax.getText().toString() );
            int sorteado=0;
            Random random=new Random();
            sorteado=random.nextInt((max-min))+min;
            sorteado= (int) (Math.random()*(max-min)+min);
            textViewResultado.setText(Integer.toString(sorteado));
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}