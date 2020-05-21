import java.util.ArrayList;

public class Cycle {
	// La clase ciclo contiene la permutacion de los vertices enumerados de 1 a n.
	// size: numero de vertices del ciclo
	// content: secuencia de enteros que indican el labeling (permutacion) del ciclo
	
	
	private int size;
	private int[] content;
	
	public Cycle(int[] vertices) {
		this.size = vertices.length;
		this.content = vertices;
	}
	
	public Cycle(int n) {
		this.size = n;
		this.content = new int[n];
		for(int i=0; i<n; i++) {
			content[i] = i;
		}
	}
	
	
	
	public Cycle[] neighborhood() {
		// Retorna un conjunto de ciclos (permutaciones) que son el vecindario. Debe ser en tiempo polinomial
		
		return null;
	}
	
	public void perturbate() {
		// Perturba el ciclo
	}
	
	public int getPos(int u) throws Exception {
		// Dado el vertice u en el candidato, regresa el lugar que ocupa en el ciclo
		for(int i=0; i<this.size; i++) {
			if(this.content[i] == u) return i;
		}
		throw new Exception("No se encontró vértice en H");
	}
	
	public int size() {
		return this.size;
	}
	
	public int[] getContent() {
		return this.content;
	}
	
	public String toString() {
		// toString del ciclo para debug
		
		String s = "";
		
		for(int i=0; i<size; i++) {
			s += content[i];
			if(i != size-1)
			s += " = ";
		}
		
		return s;
	}
	
	public int cutwidth(ArrayList<Edge> edges) {
		//Recibe un array de aristas y calcula el cyclic cutwidth de ese grafo con base en este ciclo 
		
		int cycleEdges[] = new int[this.size()];
		for(int i=0; i<edges.size(); i++) {
			try {
				// Dados el u y v en C, tomo sus posiciones en H.
				int hostU = this.getPos(edges.get(i).u);
				int hostV = this.getPos(edges.get(i).v);
				
				
				if(Math.abs(hostU - hostV) == this.size() - Math.abs(hostU - hostV)) { // Cuando son iguales toma el clockwise
					if(hostU < hostV) {
						for(int j=hostU; j<hostV; j++) {
							cycleEdges[j]++;
						}
					}
					else {
						for(int j=hostV; j<hostU; j++) {
							cycleEdges[j]++;
						}
					}
				}
				else { // Toma el menor costo
					if(Math.abs(hostU - hostV) < this.size() - Math.abs(hostU - hostV)) { //Ruta interior
						if(hostU < hostV) {
							for(int j=hostU; j<hostV; j++) {
								cycleEdges[j]++;
							}
						}
						else {
							for(int j=hostV; j<hostU; j++) {
								cycleEdges[j]++;
							}
						}
					}
					else if(Math.abs(hostU - hostV) > this.size() - Math.abs(hostU - hostV)) { // Ruta exterior
						if(hostU < hostV) {
							for(int j=hostU-1; j>=hostV; j--) {
								cycleEdges[j%4]++;
							}
						}
						else {
							for(int j=hostV; j<hostU; j++) {
								cycleEdges[j%4]++;
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		int maxi =0; // max cutwidth de todas las aristas
		for(int i=0; i<this.size(); i++) {
			maxi = maxi < cycleEdges[i] ? cycleEdges[i] : maxi;
		}
		return maxi;
	}
}
