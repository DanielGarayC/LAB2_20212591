package com.example.lab1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    Button btnRedes, btnCiber, btnMicroondas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRedes = findViewById(R.id.btnRedes);
        btnCiber = findViewById(R.id.btnCiberseguridad);
        btnMicroondas = findViewById(R.id.btnMicroondas);

        //Preguntas Redes ;)
        btnRedes.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            intent.putExtra("tema", "Redes");
            startActivity(intent);
        });

        //Preguntas Ciber ;)
        btnCiber.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            intent.putExtra("tema", "Ciberseguridad");
            startActivity(intent);
        });

        //Preguntas Microondas ;)
        btnMicroondas.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            intent.putExtra("tema", "Microondas");
            startActivity(intent);
        });
    }
}