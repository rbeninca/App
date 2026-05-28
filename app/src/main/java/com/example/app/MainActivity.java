package com.example.app;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    ImageButton buttonSalvar;
    EditText editText;
    ArrayAdapter<String> adapter;
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
        buttonSalvar = findViewById(R.id.imageButton);
        editText=findViewById(R.id.editTextText);
        //tratamento click botão salvar.
        buttonSalvar.setOnClickListener(v -> {
            ContentValues cv = new ContentValues();
            cv.put("titulo",editText.getText().toString() );
            cv.put("nota",editText.getText().toString());
            db.insert("notas",null,cv);
            listagem();

        });
        // inicializar o banco de dados ou abrir caso o mesmo não exista
        db=openOrCreateDatabase("notas",MODE_PRIVATE,null );
        db.execSQL("CREATE TABLE IF NOT EXISTS notas (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TXT,nota TEXT ) ");
        //inserirndo dados no banco de dados
//        ContentValues cv = new ContentValues();
//        cv.put("titulo","João");
//        cv.put("nota","João");
//        db.insert("notas",null,cv);

        //Recuperando dados no banco de da
        listagem();
    }
    public  void listagem(){
        Cursor  cursor = db.rawQuery("SELECT * FROM notas", null);
        cursor.moveToNext();

        ArrayList<Nota> listaNotas=new ArrayList<>();
        while (!cursor.isAfterLast()){
            int id=cursor.getInt(cursor.getColumnIndex("id"));
            String titulo=cursor.getString(cursor.getColumnIndex("titulo"));
            String nota=cursor.getString(cursor.getColumnIndex("nota"));
            Nota n = new Nota(id,titulo,nota);
            listaNotas.add(n);
            cursor.moveToNext();
        }
        ArrayList<String> listaTituloNotas=new ArrayList<>();
        for (Nota nota: listaNotas) {
            listaTituloNotas.add(nota.titulo);
        }

        //Exibindo no listview
        adapter =new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,android.R.id.text1,listaTituloNotas);

        ListView lv = findViewById(R.id.listview);
        lv.setAdapter(adapter);
    }
}