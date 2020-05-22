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
			
			
			System.out.println(minCCW(edges));
			
			
	}
	
	public static int minCCW(ArrayList<Edge> edges) {
		
		int[] label  = {2, 0, 1, 3};
		Cycle s0 = new Cycle(label);
		
		int minCutwidth = (int) Double.POSITIVE_INFINITY;
		
		int s0cutWidth = s0.cutwidth(edges);
		
		minCutwidth = minCutwidth < s0cutWidth ? minCutwidth : s0cutWidth;
		
		
		
		//3. Hace local search y obtenemos s*
		
			// while conditions not met
		
			// 3.1 Hacemos local search y lo asignamos a s*
			
			// 3.2 Perturbamos s*
		
			// 3.3 Se verifican los criterios de termino
		
		// retorna cutwidth de G usando s*
		
		return minCutwidth;
	}
	
	
	public static Cycle localSearch(Cycle[] neighborhood) {
		// Retorna aquel ciclo en el vecindario que tenga menor cutwidth
		
		return null;
	}

}
