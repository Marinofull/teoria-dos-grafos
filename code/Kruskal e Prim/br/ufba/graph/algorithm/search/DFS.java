package br.ufba.graph.algorithm.search;

import java.util.Stack;

import br.ufba.datastructures.AdjacencyMatrix;
import br.ufba.graph.Graph;
import br.ufba.graph.Vertice;
import br.ufba.graph.Aresta.Status;
import br.ufba.graph.algorithm.GraphAlgorithm;

public class DFS implements GraphAlgorithm{
	
	private Graph graph;
	private AdjacencyMatrix matrix;
	private Stack<Vertice> stack = new Stack<Vertice>();
	
	public DFS(Graph graph) {
		this.graph = graph;
	}

	@Override
	public void init() {
		this.matrix = this.graph.createAdjacencyMatrix();
		
		for(int i = 0; i < graph.getVerticesCount(); i++){
			graph.getVertices()[i].status = Vertice.Status.BRANCO;
		}
		stack.push(graph.getVertices()[0]);
		graph.getVertices()[0].status = Vertice.Status.CINZA;
	}
	
//	void buscaprofundidade(int matriz[][100],int qtdVert,int *vetorBP,int u,int *q){
//		 
//	    int v;
//	    vetorBP[u]=1;
//	    for(v = 0;v < qtdVert; v++)
//	    {
//			
//	        if(matriz[u][v] == 1 && vetorBP[v] == 0)
//	        {
//	            q[++r] = v;
//	            buscaprofundidade(matriz,qtdVert,vetorBP,v,q);
//	        }
//	    }
//	}
	
	int next = 0;
	@Override
	public boolean performStep() {
		
		if(stack.isEmpty()) return true;
		
		boolean step = false;
		
		while(!step){
			Vertice u = stack.peek();
			
			if(next >= graph.getVerticesCount()){
				next = 0;
				stack.pop();
				return false;
			}
			
			if(matrix.checkAdjacency(u.index, next) && graph.getVertices()[next].status != Vertice.Status.CINZA){
				
				Vertice vertexV = graph.getVertices()[next];
				
				if(vertexV.status == Vertice.Status.BRANCO){
					graph.getAresta(u.index, next).status = Status.TAKED;
					vertexV.status = Vertice.Status.CINZA;
					stack.push(vertexV);
				}else{
					u.status = Vertice.Status.PRETO;
					stack.pop();
				}
				next = 0;
				step = true;
			}else{
				next++;
			}
		}
		
		return false;
	}

}
