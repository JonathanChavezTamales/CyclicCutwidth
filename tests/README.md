# Experimentos

---

## Guia de Uso

Para generar 10 tests de los grafos que se encuentran en el formato rn2 (el script recibe un numero de grafos infinitos):

* `python3 tests_generator.py nombre_grafo1 ruta_al_grafo_n ... nombre_grafo_* ruta_al_grafo_*`

    *Ejemplo:*

    * `python3 tests_generator.py dwt__234 ../instancias/HarwellBoeing/small/dwt__234/dwt__234.mtx.rnd2` 

Para obtener las estadisticas de los tests

* `python3 stats_collector grafo1 ... grafo*`

    *Ejemplo:*

    * `python3 stats_collector.py bcspwr02 bcspwr03 bcsstk01 can_24`
