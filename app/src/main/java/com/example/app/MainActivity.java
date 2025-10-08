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
    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nomes   =new ArrayList<>() {{
            add("Apple");
            add("Banana");
            add("Uva");
            add("Melão");
        }};

        listView = findViewById(R.id.listView);
        editText= findViewById(R.id.editTextText);
        button=findViewById(R.id.button);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                nomes
        );
        //Define um tratamento para o evento de click sobre o bottão
        button.setOnClickListener(v -> {
            String nome = editText.getText().toString();
            nomes.add(nome);
            adapter.notifyDataSetChanged();
        });
        //Definir um tratamento para o evento de click sobre o item da lista
        listView.setOnItemClickListener(
                (parent, view, position, id) -> {
                    parent.getAdapter().getItem(position); //Obter o objeto relacionado a view clicada
                    Toast.makeText(
                            getApplicationContext(),
                            "Elemento clicado: "+nomes.get(position),
                            Toast.LENGTH_SHORT).show();
                });
        //Definir um tratamento para o evento de long click sobre o item da lista
        listView.setOnItemLongClickListener(
                (parent, view, position, id) -> {
                    nomes.remove(position);
                    adapter.notifyDataSetChanged();
                    return true;
                });
        listView.setAdapter(adapter);
    }
}


























