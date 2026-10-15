# Práctica Verificación y Validación

## Pruebas de Caja Negra

### Consideraciones para los constructores

Todos los sprites tienen un constructor que recibe como parámetros las coordenadas de inicialización del sprite.
Para la clase Shot, la documentación define específicamente un comportamiento de saturación de coordenadas: para
entradas fuera del límite del tablero, se saturarán de forma independiente para cada eje a la coordenada más cercana que
sí se encuentre dentro del tablero.

En la práctica:

- Para coordenadas menores al límite inferior del tablero por ese eje, la coordenada se ajusta de forma automática a la 
coordenada menor en ese eje del tablero (0 para ambos ejes)
- Para coordenadas mayores al límite superior del tablero para ese eje, la coordenada se ajusta de forma automática a la
coordenada mayor en ese eje del tablero (definidos como constantes en la clase Commons)

Por este motivo, hemos considerado que esta casuística, que se repite para todas las inicializaciones relativas a 
coordenadas, **no tiene clases inválidas**. Esta afirmación se apoya en que el comportamiento del programa para estos 
valores de entrada está considerado y especificado en la documentación, y no constituyen un error en la ejecución.

Basándonos en este análisis, consideramos oportuna la aproximación mediante la estrategia *Weak-Robust-ECT*, definiendo 
las siguientes clases:

- CP1: Valores menores que el límite inferior del tablero --> sobrecarga a 0
- CP2: Valores comprendidos dentro de los límites del tablero --> conservar valor de entrada
- CP3: Valores mayores que el límite superior del tablero --> sobrecarga a límite máximo (según eje)

También podría considerarse otra postura, donde los valores que no pertenecen a los límites definidos por el tablero se
tomen como inválidos, y, por tanto, podría justificarse un enfoque mediante *(Weak-)Robust-BVT*.

#### Especificación para la clase Alien

Los enemigos, por la naturaleza del juego, van desplazándose en el eje vertical hacia la parte inferior de la pantalla 
de manera progresiva, amenazando el avance del jugador en la partida. Cuando un enemigo alcanza un límite en dicho eje, 
la partida termina. Este límite se establece sobre el tablero, por lo que "recorta" el espacio útil para los enemigos en
particular. Por este motivo, se requiere plantear para ellos un Análisis de Casos Especiales para inicializaciones que 
traten de colocar directamente un enemigo en la zona restringida (GROUND < Y < BOARD_HEIGHT).
