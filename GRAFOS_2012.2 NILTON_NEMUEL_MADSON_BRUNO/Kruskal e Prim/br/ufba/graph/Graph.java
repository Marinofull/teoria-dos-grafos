package br.ufba.graph;

import br.ufba.datastructures.AdjacencyMatrix;
import br.ufba.graph.Aresta.Status;

public class Graph {
	private Vertice vertices[];
	private Aresta arestas[];

	private int verticesCount;
	private int arestasCount;

	public Graph(int max_vertices, int max_arestas) {
		setVertices(new Vertice[max_vertices]);
		setArestas(new Aresta[max_arestas]);		
	}

	public Vertice[] getVertices() {
		return vertices;
	}

	public void setVertices(Vertice vertices[]) {
		this.vertices = vertices;
	}

	public Aresta[] getArestas() {
		return arestas;
	}

	public void setArestas(Aresta arestas[]) {
		this.arestas = arestas;
	}

	public int getVerticesCount(){
		return verticesCount;
	}

	public int getArestasCount(){
		return arestasCount;
	}
	
	public Aresta getAresta(int u, int v){
		for(int i = 0; i < arestasCount; i++) {
			if( (arestas[i].u.index == u && arestas[i].v.index == v) ||
					( arestas[i].u.index == v && arestas[i].v.index == u ) ){
				return arestas[i];
			}
		}
		return null;
	}


	public void makeRandomGraph() {
		verticesCount = (int) (10D + Math.random() * 10D) / 2;
		arestasCount = 0;
		int vertices_count = 1;
		for (int i = 0; i < verticesCount; i++) {
			Vertice vertice = new Vertice();
			vertice.nome = String.valueOf(vertices_count);
			vertice.index = i;
			double ang = (double) (360 / verticesCount) * (double) vertices_count;
			vertice.x = 200 + (int) Math.round(170D * Math
					.cos((ang * 6.2831853071795862D) / 360D));
			vertice.y = 200 + (int) Math.round(170D * Math
					.sin((ang * 6.2831853071795862D) / 360D));
			vertices_count++;
			getVertices()[i] = vertice;
		}

		for (int j = 0; j < verticesCount; j++) {
			for (int i = j + 1; i < verticesCount; i++)
				if (Math.random() * 10D <= 4D) {
					Aresta edge = new Aresta();
					edge.u = vertices[i];
					edge.v = vertices[j];
					edge.weight = 1 + (int) (Math.random() * 20D);
					getArestas()[arestasCount] = edge;
					arestasCount++;
				}

		}

		for (int i = 0; i < arestasCount; i++)
			getArestas()[i].status = Status.WAITING;
	}

	public AdjacencyMatrix createAdjacencyMatrix() {
		AdjacencyMatrix m = new AdjacencyMatrix(verticesCount);
		for( int i = 0; i < arestasCount; i++){
			m.makeAdjacency(arestas[i].u.index, arestas[i].v.index);
		}
		return  m;
	}	
}
