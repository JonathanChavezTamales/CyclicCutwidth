# Cyclic Cutwidth Minimization


Se puede ver una explicación en video aquí:
(https://www.youtube.com/watch?v=zLtWxIXkW6I)[Cyclic Cutwidth Minimization - Jonathan Chávez - Youtube]
(https://www.youtube.com/watch?v=3IVKrpaZZdo)[Cyclic Cutwidth Minimization - Carol Pérez - Youtube]

# Instrucciones de compilación

Los archivos fuente están en `src`. Para compilar corra `javac src/*.java -d bin/`.

Para ejecutar, vaya a `bin/` y ejecute en terminal `java Main`.

Si se quiere usar una instancia ejecute de la siguiente manera: `java Main < ../instancias/1.txt PARAM 1 PARAM2 PARAM3 PARAM4`.

Adicionalmente se le tienen que pasar los siguientes parametros en ese orden:

- maxIteration
- maxTimeMilis
- maxConsecutiveCollision
- maxIterationsWithoutChange

Ejemplo: `java Main < ../instancias/HarwellBoeing/small/bcspwr01/bcspwr01.mtx.rnd2 100 180000 10 600`
