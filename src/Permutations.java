public class Permutations {

    private int[][] permutations;
    private int[] a; //array de ints
    private int counter; //Para contar el numero de permutaciones calculadas

    public Permutations(int[] a) {
        this.a = a;
        this.permutations = new int[this.getFactorial(a.length)][a.length];
        this.counter = 0;
        this.heapPermutation(this.a.length, this.a.length);
    }

    private int getFactorial(int n) {
        if (n <= 1) return 1;
        return n * getFactorial(n-1);
    }

    private void swap(int x, int size) {
        int temp = this.a[x];
        this.a[x] = this.a[size-1];
        this.a[size-1] = temp;
    }

    //Generar permutaciones random con Heap Algotithm
    public void heapPermutation(int size, int n) { 
        if (size == 1) this.permutations[this.counter++] = this.a.clone(); //caso base, cuando el tamano es de 1 
        
        for (int i=0; i<size; i++) { 
            heapPermutation(size-1, n); 
            if (size % 2 == 1) swap(0, size); //si es non el tamaño se hace swap entre primero y ultimo
            else swap(i, size); //si es par el tamaño se hace swap entre primero y i elemento)      
        }
    }

    public int[][] getPermutations() {
        return this.permutations;
    }

    public void printPermutation(int[] p) {
        for (int i : p) System.out.print(i + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] numeros  = {1, 2, 3, 4};

        Permutations p = new Permutations(numeros);
        
        int[][] permutaciones = p.getPermutations();

        for (int[] perm : permutaciones) {
            p.printPermutation(perm);
        }

    }
}