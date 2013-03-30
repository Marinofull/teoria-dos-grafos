package br.ufba.graph;

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


	public void makeRandomGraph() {
		verticesCount = (int) (10D + Math.random() * 10D) / 2;
		arestasCount = 0;
		int vertices_count = 1;
		for (int i = 0; i < verticesCount; i++) {
			Vertice node = new Vertice();
			node.nome = String.valueOf(vertices_count);
			double ang = (double) (360 / verticesCount) * (double) vertices_count;
			node.x = 200 + (int) Math.round(170D * Math
					.cos((ang * 6.2831853071795862D) / 360D));
			node.y = 200 + (int) Math.round(170D * Math
					.sin((ang * 6.2831853071795862D) / 360D));
			vertices_count++;
			getVertices()[i] = node;
		}

		for (int j = 0; j < verticesCount; j++) {
			for (int i = j + 1; i < verticesCount; i++)
				if (Math.random() * 10D <= 8D) {
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
}
