package com.gradle.sokoban;
import java.util.*;
import java.io.*;
import java.lang.*;

/**
 * Clase que indica si existe o no victoria, las posiciones de las cajas, ya
 * sea resueltas o no, así como si están bloqueadas; también se indica si es
 * posible que el jugador se mueva hacia cada uno de los puntos cardinales y la
 * posición en ese punto cardinal hacia la que se puede mover, según las reglas
 * de Sokoban.
 */
public class Sokoban
{
  /**
   * Obtiene datos de la entrada estándar.
   */
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
      String[][] tablero = new String[filas][columnas];
      for (int indexFila = 0; indexFila < filas; indexFila++) {
        for (int indexColumna = 0; indexColumna < columnas; indexColumna++) {
          tablero[indexFila][indexColumna] = this.input.next();
        }
      }

      posicionCajas(filas, columnas, cajas, tablero);
      input.close();
    }





    public void posicionCajas(int filas, int columnas, int cajas,
                              String[][] tablero) {
      String[] coordenadasCajas = new String[cajas];
      int contadorCoordenadas = 0;
      for (int indexFila = 0; indexFila < filas; indexFila++) {
        for (int indexColumna = 0; indexColumna < columnas; indexColumna++) {
          if (tablero[indexFila][indexColumna].equals("X")) {
            coordenadasCajas[contadorCoordenadas] =
                String.format("r0%dc0%d* " ,indexFila, indexColumna);
            contadorCoordenadas++;
          } else if (tablero[indexFila][indexColumna].equals("*")) {
            coordenadasCajas[contadorCoordenadas] =
                String.format("r0%dc0%d " ,indexFila, indexColumna);
            contadorCoordenadas++;
          }
        }
      }

    }
}




