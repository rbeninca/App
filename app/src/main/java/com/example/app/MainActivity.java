package com.example.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText edPeso,edAltura;
    TextView tvImc;
    ImageView imageView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edPeso=findViewById(R.id.edPeso);
        edAltura=findViewById(R.id.edAltura);
        tvImc =findViewById(R.id.tvImc);
        imageView=findViewById(R.id.imageView);
        button = findViewById(R.id.button);

        button.setOnClickListener( v -> {
            String strPeso= edPeso.getText().toString();
            String strAltura= edAltura.getText().toString();


            Double peso=  Double.parseDouble(strPeso);
            Double altura=  Double.parseDouble(strAltura);

            Double imc = peso/(altura*altura);
            DecimalFormat dc= new DecimalFormat("##.##");

            tvImc.setText(dc.format(imc));

        });

    }
}