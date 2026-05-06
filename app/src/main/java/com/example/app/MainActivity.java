package com.example.app;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        lv=findViewById(R.id.listview);
        //Recuperar dados datasource
        PlanetaContoller pcontroler=new PlanetaContoller();

      AdapterPlaneta adaptador = new AdapterPlaneta(
              this,
              //XML com texto e img,
              pcontroler.listaPlanetas();
      )

        lv.setAdapter(adaptador);
    }
}