package br.ufba.graph.algorithm.search;

import java.util.Stack;

import br.ufba.datastructures.AdjacencyMatrix;
import br.ufba.graph.Graph;
import br.ufba.graph.Vertice;
import br.ufba.graph.Aresta.Status;
import br.ufba.graph.algorithm.GraphAlgorithm;

/**
 * @author niltonvasques
 * link: http://www.ime.usp.br/~pf/analise_de_algoritmos/aulas/dfs.html
 */
public class DFS implements GraphAlgorithm{
	
	private Graph graph;
	private AdjacencyMatrix matrix;
	private Stack<Vertice> stack = new Stack<Vertice>();

	private int ordemBusca[];
	private int index = 0;

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
		
		index = 0;
		ordemBusca = new int[graph.getVerticesCount()];
		ordemBusca[index++] = next;
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
				u.status = Vertice.Status.PRETO;
				return false;
			}
			
			Vertice vertexV = graph.getVertices()[next];
			if(matrix.checkAdjacency(u.index, next) && vertexV != null 
					&& vertexV.status == Vertice.Status.BRANCO){
//				if(){
					if(graph.getAresta(u.index, next) == null){
						System.out.println(matrix);
					}else{
						graph.getAresta(u.index, next).status = Status.TAKED;
					}
					vertexV.status = Vertice.Status.CINZA;
					stack.push(vertexV);
					ordemBusca[index++] = next;
					next = 0;
					step = true;
//				}				
			}else{
				next++;
			}
		}
		
		return false;
	}

	@Override
	public String toString() {
		String result = "";
		for(int i = 0; i< ordemBusca.length; i++){
				result += " -> "+(ordemBusca[i]+1);
		}
		return result;
	}

}
