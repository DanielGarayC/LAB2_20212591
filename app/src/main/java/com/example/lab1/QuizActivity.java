package com.example.lab1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.os.SystemClock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    ArrayList<Pregunta> listaPreguntas = new ArrayList<>();
    int preguntaActual = 0;
    int puntaje = 0;
    TextView textPregunta;
    Button opcion1, opcion2, opcion3;
    Button btnSiguiente, btnAnterior;
    String[] respuestasUsuario;
    boolean[] respondidas;
    long tiempoInicio;

    String correctaActual = "";
    //View pa mostrar puntaje
    TextView txtPuntaje;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        TextView txtTema = findViewById(R.id.txtTema);

        String temaSeleccionado = getIntent().getStringExtra("tema");
        txtTema.setText(temaSeleccionado);

        //Cargo las preguntas según el botón que seleccioné
        switch (temaSeleccionado) {
            case "Redes":
                cargarPreguntasRedes();
                break;
            case "Ciberseguridad":
                cargarPreguntasCiberseguridad();
                break;
            case "Microondas":
                cargarPreguntasMicroondas();
                break;
        }
        //Mostramos puntaje inicial 0
        txtPuntaje = findViewById(R.id.txtPuntaje);
        txtPuntaje.setText("Puntaje\n" + puntaje);

        //Asigno variables en java para todos los botones :D y texto
        textPregunta = findViewById(R.id.textPregunta);
        opcion1 = findViewById(R.id.opcion1);
        opcion2 = findViewById(R.id.opcion2);
        opcion3 = findViewById(R.id.opcion3);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        btnAnterior = findViewById(R.id.btnAnterior);

        respuestasUsuario = new String[listaPreguntas.size()];
        respondidas = new boolean[listaPreguntas.size()];
        tiempoInicio = SystemClock.elapsedRealtime();

        mostrarPregunta(preguntaActual);

        // Listeners de opciones
        opcion1.setOnClickListener(v -> manejarRespuesta(opcion1));
        opcion2.setOnClickListener(v -> manejarRespuesta(opcion2));
        opcion3.setOnClickListener(v -> manejarRespuesta(opcion3));


        btnSiguiente.setOnClickListener(v -> {

            if (preguntaActual < listaPreguntas.size() - 1) {
                preguntaActual++;
                mostrarPregunta(preguntaActual);
            } else {
                long duracion = (SystemClock.elapsedRealtime() - tiempoInicio) / 1000;
                Intent intent = new Intent(this, PuntajeActivity.class);
                intent.putExtra("puntaje", puntaje);
                intent.putExtra("duracion", duracion);
                startActivity(intent);
                finish();
            }
        });
        btnAnterior.setOnClickListener(v -> {
            if (preguntaActual > 0) {
                preguntaActual--;
                mostrarPregunta(preguntaActual);
            }
        });
    }

    private void cargarPreguntasRedes() {
        listaPreguntas.add(new Pregunta("¿Qué protocolo se utiliza para cargar páginas web?", new String[]{"HTTP", "FTP", "SSH"}, 0));
        listaPreguntas.add(new Pregunta("¿Cuál de estas es una dirección IP privada?", new String[]{"192.168.1.1", "8.8.8.8", "172.200.50.1"}, 0));
        listaPreguntas.add(new Pregunta("¿Qué dispositivo conecta redes diferentes?", new String[]{"Router", "Switch", "Hub"}, 0));
        listaPreguntas.add(new Pregunta("¿Qué significa DNS?", new String[]{"Domain Name System", "Dynamic Network Server", "Digital Name Signal"}, 0));
        listaPreguntas.add(new Pregunta("¿Qué tipo de red cubre un área pequeña?", new String[]{"LAN", "WAN", "MAN"}, 0));
        Collections.shuffle(listaPreguntas);
    }

    private void cargarPreguntasCiberseguridad() {
        listaPreguntas.add(new Pregunta("¿Qué es un Ransomware?", new String[]{"Un malware que cifra la información y pide rescate", "Un tipo de firewall", "Un antivirus avanzado"}, 0));
        listaPreguntas.add(new Pregunta("¿Cuál es el objetivo del phishing?", new String[]{"Robar información personal", "Proteger redes", "Optimizar conexiones"}, 0));
        listaPreguntas.add(new Pregunta("¿Qué protocolo cifra las comunicaciones web?", new String[]{"HTTPS", "HTTP", "FTP"}, 0));
        listaPreguntas.add(new Pregunta("¿Qué algoritmo de cifrado es asimétrico y se usa comúnmente para firmas digitales?", new String[]{"RSA", "AES", "SHA-256"}, 0));
        listaPreguntas.add(new Pregunta("¿Para qué sirve un firewall?", new String[]{"Filtrar tráfico de red", "Aumentar velocidad de Wi-Fi", "Detectar virus en correos"}, 0));

        Collections.shuffle(listaPreguntas);
    }

    private void cargarPreguntasMicroondas() {
        listaPreguntas.add(new Pregunta("¿En qué rango de frecuencias suelen operar las redes Wi-Fi?", new String[]{"2.4 GHz y 5 GHz", "10 GHz y 20 GHz", "900 MHz y 1.8 GHz"}, 0));
        listaPreguntas.add(new Pregunta("¿Qué problema es común en enlaces de microondas?", new String[]{"Interferencia por lluvia", "Virus informáticos", "Corte de fibra óptica"}, 0));
        listaPreguntas.add(new Pregunta("¿Qué es la zona de Fresnel en microondas?", new String[]{"Área alrededor del trayecto principal de la señal", "Zona ideal para la fibra óptica", "Tipo de modulación"}, 0));
        listaPreguntas.add(new Pregunta("¿Qué ventaja tienen las comunicaciones por microondas?", new String[]{"Alta capacidad y velocidad", "Menor temperatura", "Mayor interferencia"}, 0));
        listaPreguntas.add(new Pregunta("¿Qué dispositivo se usa para enfocar señales de microondas?", new String[]{"Antena parabólica", "Router Wi-Fi", "Fibra Óptica"}, 0));

        Collections.shuffle(listaPreguntas);
    }
    private void mostrarPregunta(int i) {
        Pregunta p = listaPreguntas.get(i);
        textPregunta.setText(p.enunciado);

        // Preparo las opciones según la pregunta seleccionada
        opcion1.setEnabled(true);
        opcion2.setEnabled(true);
        opcion3.setEnabled(true);
        opcion1.setBackgroundColor(Color.LTGRAY);
        opcion2.setBackgroundColor(Color.LTGRAY);
        opcion3.setBackgroundColor(Color.LTGRAY);
        btnSiguiente.setEnabled(false);
        btnSiguiente.setAlpha(0.5f);

        btnAnterior.setEnabled(i > 0);

        String correctaActual = p.opciones[p.indiceCorrecta];

        //Mezclamos las opciones para que se muestren aleatoriamente
        List<String> opciones = new ArrayList<>(Arrays.asList(p.opciones));
        Collections.shuffle(opciones);

        // Muestro las opciones luego de chocolatear
        opcion1.setText(opciones.get(0));
        opcion2.setText(opciones.get(1));
        opcion3.setText(opciones.get(2));

        if (respondidas[i]) {
            Button[] botones = {opcion1, opcion2, opcion3};
            for (Button b : botones) {
                if (b.getText().toString().equals(respuestasUsuario[i])) {
                    b.setBackgroundColor(
                            respuestasUsuario[i].equals(correctaActual) ? Color.GREEN : Color.RED
                    );
                }

                b.setEnabled(false);
            }
            btnSiguiente.setEnabled(true);
            btnSiguiente.setAlpha(1.0f); // Normal cuando está habilitado

        }
    }

    private void manejarRespuesta(Button botonSeleccionado) {
        String textoSelec = botonSeleccionado.getText().toString();
        respuestasUsuario[preguntaActual] = textoSelec;
        respondidas[preguntaActual] = true;


        if (textoSelec.equals(correctaActual)) {
            botonSeleccionado.setBackgroundColor(Color.GREEN);
            puntaje += 2;
            txtPuntaje.setText("Puntaje\n" + puntaje);

        } else {
            botonSeleccionado.setBackgroundColor(Color.RED);
            puntaje -= 2;
            txtPuntaje.setText("Puntaje\n" + puntaje);

        }

        //Habilito todos los botones owo
        Button[] botones = {opcion1, opcion2, opcion3};
        for (Button b : botones) {
            b.setEnabled(false);
        }

        btnSiguiente.setEnabled(true);
        btnSiguiente.setAlpha(1.0f);

    }

}