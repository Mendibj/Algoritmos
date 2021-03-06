package kruskal_callecara;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class CalleCara {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			int nNodos = scanner.nextInt();
			if(nNodos == 0) break;
			int nCaminos = scanner.nextInt();
			
			Queue<Edge> cola = new PriorityQueue<>(new EdgeComparator());

			for (int i = 0; i < nCaminos; i++) {
				int n1 = scanner.nextInt();
				int n2 = scanner.nextInt();
				long peso = scanner.nextLong();

				cola.add(new Edge(n1, n2, peso));
			}

			resolver(nNodos, cola);
		}
		scanner.close();
	}

	public static void resolver(int nNodos, Queue<Edge> cola) {
		int nIslas = 0;
		int nCaminos = 0;
		long pesoMaximo = 0;
		
		int[] leader = new int[nNodos];
		int[] ranking = new int[nNodos];
		for(int i=0; i<nNodos; i++) {
			leader[i] = i;
		}

		while (!cola.isEmpty() && nCaminos < nNodos) {
			Edge edge = cola.remove();
			int oLeader = find(leader, edge.origen);
			int dLeader = find(leader, edge.destino);
			
			if(oLeader != dLeader) {
				union(leader, ranking, oLeader, dLeader);
				pesoMaximo += edge.peso;
				nCaminos += 1;
			}
		}
		
		nIslas = nNodos - nCaminos;
		System.out.println(nIslas + " " + nCaminos + " " + pesoMaximo);
		System.out.println("---");
	}

	public static int find(int[] leader, int x) {
	     if (leader[x] != x) {
	          return find(leader, leader[x]);
	     }
	     return leader[x];
	}
	
	public static void union(int[] leader, int[] ranking, int setLeaderX, int setLeaderY) {
		
		if (ranking[setLeaderX] == ranking[setLeaderY]) {
			leader[setLeaderY] = setLeaderX;
	        ranking[setLeaderX]++;
		}
		else {
			if (ranking[setLeaderX] > ranking[setLeaderY]) leader[setLeaderY] = setLeaderX;
	        else leader[setLeaderX] = setLeaderY;
		}
	}
	

	public static class Edge {
		private int origen;
		private int destino;
		private long peso;

		public Edge(int o, int d, long p) {
			origen = o;
			destino = d;
			peso = p;
		}
	}

	public static class EdgeComparator implements Comparator<Edge> {

		@Override
		public int compare(Edge e1, Edge e2) {
			if (e1.peso < e2.peso)
				return 1;
			if (e1.peso > e2.peso)
				return -1;
			else
				return 0;
		}
	}
}
