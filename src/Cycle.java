import java.util.ArrayList;
import java.util.Random;
public class Cycle {
	// La clase ciclo contiene la permutacion de los vertices enumerados de 1 a n.
	// content: secuencia de enteros que indican el labeling (permutacion) del ciclo
	
	private int[] content;
	private int size;
	private int cutwidth = -1;
	
	public Cycle(int[] vertices) {
		this.content = vertices.clone();
		this.size = vertices.length;
	}
	
	public Cycle(int n) {
		this.content = new int[n];
		for(int i=0; i<n; i++) {
			content[i] = i;
		}
		this.size = n;
	}
	
	public Cycle(Cycle c) {
		this.size = c.size();
		this.cutwidth = c.getCutwidth();
		this.content = c.getContent().clone();
	}
	
	
	public Cycle[] neighborhood() {
		// Retorna un conjunto de ciclos (permutaciones) que son el vecindario. Debe ser en tiempo polinomial
		Cycle[] neighbors = new Cycle[size*(size-1)/2];
		
		int[] currentPermutation = this.content;
		int cont = 0;
		for(int i=0; i<this.size()-1; i++) {
			for(int j=i+1; j<this.size(); j++) {
				int[] newPerm = currentPermutation.clone();
				// Swap
				int temp = newPerm[i];
				newPerm[i] = newPerm[j];
				newPerm[j] = temp;
				
				neighbors[cont] = new Cycle(newPerm);
				cont++;
				
			}
		}
		
		return neighbors;
	}
	
	public int hashCode() {
		int hc = 1;
        for(int i=0; i<this.size; i++) {
        	if(i%3 == 0) {
        		hc -= (this.content[i]+1+i)%1000000007;
        	}
        	else if(i%2 == 0) {
        		hc *= (this.content[i]+1+i)%1000000007;
        	}
        	hc %= 1000000007;
        }
        return hc;
    }
	
	public void perturbate() {
		// Perturba el ciclo
		Random rand = new Random();

		int iterations, //numero de veces que se va a perturbar el ciclo
			perturbationForce, 
			displacement;

		iterations = rand.nextInt(10) + 1; //mas 1 para que haga por lo menos una iteracion

		for (int i=0; i<iterations; i++) {
			perturbationForce = 3 + rand.nextInt(this.size()-3 + 1); //+-3 porque la fuerza minima es de 3
			displacement = rand.nextInt(this.size() - perturbationForce + 1); //despalzamiento de indice de inicio
			this.perturbate(perturbationForce, displacement);
		}
	}

	private void perturbate(int perturbationForce, int displacement) {
		int temp;
		for (int i=0; i<perturbationForce/2; i++) { //invierte el bloque especificado del cyclo
			temp = this.content[i+displacement];
			this.content[i+displacement] = this.content[displacement+perturbationForce-i-1];
			this.content[displacement+perturbationForce-i-1] = temp;
		}
	}
	
	public int getPos(int u) throws Exception {
		// Dado el vertice u en el candidato, regresa el lugar que ocupa en el ciclo
		for(int i=0; i<this.size(); i++) {
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
	
	public int getCutwidth() {
		return this.cutwidth;
	}
	
	public String toString() {
		// toString del ciclo para debug
		
		String s = "";
		
		for(int i=0; i<size(); i++) {
			s += content[i];
			if(i != size()-1)
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
		
		this.cutwidth = maxi;
		return maxi;
	}

 
}


