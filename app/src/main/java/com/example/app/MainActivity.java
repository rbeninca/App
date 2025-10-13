package com.example.app;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> nomes ;
    ControllerPlaneta controllerPlaneta;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controllerPlaneta = new ControllerPlaneta();

        PlanetaAdapter adapter = new PlanetaAdapter(this,R.layout.itemlista, controllerPlaneta.getPlanetas());
        listView = findViewById(R.id.listView);
        button=findViewById(R.id.button);

        //Define um tratamento para o evento de click sobre o bottão
        button.setOnClickListener(v -> {

        });
        //Definir um tratamento para o evento de click sobre o item da lista
        listView.setOnItemClickListener((parent, view, position, id) -> {});
        listView.setAdapter(adapter);
    }
}


























