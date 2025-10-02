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
    EditText ednome, edpeso,edaltura;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ednome=findViewById(R.id.edNome);
        edpeso=findViewById(R.id.edpeso);
        edaltura=findViewById(R.id.edaltura);

        Button b =findViewById(R.id.button);

        b.setOnClickListener(v -> {
            boolean dadosfornecidos=true;
            if (ednome.getText().toString()==""){
                ednome.setError("Informe um nome");
                dadosfornecidos=false;
            }
            if (edpeso.getText().toString()=="") {
                edpeso.setError("Informe um peso");
                dadosfornecidos=false;
            }
            if (edaltura.getText().toString()==""){
                edaltura.setError("Informe um altura");
                dadosfornecidos=false;
            }

            if (dadosfornecidos){
                Double altura= Double.parseDouble(edaltura.getText().toString());
                Double peso= Double.parseDouble(edpeso.getText().toString());
                String nome =ednome.getText().toString();

                getIntent().putExtra("altura",altura);
                getIntent().putExtra("peso",peso);
                getIntent().putExtra("nome",altura);
                Intent i = new Intent(getApplicationContext(),ResultadoIMC.class);
                startActivity(i);

            }

        });
    }
}