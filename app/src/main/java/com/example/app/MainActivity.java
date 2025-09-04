package com.example.app;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Random;
public class MainActivity extends AppCompatActivity {
    Button button; //aponta inicialmente para null
    EditText editTextMin, editTextMax;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button =findViewById(R.id.button);
        editTextMin =findViewById(R.id.edMin);
        editTextMax =findViewById(R.id.edMax);

        editTextMin.setText(Integer.toString(50));
        editTextMax.setText(Integer.toString(100));
        tv=findViewById(R.id.tvResultado);

        button.setOnClickListener(v -> {
            Random  random=new Random();
            int min,max;
            min=Integer.parseInt(editTextMin.getText().toString());
            max=Integer.parseInt(editTextMax.getText().toString());
            int delta=max-min;
            int sortiado= random.nextInt(delta)+min+1;

            tv.setText(Integer.toString( sortiado ));
        });

    }

    /// protect  OnSaveInstaceState ele é chamado na recriação do layout no ciclo de vida
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("sorteado",tv.getText().toString() );
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState!=null){
            tv.setText(savedInstanceState.getString("sorteado"));
        }
    }
    /// protect  OnSaveInstaceState ele é chamado após um onDestroy
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }
}