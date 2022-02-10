package com.gradle.sokoban;
import java.util.*;
import java.io.*;


public class Sokoban
{
 private Scanner input = null;

    public static void main (String[] args) {

        Sokoban sokoban = new Sokoban();
        sokoban.run();
    }

    public void run() {
      input = new Scanner(System.in);
      this.input.useDelimiter("[\\s]*");
      int filas = this.input.nextInt();
      int columnas = this.input.nextInt();
      int cajas = this.input.nextInt();
      char[][] tablero = new char[filas][columnas];
      for (int indexFila = 0; indexFila < filas; indexFila++) {
        for (int indexColumna = 0; indexColumna < columnas; indexColumna++) {
          tablero[indexFila][indexColumna] = this.input.next().charAt(0);
        }
      }

      posicionCajas(filas, columnas, cajas, tablero);
      input.close();
    }

    public void posicionCajas(int filas, int columnas, int cajas,
                              char[][] tablero) {
      String[] coordenadasCajas = new String[cajas];
      int contadorCoordenadas = 0;
      for (int indexFila = 0; indexFila < filas; indexFila++) {
        for (int indexColumna = 0; indexColumna < columnas; indexColumna++) {
          if (tablero[indexFila][indexColumna].equals('X')) {
            coordenadasCajas[contadorCoordenadas] =
                String.format("r0%dc0%d* " ,indexFila, indexColumna);
            contadorCoordenadas++;
          } else if (tablero[indexFila][indexColumna].equals('*')) {
            coordenadasCajas[contadorCoordenadas] =
                String.format("r0%dc0%d " ,indexFila, indexColumna);
            contadorCoordenadas++;
          }
        }
      }

    }
}




