package br.ufba.graph;


public class Aresta {
	
	public enum Status{
		WAITING, DISCARDED, PROCESSING, TAKED
	};

	public Vertice u;

	public Vertice v;

	public int weight;

	public Status status;

	String name;

}
