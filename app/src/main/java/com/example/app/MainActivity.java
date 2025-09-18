package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edPeso, edAltura;
    Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edPeso = findViewById(R.id.edpeso);
        edAltura = findViewById(R.id.edaltura);
        btnCalcular = findViewById(R.id.button);
        btnCalcular.setOnClickListener(v -> {
            Double altura = Double.parseDouble(edAltura.getText().toString());
            Double peso = Double.parseDouble(edPeso.getText().toString());

            Intent intent = new Intent(MainActivity.this, ImcResultado.class);
            intent.putExtra("altura", altura);
            intent.putExtra("peso", peso);
            startActivities(intent);

        });
    }
}