package br.ufba.graph.algorithm.minimumspanningtree;

import java.util.ArrayList;

import br.ufba.datastructures.AdjacencyMatrix;
import br.ufba.graph.Aresta;
import br.ufba.graph.Aresta.Status;
import br.ufba.graph.Graph;
import br.ufba.graph.Vertice;
import br.ufba.graph.algorithm.GraphAlgorithm;

/**
 * @author niltonvasques
 * http://en.wikipedia.org/wiki/Prim%27s_algorithm
 */
public class Prim implements GraphAlgorithm{
	private Graph mGraph;
	private ArrayList<Vertice> vertices = new ArrayList<Vertice>();
	private AdjacencyMatrix matrix;
	
	public Prim( Graph graph) {
		mGraph = graph;
	}
	@Override
	public void init() {
		vertices.clear();
		Vertice initial = mGraph.getVertices()[0];
		vertices.add(initial);
		matrix = mGraph.createAdjacencyMatrix();
	}

	@Override
	public boolean performStep() {
		Aresta bestChoice 	= null;
		int bestVertice 	= -1;
		for (Vertice v : vertices) {
			int adjacencys[] = matrix.getAdjacencys(v.index);
			for( int i = 0; i < adjacencys.length; i++){
				if(adjacencys[i] == 1){
					System.out.println("aresta ("+v.index+","+i+")");
					Aresta aresta = mGraph.getAresta(i, v.index);
					if( aresta != null){
						if( bestChoice != null ){
							if( bestChoice.weight > aresta.weight ){
								bestChoice  = aresta;
								bestVertice = i;
							}
						}else{
							bestChoice = aresta;
							bestVertice = i;
						}
					}
				}
			}
		}
		if( bestVertice != -1 ){
			bestChoice.status = Status.TAKED;
			Vertice v = mGraph.getVertices()[bestVertice];			
			for(int i = 0; i < vertices.size(); i++){
				matrix.removeAdjacency(vertices.get(i).index, bestVertice);				
			}			
			if(!vertices.contains(v))	vertices.add(v);
		}
		return vertices.size() == mGraph.getVerticesCount();
	}
	

}
