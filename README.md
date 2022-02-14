# Sokoban

Una empresa independiente de desarrollo de videojuegos casuales necesita implementar el codigo para manejar la logica de su juego, pero la empresa actualmente no cuenta 
con un equipo de programadores que implementen el codigo de logica del juego, por lo cual la empresa nos ha pedido ayuda para implementar el codigo.

El juego en cuestion es el famoso Sokoban, el cual consiste en que un encargado de un almacén debe ordenar cajas
colocándolas en los puntos designados. El almacén es representado por un tablero compuesto
de cuadrados. Los cuadros pueden representar piso o paredes. Sobre el piso se encuentran las
cajas y las señales que indican la posición donde la caja debe colocarse. 

Para ordenar las cajas, el jugador puede moverse horizontalmente y verticalmente sobre
posiciones de piso vacías o posiciones designadas (que se encuentren vacías). Además, el
jugador puede empujar las cajas siempre y cuando no haya un obstáculo (una pared u otra
caja) que impida mover la caja hacia esa dirección. El jugador no puede jalar una caja,
únicamente empujarla.



## Analisis

La empresa nos pidio desarrollar un programa que verifique el estado del juego cada vez que el jugador realice un movimiento, los aspectos a verificar son:
- Si el jugador ganó el juego.
- Las posiciones actuales de las cajas.
- Las posiciones de las cajas bloqueadas.
- Los movimientos válidos que el jugador puede realizar el próximo turno. 

Para mantener la simplicidad en el codigo la simbologia a utilizar seria la siguiente:

| Simbolo | Significado                                     |
|---------|-------------------------------------------------|
| #       | Pared.                                          |
| X       | Una posicion de caja, con una caja.             |
| *       | Caja sobre cualquier posicion que no sea final. |
| O       | Posicion final sin caja.                        |
| @       | El jugador.                                     |
| .       | Piso del almacen.                               |



## Guia de Usuario

El programa debe leer de la entrada estandar el número de filas, número de columnas,
número de cajas, y un tablero del estado actual del juego, por ejemplo:

5 5 2

| # | # | # | # | # |
|---|---|---|---|---|
| # | * | . | O | # |
| # | X | . | O | # |
| # | . | . | @ | # |
| # | # | # | # | # |

Luego el programa debe realizar las verificaciones requeridas e imprimira un reporte del estado de juego:




|           Reporte de Juego                     |
|------------------------------------------------|
|            Victoria: No                        |
|               Cajas: r01c01 r02c01*            |
|    Cajas bloqueadas: r01c01                    |
| Movimientos validos: N:r02c03 E:- S:- O:r03c02 |

***Victorias:*** El jugador gana cuando todas las cajas esten en posiciones finales, de lo contrario se imprimira "no" en el estado de la victoria.

***Cajas:*** Se refiere a las posiciones actuales de las cajas, siguiendo el formato de "r##c##" mostrado anteriormente, en donde "r##" indica la posicion de fila y
"c##" indica la posicion de columna, ademas si la caja se encuentra en una posicion final se le debe agregar un * al final de la coordenada.

***Cajas bloqueadas:*** Corresponde a las coordenadas de aquellas cajas que no se puedan mover mas y que no se encuentren en una posicion final.

***Movimientos Validos:*** Estos son los movimientos que el jugador podria hacer el siguiente turno. Se deben reportar las cuatro casillas alrededor del jugador
(Norte, Este, Sur, Oeste; en este orden) y si la posicion no es valida se imprime un "-".

Finalmente, si el programa recibe valores faltantes, menores o igual a cero, caracteres o
símbolos en la primera línea, el programa imprimira “entrada invalida”. Si el programa
encuentra en la matriz que representa el tablero, valores faltantes, símbolos u otros caracteres
que no son los que se mencionan en la tabla de simbolos, el programa debe imprimir “matriz invalida”.


### Como correr el programa
Para ejecutar el programa abra la carpeta "libs" (Sokoban\build\libs\) utilizando la consola.
- En windows seria: Boton windows + R
- Escribir cmd.exe y luego run
- Escribir dentro de la consola "cd ..\Sokoban\build\libs"

Luego escribir el comando "java -jar Sokoban-1.0.jar < ../../tests/input000.txt".
Esto ejecutara el caso de prueba deseado.
Si tiene problemas al ejecutar el codigo puede ser que su JDK ocupe actualizar. 

## Creditos

Diego Valladares: diego.valladaresbermudez@ucr.ac.cr

Sebastian Jimenez Camacho: sebastian.jimenezcamacho@ucr.ac.cr


