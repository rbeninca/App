package com.example.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button buttonSalvar;
    EditText editText;
    ArrayList<String> nomes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView =findViewById(R.id.listView); //Associando view a variavel local listView
        buttonSalvar = findViewById(R.id.buttonSalvar); //Associando view a variavel local
        editText = findViewById(R.id.editTextText); //Associando view a variavel local
        nomes = new ArrayList<String>();//Inicializando lista de nomes com ArrayList Vazio
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nomes);
        listView.setAdapter(adapter);//Definindo adapter para a listView
        buttonSalvar.setOnClickListener( v -> {
            nomes.add(editText.getText().toString());
            adapter.notifyDataSetChanged();
        });
        listView.setOnItemLongClickListener( (parent, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Tem Certeza que quer excluir?")
                    .setMessage("Confirma a excluisão do item!")
                    .setPositiveButton("OK", (dialog, which) -> {
                        nomes.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Item excluído com sucesso!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Cancelar", (dialog, which) -> {
                        Toast.makeText(MainActivity.this, "Operação cancelada!", Toast.LENGTH_SHORT).show();
                    })
                    .setCancelable(false); // Impede o fechamento clicando fora do diálogo
            // Exibe o Diálogo
            builder.show();

            return true;
        });

    }
}