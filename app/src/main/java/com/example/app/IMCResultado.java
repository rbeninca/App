package com.example.app;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IMCResultado extends AppCompatActivity {

    TextView tvPeso,tvAltura,tvImc;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imcresultado);
        tvPeso=findViewById(R.id.tvPeso);
        tvAltura=findViewById(R.id.tvAltura);
        tvImc=findViewById(R.id.tvImc);

        imageView=findViewById(R.id.imgPerfil);

        Bundle b=getIntent().getExtras();

        float peso=b.getFloat("peso");
        float altura=b.getFloat("altura");

        float imc=(peso)/(altura*altura);

        tvPeso.setText(Float.toString(peso));
        tvAltura.setText(Float.toString(altura));
        tvImc.setText(Float.toString(imc));


        if (imc<18.5){
            imageView.setImageResource(R.drawable.abaixopeso);
        }
        if(imc>18.5 && imc<100){
            imageView.setImageResource(R.drawable.normal);
        }


    }
}