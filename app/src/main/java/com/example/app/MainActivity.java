package com.example.app;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase database; //declara uma variável do tipo SQLiteDatabase
    EditText editText;
    Button button;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database=openOrCreateDatabase("notinhas",MODE_PRIVATE,null);
        database.execSQL("CREATE TABLE IF NOT EXISTS notas" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " titulo VARCHAR," +
                "conteudo VARCHAR)");
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.edtext);
        button=findViewById(R.id.button);
        listView=findViewById(R.id.listView);

        button.setOnClickListener(v -> {
            String titulo=editText.getText().toString();
            ContentValues values=new ContentValues();
            values.put("titulo",titulo);
            int id=(int) database.insert("notas",null,values);
            if (id>0) {
                Toast.makeText(getApplicationContext(),
                        "Nota salva com sucesso",
                        Toast.LENGTH_SHORT).show();}
            else {
                Toast.makeText(getApplicationContext(),
                        "Erro ao salvar nota",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public  void listarNotas(){
        Cursor c= database.rawQuery("SELECT * FROM notas LIMIT ? ",new String[]{"100"});
    }
}