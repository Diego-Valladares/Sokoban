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

  /**
   * Método que arranca el programa.
   *
   * @param args argumentos del programa.
   */
    public static void main (String[] args) {

      /**
       * Creación de nueva instancia de la clase Sokoban.
       */
      Sokoban sokoban = new Sokoban();

      /**
       * Llamado del método "run" para iniciar con la funcionalidad del
       * programa.
       */
      sokoban.run();
    }

  /**
   * Ejecuta la lógica del programa; en él se leen los números introducidos
   * en la entrada estándar y, con eso forma la matriz y se llena con los
   * caracteres que fueron introducidos después en la entrada estándar, luego
   * se llama al método "posicionCajas".
   */
  public void run() {
    /**
     * Instancia de clase Scanner donde se leen los datos de la entrada
     * estándar con delimitador que permite ir carácter por carácter sin tomar
     * en cuenta saltos de línea.
     */
      input = new Scanner(System.in);
      this.input.useDelimiter("[\\s]*");

    /**
     * Variable que indica el número de filas que tiene la matriz.
     */
      int filas = this.input.nextInt();
    /**
     * Variable que indica el número de columnas que tiene la matriz.
     */
      int columnas = this.input.nextInt();
    /**
     * Variable que indica el número de cajas que tiene la partida.
     */
      int cajas = this.input.nextInt();
    /**
     * Variable que indica la creación de la matriz que almacena los caracteres
     * del tablero del juego Sokoban.
     */
      String[][] tablero = new String[filas][columnas];
    /**
     * Dos ciclos for: el primero de filas y el segundo de columnas, en donde
     * se leen los caracteres del tablero y la matriz se llena con ellos.
     */
      for (int indexFila = 0; indexFila < filas; indexFila++) {
        for (int indexColumna = 0; indexColumna < columnas; indexColumna++) {
          /**
           * Variable que indica que la posición marcada por el actual número
           * de filas y el actual número de columnas contienen el siguiente
           * string, que debido al delimitador usado, corresponde a un carácter.
           */
          tablero[indexFila][indexColumna] = this.input.next();
        }
      }

    /**
     * Invocación del método posicionCajas, que recibe los parámetros: "filas",
     * "columnas", "cajas" y "tablero".
     */
      posicionCajas(filas, columnas, cajas, tablero);
    /**
     * Finaliza lo relacionado con input.
     */
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




