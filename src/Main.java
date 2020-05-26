import java.util.ArrayList;
import java.util.HashMap;
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
			
			
			System.out.println(minCCW(edges, n, 13, 10, 5));
			
			
			
	}
	
	public static int minCCW(ArrayList<Edge> edges, int vertices, int maxIterations, int maxIterationsWithoutNewMinimum, int maxPerturbateCollisions) {
		// Retorna el min cutwidth utilizando hill climbing
		// edges: array de pares de vertices que representan una arista bidireccional
		// vertices: número de vértices del grafo
		// CONDICIONES DE TERMINACIÓN:
		// totalIterations: máximo de iteraciones a ejecutar
		// iterationsWithoutNewMinimum: iteraciones seguidas sin encontrar nuevo mínimo
		// maxPerturbateCollisions: número máximo de permutaciones colisionadas
		
		
		Cycle c = new Cycle(vertices); // Empezamos con el primer ciclo c0
		
		int currentIterationsWithoutNewMinimum = 0;
		int currentPerturbateCollisions = 0;
		
		c = localSearch(c, edges); // Obtiene el minimo del vecindario de c0
		
		Cycle min = new Cycle(c);
		
		
		while (maxIterations > 0 && currentIterationsWithoutNewMinimum < maxIterationsWithoutNewMinimum  && currentPerturbateCollisions < maxPerturbateCollisions) { // Condiciones de terminacion
			
			
			
			c = localSearch(c, edges);
						
			if(c.getCutwidth() >= min.getCutwidth()) {
				currentIterationsWithoutNewMinimum++;
				c.perturbate();
			}
			else {
				min = c;
			}
			
			// Implementar termino por colision
			
			
			
			maxIterations--;
		}
		
		
		if(maxIterations == 0) {
			System.out.println("Terminado por maxIter");
		}
		if(currentIterationsWithoutNewMinimum == maxIterationsWithoutNewMinimum) {
			System.out.println("Terminado por maxIterationsWithoutNewMinimum");
		}
		if(currentPerturbateCollisions == maxPerturbateCollisions) {
			System.out.println("Terminado por maxPerturbateCollisions");
		}
		
		return min.getCutwidth();
		
	}
	
	
	public static Cycle localSearch(Cycle s, ArrayList<Edge> edges) {
		//Retorna el cycle con el menor cutwidth entre todos sus vecinos
		
		Cycle min = new Cycle(s);
		min.cutwidth(edges);
		Cycle[] neighborhood = s.neighborhood();
				
		//Recorre cada elemento del neighborhood
		for(Cycle neighbor : neighborhood) {
			//Actualiza la solución más pequeña
			if (neighbor.cutwidth(edges) < min.getCutwidth()) {
				min = neighbor;
			}
		}
		return min;
	}
	

}
