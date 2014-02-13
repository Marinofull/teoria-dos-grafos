package br.ufba.graph.algorithm.mininumpath;

import java.util.ArrayList;
import java.util.List;

import br.ufba.datastructures.AdjacencyMatrix;
import br.ufba.graph.Aresta;
import br.ufba.graph.Aresta.Status;
import br.ufba.graph.algorithm.GraphAlgorithm;
import br.ufba.graph.Graph;
import br.ufba.graph.Vertice;


/**
 * @author niltonvasques
 * Link: http://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 */
public class Dijkstra implements GraphAlgorithm{
	
	private static final int INFINITY = Integer.MAX_VALUE;
	private static final int UNDEFINED = -1;
	
	private Graph graph;
	private AdjacencyMatrix matrix;
	private Vertice source;
	private Vertice target;

	public Dijkstra(Graph graph) {
		this.graph = graph;
	}

	@Override
	public void init() {
		matrix = graph.createAdjacencyMatrix();
	}

	@Override
	public boolean performStep() {
		execute(source);
		return false;
	}
	
	public void setSource(int source){
		this.source = graph.getVertices()[source];
	}
	
	public void setTarget(int target){
		try{
			this.target = graph.getVertices()[target];
		}catch(Exception e){
			this.target = null;
		}
	}
	
	private void execute(Vertice source){
		int[] dist = new int[graph.getVerticesCount()];
		int[] previous = new int[graph.getVerticesCount()];
		
		for(int i = 0; i < graph.getVerticesCount(); i++){
			dist[i] = INFINITY;
			
			previous[i] = UNDEFINED;
		}
		
		dist[source.index] = 0;
		
		List<Vertice> q = new ArrayList<Vertice>();
		for (int i = 0; i < graph.getVerticesCount(); i++) {
			q.add(graph.getVertices()[i]);
		}
		
		
		while(!q.isEmpty()){
			
			Vertice u = menorDistancia(dist, q);
			q.remove(u);
			
			if(dist[u.index] == INFINITY)
				break;
			
			int[] adj = matrix.getAdjacencys(u.index);
			for(int v = 0; v < adj.length; v++){
				if(adj[v] == 1 && q.contains(graph.getVertices()[v])){
					int alt = dist[u.index] + graph.getAresta(u.index, v).weight;
					if(alt < dist[v]){
						dist[v] = alt;
						previous[v] = u.index;
						q.remove(graph.getVertices()[v]);
						q.add(graph.getVertices()[v]);
					}
					
				}
			}
		}
		
		if(target == null){
			for(int i = 0; i < dist.length; i++){
					System.out.println(source.nome+" -> "+(i+1)+" = "+(dist[i] == INFINITY ? "INFINITO": dist[i]));
			}
		}else{
			System.out.println(source.nome+" -> "+target.nome+" = "+(dist[target.index] == INFINITY ? "INFINITO": dist[target.index]));
			clearPaths();
			paintPath(target.index, previous);
		}
	}

	private Vertice menorDistancia(int[] dist, List<Vertice> q) {
		Vertice menor = q.get(0);
		
		int menorDist = INFINITY;
		for(Vertice v : q){
			if(dist[v.index] < menorDist){
				menor = v;
				menorDist = dist[v.index];
			}
		}
		return menor;
	}
	
	private void paintPath(int origem, int[] previous){
		if(previous[origem] != UNDEFINED){
			paintPath(previous[origem], previous);
			graph.getAresta(origem, previous[origem] ).status = Status.TAKED;
		}
	}
	
	private void clearPaths(){
		for (Aresta aresta : graph.getArestas()) {
			if(aresta != null)
				aresta.status = Status.WAITING;
		}
	}
}
