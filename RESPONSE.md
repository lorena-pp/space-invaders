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

Por este motivo, se consideran dos aproximaciones al problema:

#### (Weak-)Robust-BVT

Se consideran los valores fuera del tablero como entradas inválidas, lo que acota las coordenadas válidas del tablero, a
expensas de realizar ajustes según las dimensiones del sprite, a las siguientes:

- Eje X: [0, 358]
- Eje Y: [0, 350]

> 358 está definido como `Commons.BOARD_WIDTH`.
> 350 está definido como `Commons.BOARD_HEIGHT`.

Por tanto, se definen las clases de prueba, a falta de ajustar según el tamaño del sprite:

| CPs  | X   | Y   |
|------|-----|-----|
| CP1  | 179 | 175 |
| CP2  | -1  | 175 |
| CP3  | 0   | 175 |
| CP4  | 1   | 175 |
| CP5  | 357 | 175 |
| CP6  | 358 | 175 |
| CP7  | 359 | 175 |
| CP8  | 179 | -1  |
| CP9  | 179 | 0   |
| CP10 | 179 | 1   |
| CP11 | 179 | 349 |
| CP12 | 179 | 350 |
| CP13 | 179 | 351 |

> Se define como valor nominal el centro del tablero: (179, 175)

Para ajustar la validez de la entrada en función del tamaño del sprite, se debe tener en cuenta que el origen de
coordenadas se sitúa en la esquina superior izquierda para el sprite y el propio tablero, y crece hacia la derecha y
hacia abajo para los ejes X e Y, respectivamente.

De esta forma, la versión ajustada queda como sigue:

| CPs  | X                             | Y                              |
|------|-------------------------------|--------------------------------|
| CP1  | 179 - CeilDiv(sprite.width/2) | 175 - CeilDiv(sprite.height/2) |
| CP2  | -1                            | 175 - CeilDiv(sprite.height/2) |
| CP3  | 0                             | 175 - CeilDiv(sprite.height/2) |
| CP4  | 1                             | 175 - CeilDiv(sprite.height/2) |
| CP5  | 357 - sprite.width            | 175 - CeilDiv(sprite.height/2) |
| CP6  | 358 - sprite.width            | 175 - CeilDiv(sprite.height/2) |
| CP7  | 359 - sprite.width            | 175 - CeilDiv(sprite.height/2) |
| CP8  | 179 - CeilDiv(sprite.width/2) | -1                             |
| CP9  | 179                           | 0                              |
| CP10 | 179                           | 1                              |
| CP11 | 179                           | 349 - sprite.height            |
| CP12 | 179                           | 350 - sprite.height            |
| CP13 | 179                           | 351 - sprite.height            |

### Weak-Normal-ECT

Esta aproximación parte del hecho de que los constructores de sprites que reciben coordenadas delegan internamente en
una función (init<Sprite>()) que sobrecarga las coordenadas inválidas a los valores válidos más cercanos por sprite. Por
tanto, entradas que quedan fuera de los límites del tablero se ajustan inmediatamente, según un comportamiento
documentado, y sin producir un error en la ejecución.
Se puede considerar que, por esta casuística, que se repite para todas las inicializaciones relativas a
coordenadas, las entradas **no tiene clases inválidas**.

Basándonos en este análisis, consideramos oportuna la aproximación mediante la estrategia *Weak-Robust-ECT*, puesto que,
aunque la entrada es una magnitud física, no está delimitada por un rango definido realmente. El rango válido es total,
pero dentro de él se definen subrangos válidos con comportamientos distintos. De esta manera, se definen las siguientes
clases:

- CE1: Valores menores que el límite inferior del tablero --> sobrecarga a 0
- CE2: Valores comprendidos dentro de los límites del tablero --> conservar valor de entrada
- CE3: Valores mayores que el límite superior del tablero --> sobrecarga a límite máximo (según eje)

Los casos de prueba sin ajustar por sprite quedan como sigue:

| CPs | X   | Y   |
|-----|-----|-----|
| CP1 | 179 | 175 |
| CP2 | -1  | 175 |
| CP3 | 359 | 175 |
| CP4 | 179 | -1  |
| CP5 | 179 | 351 |

#### Especificación para la clase Alien

Los enemigos, por la naturaleza del juego, van desplazándose en el eje vertical hacia la parte inferior de la pantalla
de manera progresiva, amenazando el avance del jugador en la partida. Cuando un enemigo alcanza un límite en dicho eje,
la partida termina, por lo que suena coherente que los enemigos no puedan navegar libremente a partir de dicho punto.
Este límite se establece sobre el tablero, por lo que "recorta" sobre este el espacio navegable para los enemigos en
particular. Por este motivo, se requiere plantear para ellos un Análisis de Casos Especiales para inicializaciones que
traten de colocar directamente un enemigo en la zona restringida (GROUND < Y < BOARD_HEIGHT). Esto podría ser
equivalente a considerar una clase de equivalencia más, que en este caso sí sería inválida.
