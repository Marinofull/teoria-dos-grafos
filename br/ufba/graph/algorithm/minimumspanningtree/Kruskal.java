package br.ufba.graph.algorithm.minimumspanningtree;

import br.ufba.datastructures.UnionFind;
import br.ufba.datastructures.UnionFind.UnionElement;
import br.ufba.graph.Aresta.Status;
import br.ufba.graph.Aresta;
import br.ufba.graph.Graph;
import br.ufba.graph.Vertice;
import br.ufba.graph.algorithm.GraphAlgorithm;


public class Kruskal implements GraphAlgorithm{
	
	private Graph mGraph;
	
	private int edges[];
	
	private int edgeIndex = 0;

	public Kruskal(Graph grafo) {
		mGraph = grafo;
	}	
	
	@Override
	public void init(){
		edgeIndex = 0;
//		crie uma floresta F (um conjunto de árvores), onde cada vértice no grafo é uma árvore separada
		
		for (int i = 0; i < mGraph.getVerticesCount() ; i++) {
			Vertice vertice = mGraph.getVertices()[i];
			UnionFind.makeSet(vertice);
		}
		
//		create a set S containing all the edges in the graph ordered by weight
		edges = new int[mGraph.getArestasCount()];
		for( int i = 0; i < edges.length; edges[i] = i++);
		qsort(0, edges.length -1);
	}
	


	@Override
	public boolean performStep() {
/*		enquanto S for não-vazio, faça:
*			remova uma aresta com peso mínimo de S
*			se essa aresta conecta duas árvores diferentes, adicione-a à floresta, combinando duas árvores numa única árvore parcial
*			do contrário, descarte a aresta
*/
		if( edgeIndex >= edges.length )
			return true;
		
		Aresta aresta 	= mGraph.getArestas()[ edges[edgeIndex]];
		if( aresta.status == Status.WAITING ){
			aresta.status = Status.PROCESSING;
			return false;
		}
		edgeIndex++;
		
		UnionElement u 	= aresta.u;
		UnionElement v 	= aresta.v;
		if( !UnionFind.find(u).equals(UnionFind.find(v))){
			aresta.status = Status.TAKED;
			UnionFind.union(u, v);
		}else{
			aresta.status = Status.DISCARDED;
		}
		return false;
	}
	
	private int partition(int left, int right) {
		int pivot = mGraph.getArestas()[edges[(left + right) / 2]].weight;
		while (left <= right) {
			while (mGraph.getArestas()[edges[left]].weight < pivot)
				left++;
			while (mGraph.getArestas()[edges[right]].weight > pivot)
				right--;

			if (left <= right) {
				int i = left++;
				int j = right--;
				int k = edges[i];
				edges[i] = edges[j];
				edges[j] = k;
			}
		}
		return left;
	}

	protected void qsort(int left, int right) {
		if (left >= right)
			return;

		int i = partition(left, right);
		qsort(left, i - 1);
		qsort(i, right);
	}
}
