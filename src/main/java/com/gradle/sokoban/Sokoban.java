package com.gradle.sokoban;
import java.util.*;
import java.io.*;
import java.lang.*;

/**
 * Para ejecutar el programa abra la carpeta "libs" (Sokoban\build\libs\) utilizando la consola.
 * En windows seria: Boton windows + R
 *                  Escribir cmd.exe y luego run
 *                  Escribir dentro de la consola "cd ..\Sokoban\build\libs"
 *
 * Luego escribir el comando "java -jar Sokoban-1.0.jar < ../../tests/input000.txt"
 * Esto ejecutara el caso de prueba deseado.
 * Si tiene problemas al ejecutar el codigo puede ser que su JDK ocupe actualizar.
 */


/**
 * Clase que indica si existe o no victoria, las posiciones de las cajas, ya
 * sea resueltas o no, así como si están bloqueadas; también se indica si es
 * posible que el jugador se mueva hacia cada uno de los puntos cardinales y la
 * posición en ese punto cardinal hacia la que se puede mover, según las reglas
 * de Sokoban.
 */

public class Sokoban{

  /**
   * Método que arranca el programa.
   *
   * @param args argumentos del programa.
   */

  Scanner input = null;

  public static void main(String[] args) {

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
   * Ejecuta la lógica del programa; en él se busca un archivo de texto en la
   * dirección proporcionada, si no lo encuentra imprimirá un mensaje de error,
   * de lo contrario el programa leerá el contenido del archivo y con él creará
   * el tablero de juego, luego llamara a los métodos necesarios para resolver
   * el problema.
   */

  public void run() {


    int filas = 0;



    int columnas = 0;



    int cajas = 0;



    String[][] tablero = new String[0][];




      input = new Scanner(System.in);



      try {
        filas = input.nextInt();
        columnas = input.nextInt();
        cajas = input.nextInt();

      }


      catch(InputMismatchException e) {
        System.out.println("Entrada invalida.");
        e.printStackTrace();
        System.exit(0);
      }


      if (filas <= 0 || columnas <= 0 || cajas <= 0){
        System.out.println("Entrada invalida.");
        System.exit(0);
      }



      input.useDelimiter("[\\s]*");



      tablero = new String[filas][columnas];

      boolean matrizValida = true;
      for (int row = 0; row < filas; row++) {
        for (int col = 0; col < columnas; col++) {
          tablero[row][col] = input.next();
          if (!(tablero[row][col].equals("#")
              || tablero[row][col].equals("X")
              || tablero[row][col].equals("O")
              || tablero[row][col].equals("*")
              || tablero[row][col].equals("@")
              || tablero[row][col].equals(".") )) {
            System.out.println("Matriz Invalida.");
            System.exit(0);

          }
        }
      }


      System.out.printf("%10s %n","Tablero");

      for (int row = 0; row < filas; row++) {
        System.out.println("");
        for (int col = 0; col < columnas; col++) {
          System.out.printf("%3s",tablero[row][col]);
        }
      }

      System.out.println("");


      input.close();



    System.out.println("");
    condicionVictoria(filas, columnas, cajas, tablero);
    System.out.println("");
    posicionCajas(filas, columnas, cajas, tablero);
    System.out.println("");
    posicionCajasBloq(filas, columnas, cajas, tablero);
    System.out.println("");
    movimientosValidos(filas, columnas, cajas, tablero);

  }

  /**
   * Método donde se recorre la matriz para encontrar las posiciones en las que
   * están las cajas y guarda sus posiciones, estén resueltas o no.
   *
   * @param filas Recibe un parámetro de tipo {@code int} que indica el número
   *              de filas de la matriz donde se almacenan los caracteres del
   *              tablero.
   * @param columnas Recibe un parámetro de tipo {@code int} que indica el
   *                 número de filas de la matriz donde se almacenan los
   *                 caracteres del tablero.
   * @param cajas Recibe un parámetro de tipo {@code int} que indica el número
   *              de cajas que hay en el tablero.
   * @param tablero Recibe una matriz de tipo {@code String[][]} que contiene
   *                los caracteres del tablero.
   */

  public void posicionCajas(int filas, int columnas, int cajas,
                            String[][] tablero) {

    String[] coordenadasCajas = new String[cajas];

    int contadorCoordenadas = 0;



    for (int indexFila = 0; indexFila < filas; indexFila++) {
      for (int indexColumna = 0; indexColumna < columnas; indexColumna++) {

        if (tablero[indexFila][indexColumna].equals("X")) {
          coordenadasCajas[contadorCoordenadas] =
              String.format("r%02dc%02d* " ,indexFila, indexColumna);
          contadorCoordenadas++;
        }

        else if (tablero[indexFila][indexColumna].equals("*")) {
          coordenadasCajas[contadorCoordenadas] =
              String.format("r%02dc%02d " ,indexFila, indexColumna);
          contadorCoordenadas++;
        }
      }
    }


    System.out.printf("%19s: ", "Cajas");
    for (int i = 0; i < coordenadasCajas.length; i++){
      System.out.print(coordenadasCajas[i]);
    }

  }

  /**
   * Método en donde se recorre el tablero para encontrar las cajas bloqueadas y
   * luego guardarlas en un arreglo, sin contar cajas ya resueltas.
   *
   * @param filas Recibe un parámetro de tipo {@code int} que indica el número
   *              de filas de la matriz donde se almacenan los caracteres del
   *              tablero.
   * @param columnas Recibe un parámetro de tipo {@code int} que indica el
   *                 número de filas de la matriz donde se almacenan los
   *                 caracteres del tablero.
   * @param cajas Recibe un parámetro de tipo {@code int} que indica el número
   *              de cajas que hay en el tablero.
   * @param tablero Recibe una matriz de tipo {@code String[][]} que contiene
   *                los caracteres del tablero.
   */

  public void posicionCajasBloq(int filas, int columnas, int cajas,
                                String[][] tablero){



    int cajasBloq = 0;
    for (int row = 0; row < filas; row++){
      for (int col = 0; col < columnas; col++){
        if (tablero[row][col].equals("*")){
          cajasBloq++;
        }
      }
    }

    String [] coordenadaCajasBloq = new String [cajasBloq];



    int contadorCajasBloq = 0;

    for (int row = 0; row < filas; row++){
      for (int col = 0; col < columnas; col++){

        if (tablero[row][col].equals("*")){

          if (tablero[row-1][col].equals("#")
              && (tablero[row][col+1].equals("#")
              || tablero[row][col-1].equals("#"))){
            coordenadaCajasBloq[contadorCajasBloq] =
                String.format("r%02dc%02d ",row, col);
            contadorCajasBloq++;
          }
          else if (tablero[row+1][col].equals("#")
              && (tablero[row][col+1].equals("#")
              || tablero[row][col-1].equals("#"))) {
            coordenadaCajasBloq[contadorCajasBloq] =
                String.format("r%02dc%02d ", row, col);
            contadorCajasBloq++;
          }

          if ((tablero[row-1][col].equals("*")
              || tablero[row-1][col].equals("X"))
              && ((tablero[row][col+1].equals("*")
              || (tablero[row][col+1].equals("X")))
              || (tablero[row][col-1].equals("*")
              || tablero[row][col-1].equals("X")))){
            coordenadaCajasBloq[contadorCajasBloq] =
                String.format("r%02dc%02d ",row, col);
            contadorCajasBloq++;
          }
          else if ((tablero[row+1][col].equals("*")
              || tablero[row+1][col].equals("X"))
              && ((tablero[row][col+1].equals("*")
              || (tablero[row][col+1].equals("X")))
              || (tablero[row][col-1].equals("*")
              || tablero[row][col-1].equals("X")))) {
            coordenadaCajasBloq[contadorCajasBloq] =
                String.format("r%02dc%02d ", row, col);
            contadorCajasBloq++;
          }

        }
      }
    }



    System.out.printf("%19s: ", "Cajas Bloqueadas");
    for (int i = 0; i < coordenadaCajasBloq.length; i++) {
      if (coordenadaCajasBloq[i] == null)
            ;
      else{
        System.out.print(coordenadaCajasBloq[i]);
      }
    }

  }

  /**
   * Método donde se recorre la matriz para encontrar la posición del jugador,
   * y luego fijarse en cuáles movimientos (NORTE, ESTE, SUR, OESTE; en ese
   * orden) son válidos.
   *
   * @param filas Recibe un parámetro de tipo {@code int} que indica el número
   *              de filas de la matriz donde se almacenan los caracteres del
   *              tablero.
   * @param columnas Recibe un parámetro de tipo {@code int} que indica el
   *                 número de filas de la matriz donde se almacenan los
   *                 caracteres del tablero.
   * @param cajas Recibe un parámetro de tipo {@code int} que indica el número
   *              de cajas que hay en el tablero.
   * @param tablero Recibe una matriz de tipo {@code String[][]} que contiene
   *                los caracteres del tablero.
   */

  public void movimientosValidos (int filas, int columnas, int cajas,
                                  String[][] tablero){


    String [] movimientosVal = new String[4];

    for (int row = 0; row < filas; row++){
      for (int col = 0; col < columnas; col++){
        if (tablero[row][col].equals("@")){

          if (tablero[row-1][col].equals(".")
              || tablero[row-1][col].equals("O")
              || tablero[row-1][col].equals("X")
              || tablero[row-1][col].equals("*")){
            movimientosVal[0] = String.format("r%02dc%02d ",row-1, col);
          }
          else{
            movimientosVal[0] = "-";
          }

          if (tablero[row][col+1].equals(".")
              || tablero[row][col+1].equals("O")
              || tablero[row][col+1].equals("X")
              || tablero[row][col+1].equals("*")){
            movimientosVal[1] = String.format("r%02dc%02d ",row, col+1);
          }
          else{
            movimientosVal[1] = "-";
          }

          if (tablero[row+1][col].equals(".")
              || tablero[row+1][col].equals("O")
              || tablero[row+1][col].equals("X")
              || tablero[row+1][col].equals("*")){
            movimientosVal[2] = String.format("r%02dc%02d ",row+1, col);
          }
          else{
            movimientosVal[2] = "-";
          }

          if (tablero[row][col-1].equals(".")
              || tablero[row][col-1].equals("O")
              || tablero[row][col-1].equals("X")
              || tablero[row][col-1].equals("*")){
            movimientosVal[3] = String.format("r%02dc%02d ",row, col-1);
          }
          else{
            movimientosVal[3] = "-";
          }

          if ((tablero[row - 1][col].equals("*")
              || tablero[row-1][col].equals("X"))
              && (tablero[row-2][col].equals("*")
              || tablero[row-2][col].equals("X")
              || tablero[row-2][col].equals("#")) ){
            movimientosVal[0] = "-";
          }
          else if ((tablero[row - 1][col].equals("*")
              || tablero[row-1][col].equals("X"))
              && (tablero[row-2][col].equals(".")
              || tablero[row-2][col].equals("O"))){
            movimientosVal[0] = String.format("r%02dc%02d ",row-1, col);
          }

          if ((tablero[row ][col+1].equals("*")
              || tablero[row][col+1].equals("X"))
              && (tablero[row][col+2].equals("*")
              || tablero[row][col+2].equals("X")
              || tablero[row][col+2].equals("#")) ){
            movimientosVal[1] = "-";
          }
          else if ((tablero[row ][col+1].equals("*")
              || tablero[row][col+1].equals("X"))
              && (tablero[row][col+2].equals(".")
              || tablero[row][col+2].equals("O"))){
            movimientosVal[1] = String.format("r%02dc%02d ",row, col+1);
          }

          if ((tablero[row + 1][col].equals("*")
              || tablero[row+1][col].equals("X"))
              && (tablero[row+2][col].equals("*")
              || tablero[row+2][col].equals("X")
              || tablero[row+2][col].equals("#"))){
            movimientosVal[2] = "-";
          }
          else if ((tablero[row + 1][col].equals("*")
              || tablero[row+1][col].equals("X"))
              && (tablero[row+2][col].equals(".")
              || tablero[row+2][col].equals("O"))){
            movimientosVal[3] = String.format("r%02dc%02d ",row+1, col);
          }

          if ((tablero[row][col-1].equals("*")
              || tablero[row][col-1].equals("X"))
              && (tablero[row][col-2].equals("*")
              || tablero[row][col-2].equals("X")
              || tablero[row][col-2].equals("#"))){
            movimientosVal[3] = "-";
          }
          else if ((tablero[row][col-1].equals("*")
              || tablero[row][col-1].equals("X"))
              && (tablero[row][col-2].equals(".")
              || tablero[row][col-2].equals("O"))){
            movimientosVal[3] = String.format("r%02dc%02d ",row, col-1);
          }
        }
      }
    }

    System.out.printf("Movimientos Validos: N:%s E:%s S:%s O:%s",
        movimientosVal[0],movimientosVal[1], movimientosVal[2],
        movimientosVal[3]);
  }

  /**
   * Método que analiza si se cumplen las condiciones para ganar el juego según
   * las reglas del Sokoban.
   *
   * @param filas Recibe un parámetro de tipo {@code int} que indica el número
   *              de filas de la matriz donde se almacenan los caracteres del
   *              tablero.
   * @param columnas Recibe un parámetro de tipo {@code int} que indica el
   *                 número de filas de la matriz donde se almacenan los
   *                 caracteres del tablero.
   * @param cajas Recibe un parámetro de tipo {@code int} que indica el número
   *              de cajas que hay en el tablero.
   * @param tablero Recibe una matriz de tipo {@code String[][]} que contiene
   *                los caracteres del tablero.
   */
  public void condicionVictoria (int filas, int columnas, int cajas,
                                 String [][] tablero) {

    boolean victoria = true;

    for (int row = 0; row < filas; row++) {
      for (int col = 0; col < columnas; col++) {
        if (tablero[row][col].equals("O")) {
          victoria = false;
        }
      }
    }
  
    if (victoria == false)
      System.out.printf("%19s: %s", "Victoria", "No");

    else if (victoria == true)
      System.out.printf("%19s: %s", "Victoria", "Si");

  }



}




