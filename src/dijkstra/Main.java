package dijkstra;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int nNodos = scanner.nextInt();
		Graph grafo = new Graph(nNodos);
		
		int nCaminos = scanner.nextInt();
		for(int i=0; i<nCaminos; i++) {
			int origen = scanner.nextInt();
			int destino = scanner.nextInt();
			int peso = scanner.nextInt();
			
			grafo.insertarArista(origen, destino, peso);
			grafo.insertarArista(destino, origen, peso);
		}
		
		int nodoPartida = scanner.nextInt();
		long[] vectorDistancias = Dijkstra.solve(grafo, nodoPartida);
		for(int i=0; i<nNodos; i++) {
			System.out.println(i + " -> " + vectorDistancias[i]);
		}
		
		scanner.close();

	}

}
