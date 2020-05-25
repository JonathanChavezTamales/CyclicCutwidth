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
			
			
			System.out.println(minCCW(edges, n, 10));
			
			
			
	}
	
	public static int minCCW(ArrayList<Edge> edges, int vertices, int totalIterations) {
		
		int[] label  = {2, 0, 1, 3};
		Cycle s0 = new Cycle(label);
		
		int minCutwidth = (int) Double.POSITIVE_INFINITY; //Infinito positivo
		
		int s0cutWidth = s0.cutwidth(edges);
		
		minCutwidth = minCutwidth < s0cutWidth ? minCutwidth : s0cutWidth;
		
		Cycle[] neighborhood = s0.neighborhood();
		
		
		
		
			while(totalIterations > 0) {
		
				//s0 = localSearch(neighborhood, edges) 
			
				//perturbate(s0)
				
				//neighborhood = s0.neighborhood();
				
				//s1 = localSearch(neighborhood, edges);
				
				// if s0.cutwidth <= s1.cutwidth return
				// else s0 = s1;
				

				totalIterations--;
				
			}
		
		// retorna cutwidth de G usando s*
		
		return minCutwidth;
	}
	
	
	public static Cycle localSearch(Cycle[] neighborhood, ArrayList<Edge> edges) {
		// Retorna aquel ciclo en el vecindario que tenga menor cutwidth
		
		return null;
	}

}
