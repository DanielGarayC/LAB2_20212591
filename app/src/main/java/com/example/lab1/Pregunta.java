package com.example.lab1;

public class Pregunta {
    String enunciado;
    String[] opciones;
    int indiceCorrecta;

    public Pregunta(String enunciado, String[] opciones, int indiceCorrecta) {
        this.enunciado = enunciado;
        this.opciones = opciones;
        this.indiceCorrecta = indiceCorrecta;
    }
    public String getEnunciado() {
        return enunciado;
    }

    public String[] getOpciones() {
        return opciones;
    }

    public int getIndiceCorrecta() {
        return indiceCorrecta;
    }
}
