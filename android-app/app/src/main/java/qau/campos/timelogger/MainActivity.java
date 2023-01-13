package qau.campos.timelogger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(view -> {
           try {
               Intent intent = new Intent(MainActivity.this, LoggerView.class);
               startActivity(intent);
           }catch(Exception ex){
               Log.e("main", ex.toString());
            }
        });
    }
}