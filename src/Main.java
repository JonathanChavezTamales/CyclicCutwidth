import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;
import java.lang.Math;

public class Main {

	public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			
			
			int n = sc.nextInt(); // Número de vértices
			
			ArrayList<Edge> edges = new ArrayList<Edge>();
				
			
			while(true) { // Recibe aristas de nodos, (u, v), cuando ya no haya más se escribe -1 -1
				int a = sc.nextInt();
				int b = sc.nextInt();
				if(a==-1 && b == -1) {
					break;
				}
				if(a>=n || b>=n || a<0 || b<0) {
					throw new InputMismatchException("Input inválido");
				}
				edges.add(new Edge(a, b));
			}
			
			int maxIterations = Integer.parseInt(args[0]);
			int maxTimeMilis = Integer.parseInt(args[1]);
			int maxConsecutiveCollisions = Integer.parseInt(args[2]);
			int maxIterationsWithoutChange = Integer.parseInt(args[3]);
			
			
			System.out.println(minCCW(edges, n, maxIterations, maxTimeMilis, maxConsecutiveCollisions, maxIterationsWithoutChange));
			
			
			
	}
	
	public static int minCCW(ArrayList<Edge> edges, int vertices, int maxIterations, int maxTimeMilis, int maxConsecutiveCollisions, int maxIterationsWithoutChange) {
		// Retorna el min cutwidth utilizando hill climbing
		// edges: array de pares de vertices que representan una arista bidireccional
		// vertices: número de vértices del grafo
		// CONDICIONES DE TERMINACIÓN:
		// totalIterations: máximo de iteraciones a ejecutar
		// iterationsWithoutNewMinimum: iteraciones seguidas sin encontrar nuevo mínimo
		// maxPerturbateCollisions: número máximo de permutaciones colisionadas
		
		HashSet<Integer> set = new HashSet<Integer>();
		int consecutiveCollisions = 0;
		int itsWithoutChange = 0;
		long start = System.currentTimeMillis();

		
		Cycle c = new Cycle(vertices); // Empezamos con el primer ciclo c0
		
		
		c = localSearch(c, edges); // Obtiene el minimo del vecindario de c0
		set.add(c.hashCode());
		
		Cycle min = new Cycle(c);
		
		int iter = 0;
		
		while (iter < maxIterations &&  System.currentTimeMillis() - start < maxTimeMilis && consecutiveCollisions < maxConsecutiveCollisions && itsWithoutChange<maxIterationsWithoutChange) { // Condiciones de terminacion
			
			
			c.perturbate();
			set.add(c.hashCode());
			
			
			consecutiveCollisions = 0;
			
			// Si encuentra de nuevo esa permutacion, actualizar contador de colisiones seguidas y perturbar de nuevo.
			while(set.contains(c.hashCode())) { // Preturbate collision
				consecutiveCollisions++;
				c.perturbate();
				if(consecutiveCollisions >= maxConsecutiveCollisions) {
					break;
				}
			}
			
			c = localSearch(c, edges);
			
			// Si no cambia el valor en la iteracion, actualizar contador.
			if(c.getCutwidth() < min.getCutwidth()) {
				min = c;
				itsWithoutChange = 0;
			}
			else{
				itsWithoutChange++;
			}
				
			
			
			System.out.println(iter + "," +min.getCutwidth());
			iter++;
		}
		
		System.out.println(System.currentTimeMillis() - start);
		return min.getCutwidth();
		
	}
	
	
	public static Cycle localSearch(Cycle s, ArrayList<Edge> edges) {
		//Retorna el cycle con el menor cutwidth entre todos sus vecinos
		
		Cycle min = new Cycle(s);
		min.cutwidth(edges);
		boolean foundMin = true;
		
		while(foundMin) {
			foundMin = false;
			Cycle[] neighborhood = s.neighborhood();
					
			//Recorre cada elemento del neighborhood
			for(Cycle neighbor : neighborhood) {
				//Actualiza la solución más pequeña
				if (neighbor.cutwidth(edges) < min.getCutwidth()) {
					min = neighbor;
					foundMin = true;
				}
			}
			s = min;
		}
		
		return s;
	}
	

}
