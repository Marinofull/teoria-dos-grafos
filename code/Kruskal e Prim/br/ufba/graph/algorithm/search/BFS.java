package br.ufba.graph.algorithm.search;


import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;

import java.util.Queue;
import br.ufba.datastructures.AdjacencyMatrix;
import br.ufba.graph.Graph;
import br.ufba.graph.Vertice;
import br.ufba.graph.Aresta.Status;
import br.ufba.graph.algorithm.GraphAlgorithm;


public class BFS implements GraphAlgorithm{
	
	private Graph graph;
	private AdjacencyMatrix matrix;
	private boolean processing = false;
	private Random gerador = new Random();
	private int d[];
	private Vertice v0, v, u; 
	
	//private ArrayList<Vertice> Q= new ArrayList<Vertice>();
	private Queue<Vertice> Q = new PriorityQueue<Vertice>();
	

	
	public BFS(Graph graph){
		this.graph = graph;
	}
	
	
	public void init() {
		matrix = graph.createAdjacencyMatrix();
		d = new int[graph.getVerticesCount()];//
		v0 = graph.getVertices()[gerador.nextInt(graph.getVerticesCount())];
	}
	
	
	
	public boolean performStep() {
		buscaemlargura();
		return true;
	}
	
	public void buscaemlargura() {
		int i;
		
		for(i=0; i < graph.getVerticesCount() ; d[i++]= 999);
		d[v0.index]=0;Q.add(v0);
		
		while(!Q.isEmpty()){
			u=Q.poll();
			for(i=0; i < graph.getVerticesCount() ; i++){
				v=graph.getVertices()[i];
				
				if(matrix.checkAdjacency(u.index, v.index)){
					if(d[v.index]==999){
						d[v.index]=d[u.index]+1;
						Q.add(v);
						graph.getAresta(u.index, v.index).status = Status.TAKED;
					}
				}
					
			}
		}
		
	}
	

}
