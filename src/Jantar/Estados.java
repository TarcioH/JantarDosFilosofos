/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Jantar;

/**
 *
 * @author Tárcio
 */
public class Estados {

    private final int[] contador;
    private final int[] estado;
    private final int[] tempoSemComer;
    private final int[] mediaDeEspera;
    private final int[] tempoMaximoSemComer;

    public Estados() {
        contador = new int[5];
        estado = new int[5];
        tempoSemComer = new int[5];
        mediaDeEspera = new int[5];
        tempoMaximoSemComer = new int[5];
    }

    public int getContador(int i) {
        return contador[i];
    }

    public int getEstado(int i) {
        return estado[i];
    }

    public int getTempoSemComer(int i) {
        return tempoSemComer[i];
    }

    public int getMediaDeEspera(int i) {
        return mediaDeEspera[i];
    }

    public int getTempoMaximoSemComer(int i) {
        return tempoMaximoSemComer[i];
    }

    public void setContador(int i, int valor) {
        this.contador[i] = valor;
    }

    public void setEstado(int i, int valor) {
        this.estado[i] = valor;
    }

    public void setTempoSemComer(int i, int valor) {
        this.tempoSemComer[i] = valor;
    }

    public void setMediaDeEspera(int i, int valor) {
        this.mediaDeEspera[i] = valor;
    }

    public void setTempoMaximoSemComer(int i, int valor) {
        this.tempoMaximoSemComer[i] = valor;
    }
    
}
