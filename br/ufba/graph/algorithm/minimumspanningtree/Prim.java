package br.ufba.graph.algorithm.minimumspanningtree;

import br.ufba.datastructures.AdjacencyMatrix;
import br.ufba.graph.Aresta;
import br.ufba.graph.Aresta.Status;
import br.ufba.graph.Graph;
import br.ufba.graph.algorithm.GraphAlgorithm;

/**
 * @author niltonvasques
 * http://en.wikipedia.org/wiki/Prim%27s_algorithm
 */
public class Prim implements GraphAlgorithm{
	private Graph mGraph;
	private AdjacencyMatrix verticesMatrix;
	private AdjacencyMatrix matrix;
	private boolean processing = false;
	
	public Prim( Graph graph) {
		mGraph = graph;
	}
	@Override
	public void init() {
		processing = false;
		verticesMatrix = new AdjacencyMatrix(mGraph.getVerticesCount());
		verticesMatrix.makeAdjacency(0, 0);
		matrix = mGraph.createAdjacencyMatrix();
	}

	@Override
	public boolean performStep() {
		Aresta bestChoice 	= null;
		int bestVertice 	= -1;
		int vertices[] = verticesMatrix.getAdjacencys(0);
		for( int v = 0; v < vertices.length; v++){
			if( vertices[v] == 1){
				int adjacencys[] = matrix.getAdjacencys(v);
				for( int i = 0; i < adjacencys.length; i++){
					if(adjacencys[i] == 1){
						Aresta aresta = mGraph.getAresta(i, v);
						if( aresta != null){
							if( !processing ){
								aresta.status = Status.PROCESSING;
							}else if ( bestChoice != null ){
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
		}
		if(!processing){
			processing = true;
			return false;
		}
		boolean finish = true;
		if( bestVertice != -1 ){
			bestChoice.status = Status.TAKED;			
			for(int i = 0; i < vertices.length; i++){
				if( vertices[i] == 1){
					matrix.removeAdjacency(i, bestVertice);
				}else{
					finish = false;
				}
			}			
			processing = false;
			verticesMatrix.makeAdjacency(0, bestVertice);
		}
		return finish;
	}
	

}
