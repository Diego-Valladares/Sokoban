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

    /**
     * Variable que indica el número de filas que tiene el tablero.
     */

    int filas = 0;

    /**
     * Variable que indica el número de columnas que tiene el tablero.
     */

    int columnas = 0;

    /**
     * Variable que indica el número de cajas que hay en el tablero.
     */

    int cajas = 0;

    /**
     * Variable estilo matriz que contiene los datos del tablero actual.
     */

    String[][] tablero = new String[0][];

    /**
     * El programa intenta leer el archivo .txt en la direccion proporcionada.
     * Si el programa detecta y lee los datos con exito, continua.
     */



      /**
       * Lee los archivos de la entrada estandar.
       */

      input = new Scanner(System.in);

      /**
       * Se toman los valores "int" al principio del archivo.
       */

      try {
        filas = input.nextInt();
        columnas = input.nextInt();
        cajas = input.nextInt();

      }

      /**
       * Si los valores no son int, imprime un mensaje de error y detiene el programa.
       */

      catch(InputMismatchException e) {
        System.out.println("Entrada invalida.");
        e.printStackTrace();
        System.exit(0);
      }

      /**
       * También si los valores son 0 o negativos imprime un mensaje de error y
       * cierra el programa.
       */

      if (filas <= 0 || columnas <= 0 || cajas <= 0){
        System.out.println("Entrada invalida.");
        System.exit(0);
      }

      /**
       * Delimitador para ignorar espacios en blanco o vacíos.
       */

      input.useDelimiter("[\\s]*");

      /**
       * Se crea en tablero con las dimensiones válidas.
       */

      tablero = new String[filas][columnas];

      /**
       * Ciclo para agregar los datos al tablero.
       */
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

      /**
       * Ciclo para imprimir el tablero, asi el usuario podrá visualizarlo.
       */
      System.out.printf("%10s %n","Tablero");

      for (int row = 0; row < filas; row++) {
        System.out.println("");
        for (int col = 0; col < columnas; col++) {
          System.out.printf("%3s",tablero[row][col]);
        }
      }

      System.out.println("");

      /**
       * Cierra el input debido a que ya no se necesita.
       */
      input.close();

    /**
     * Si el programa no detecta un archivo .txt válido imprime un mensaje de
     * error.
     */

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
    /**
     * Variable de tipo arreglo en donde se guardan las coordenadas de las cajas.
     */

    String[] coordenadasCajas = new String[cajas];
    /**
     * Variable que sirve de contador.
     */
    int contadorCoordenadas = 0;

    /**
     * Ciclo en donde se recorre el tablero en busqueda de cajas.
     */

    for (int indexFila = 0; indexFila < filas; indexFila++) {
      for (int indexColumna = 0; indexColumna < columnas; indexColumna++) {
        /**
         * Si se encuentra a la "X" que es una posición de una caja ya resuelta,
         * la coordenada se guarda en "coordenadasCajas" con un "*" al final
         * y se aumenta por 1 la variable "contadorCoordenadas".
         */
        if (tablero[indexFila][indexColumna].equals("X")) {
          coordenadasCajas[contadorCoordenadas] =
              String.format("r%02dc%02d* " ,indexFila, indexColumna);
          contadorCoordenadas++;
        }
        /**
         * Si se encuentra a la "*" que es una posición de una caja sin resolver,
         * la coordenada se guarda en "coordenadasCajas" sin ningun caracter extra
         * y se aumenta por 1 la variable "contadorCoordenadas".
         */
        else if (tablero[indexFila][indexColumna].equals("*")) {
          coordenadasCajas[contadorCoordenadas] =
              String.format("r%02dc%02d " ,indexFila, indexColumna);
          contadorCoordenadas++;
        }
      }
    }

    /**
     * Ciclo para imprimir las coordenadas de las cajas.
     */

    System.out.printf("%19s: ", "Cajas");
    for (int i = 0; i < coordenadasCajas.length; i++){
      System.out.print(coordenadasCajas[i]);
    }

  }

  /**
   * Metodo en donde se recorre el tablero para encontrar las cajas bloqueadas y
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

  public void posicionCajasBloq(int filas, int columnas, int cajas, String[][] tablero){

    /**
     * Ciclo para determinar la cantidad de cajas bloqueadas en el tablero.
     */

    int cajasBloq = 0;
    for (int row = 0; row < filas; row++){
      for (int col = 0; col < columnas; col++){
        if (tablero[row][col].equals("*")){
          cajasBloq++;
        }
      }
    }

    /**
     * Arreglo para almacenar las coordenadas de las cajas bloqueadas.
     */
    String [] coordenadaCajasBloq = new String [cajasBloq];

    /**
     * Variable de estilo contador.
     */

    int contadorCajasBloq = 0;

    /**
     * Ciclo para recorrer el tablero en busqueda de cajas bloqueadas.
     */
    for (int row = 0; row < filas; row++){
      for (int col = 0; col < columnas; col++){
        /**
         * Si la coordenada actual es una caja sin resolver continua.
         */
        if (tablero[row][col].equals("*")){
          /**
           * Si la coordenada actual tiene una pared ARRIBA y una pared DERECHA o IZQUIERDA
           * Significa que esta bloqueada, por lo que se agrega la coordenada al
           * arreglo que contiene las coordenadas y se le suma 1 al contador.
           */
          if (tablero[row-1][col].equals("#")
              && (tablero[row][col+1].equals("#")
              || tablero[row][col-1].equals("#"))){
            coordenadaCajasBloq[contadorCajasBloq] =
                String.format("r%02dc%02d ",row, col);
            contadorCajasBloq++;
          }
          /**
           * Si la coordenada actual tiene una pared ABAJO y una pared DERECHA o IZQUIERDA
           * Significa que esta bloqueada, por lo que se agrega la coordenada al
           * arreglo que contiene las coordenadas y se le suma 1 al contador.
           */
          else if (tablero[row+1][col].equals("#")
              && (tablero[row][col+1].equals("#")
              || tablero[row][col-1].equals("#"))) {
            coordenadaCajasBloq[contadorCajasBloq] =
                String.format("r%02dc%02d ", row, col);
            contadorCajasBloq++;
          }

          if ((tablero[row-1][col].equals("*") || tablero[row-1][col].equals("X"))
              && ((tablero[row][col+1].equals("*") || (tablero[row][col+1].equals("X")))
              || (tablero[row][col-1].equals("*") || tablero[row][col-1].equals("X")))){
            coordenadaCajasBloq[contadorCajasBloq] =
                String.format("r%02dc%02d ",row, col);
            contadorCajasBloq++;
          }
          else if ((tablero[row+1][col].equals("*") || tablero[row+1][col].equals("X"))
              && ((tablero[row][col+1].equals("*") || (tablero[row][col+1].equals("X")))
              || (tablero[row][col-1].equals("*") || tablero[row][col-1].equals("X")))) {
            coordenadaCajasBloq[contadorCajasBloq] =
                String.format("r%02dc%02d ", row, col);
            contadorCajasBloq++;
          }

        }
      }
    }

    /**
     * Ciclo para imprimir las coordenadas de las bloqueadas.
     */

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
   * Metodo donde se recorre la matriz para encontrar la posicion del jugador, y luego
   * fijarse en cuales movimientos (NORTE, ESTE, SUR, OESTE; en ese orden) son validos.
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

  public void movimientosValidos (int filas, int columnas, int cajas, String[][] tablero){

    /**
     * Variable que crea un arreglo donde se almacenarán las coordenadas de los
     * 4 puntos cardinales hacia las que se podría mover el jugador; si no es
     * posible que se mueva a algún punto, se almacena "-".
     */
    String [] movimientosVal = new String[4];
    /**
     * Doble ciclo for que recorre la matriz y, si se encuentra el símbolo "@",
     * que identifica al jugador, procede con el siguiente con el if de debajo.
     */
    for (int row = 0; row < filas; row++){
      for (int col = 0; col < columnas; col++){
        if (tablero[row][col].equals("@")){
          /**
           * If que analiza la posición de la matriz inmediatamente superior a
           * la posición del jugador, si lo que tiene almacenado es igual a ".",
           * "O", "X" o "*", se guardan sus coordenadas con el formato de salida
           * en el arreglo "movimientosVal"; si esto no se da, se guarda "-".
           */
          if (tablero[row-1][col].equals(".")
              || tablero[row-1][col].equals("O")
              || tablero[row-1][col].equals("X")
              || tablero[row-1][col].equals("*")){
            movimientosVal[0] = String.format("r%02dc%02d ",row-1, col);
          }
          else{
            movimientosVal[0] = "-";
          }
          /**
           * If que analiza la posición de la matriz inmediatamente a la derecha
           * de la posición del jugador, si lo que tiene almacenado es igual a
           * ".", "O", "X" o "*", se guardan sus coordenadas con el formato de
           * salida en el arreglo "movimientosVal"; si esto no se da, se guarda
           * "-".
           */
          if (tablero[row][col+1].equals(".")
              || tablero[row][col+1].equals("O")
              || tablero[row][col+1].equals("X")
              || tablero[row][col+1].equals("*")){
            movimientosVal[1] = String.format("r%02dc%02d ",row, col+1);
          }
          else{
            movimientosVal[1] = "-";
          }
          /**
           * If que analiza la posición de la matriz inmediatamente inferior a
           * la posición del jugador, si lo que tiene almacenado es igual a ".",
           * "O", "X" o "*", se guardan sus coordenadas con el formato de salida
           * en el arreglo "movimientosVal"; si esto no se da, se guarda "-".
           */
          if (tablero[row+1][col].equals(".")
              || tablero[row+1][col].equals("O")
              || tablero[row+1][col].equals("X")
              || tablero[row+1][col].equals("*")){
            movimientosVal[2] = String.format("r%02dc%02d ",row+1, col);
          }
          else{
            movimientosVal[2] = "-";
          }
          /**
           * If que analiza la posición de la matriz inmediatamente a la
           * izquierda de la posición del jugador, si lo que tiene almacenado es
           * igual a ".", "O", "X" o "*", se guardan sus coordenadas con el
           * formato de salida en el arreglo "movimientosVal"; si esto no se da,
           * se guarda "-".
           */
          if (tablero[row][col-1].equals(".")
              || tablero[row][col-1].equals("O")
              || tablero[row][col-1].equals("X")
              || tablero[row][col-1].equals("*")){
            movimientosVal[3] = String.format("r%02dc%02d ",row, col-1);
          }
          else{
            movimientosVal[3] = "-";
          }
          /**
           * If que analiza la posición de la matriz inmediatamente superior a
           * la de la del jugador y la que está encima de esta, donde "*" es una
           * caja no resuelta, "X" es una resuelta y "#" es una pared del borde
           * del tablero; si alguno de estos ocupa la posición inmediatamente
           * superior a la del jugador, y se da lo mismo con la posición encima
           * de esa, se guarda "-" en el arreglo "movimientosVal" y, sino se
           * guarda la ubicación con el formato de salida.
           */
          if ((tablero[row - 1][col].equals("*") || tablero[row-1][col].equals("X"))
              && (tablero[row-2][col].equals("*")
              || tablero[row-2][col].equals("X")
              || tablero[row-2][col].equals("#")) ){
            movimientosVal[0] = "-";
          }
          else{
            movimientosVal[0] = String.format("r%02dc%02d ",row-1, col);
          }
          /**
           * If que analiza la posición de la matriz inmediatamente a la derecha
           * a la de la del jugador y la que está encima de esta, donde "*" es
           * una caja no resuelta, "X" es una resuelta y "#" es una pared del
           * borde del tablero; si alguno de estos ocupa la posición
           * inmediatamente superior a la del jugador, y se da lo mismo con la
           * posición encima de esa, se guarda "-" en el arreglo
           * "movimientosVal" y, sino se guarda la ubicación con el formato de
           * salida.
           */
          if ((tablero[row ][col+1].equals("*") || tablero[row][col+1].equals("X"))
              && (tablero[row][col+2].equals("*")
              || tablero[row][col+2].equals("X")
              || tablero[row][col+2].equals("#")) ){
            movimientosVal[1] = "-";
          }
          else{
            movimientosVal[1] = String.format("r%02dc%02d ",row, col+1);
          }
          /**
           * If que analiza la posición de la matriz inmediatamente inferior a
           * la de la del jugador y la que está encima de esta, donde "*" es una
           * caja no resuelta, "X" es una resuelta y "#" es una pared del borde
           * del tablero; si alguno de estos ocupa la posición inmediatamente
           * superior a la del jugador, y se da lo mismo con la posición encima
           * de esa, se guarda "-" en el arreglo "movimientosVal" y, sino se
           * guarda la ubicación con el formato de salida.
           */
          if ((tablero[row + 1][col].equals("*") || tablero[row+1][col].equals("X"))
              && (tablero[row+2][col].equals("*")
              || tablero[row+2][col].equals("X")
              || tablero[row+2][col].equals("#"))){
            movimientosVal[2] = "-";
          }
          else{
            movimientosVal[2] = String.format("r%02dc%02d ",row+1, col);
          }
          /**
           * If que analiza la posición de la matriz inmediatamente a la
           * izquierda a la de la del jugador y la que está encima de esta,
           * donde "*" es una caja no resuelta, "X" es una resuelta y "#" es una
           * pared del borde del tablero; si alguno de estos ocupa la posición
           * inmediatamente superior a la del jugador, y se da lo mismo con la
           * posición encima de esa, se guarda "-" en el arreglo
           * "movimientosVal" y, sino se guarda la ubicación con el formato de
           * salida.
           */
          if ((tablero[row][col-1].equals("*") || tablero[row][col-1].equals("X"))
              && (tablero[row][col-2].equals("*")
              || tablero[row][col-2].equals("X")
              || tablero[row][col-2].equals("#"))){
            movimientosVal[3] = "-";
          }
          else{
            movimientosVal[3] = String.format("r%02dc%02d ",row, col-1);
          }


        }
      }
    }
    /**
     * Se imprimen las coordenadas o guiones del arreglo "movimientosVal"
     * correspondientes a cada punto cardinal.
     */
    System.out.printf("Movimientos Validos: N:%s E:%s S:%s O:%s",movimientosVal[0],movimientosVal[1],
        movimientosVal[2], movimientosVal[3]);
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
  public void condicionVictoria (int filas, int columnas, int cajas, String [][] tablero) {
    /**
     * Variable que identifica si la condición para que el jugador gane el
     * juego es verdadera o falsa(empieza siendo verdadera).
     */
    boolean victoria = true;
    /**
     * Par de ciclos for que recorren la matriz del tablero del Sokoban, si se
     * cumple la condición, la variable de victoria cambia a falsa.
     */
    for (int row = 0; row < filas; row++) {
      for (int col = 0; col < columnas; col++) {
        if (tablero[row][col].equals("O")) {
          victoria = false;
        }
      }
    }
    /**
     * If que indica que si la condición de victoria es falsa, se imprime
     * "Victoria: No" y si es verdadera se imprime "Victoria: Sí".
     */
    if (victoria == false)
      System.out.printf("%19s: %s", "Victoria", "No");

    else if (victoria == true)
      System.out.printf("%19s: %s", "Victoria", "Si");

  }



}




