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
	
	public Cycle[] neighborhood() {
		// Retorna un conjunto de ciclos (permutaciones) que son el vecindario. 
		
		return null;
	}
	
	public void perturbate() {
		// Perturba el ciclo
	}
	
	
}
