package br.ufba.graph;

import br.ufba.datastructures.UnionFind.UnionElement;

public class Vertice implements UnionElement{
	
	public enum Status{
		NORMAL, BRANCO, PRETO, CINZA
	}
	
	public int x;

	public int y;

	public int width;

	public int height;
	
	public String nome;
	
	public int index;
	
	private Vertice root;
	
	private Vertice parent;
	
	public Status status = Status.NORMAL;
	

	@Override
	public UnionElement getRoot() {
		return root;
	}

	@Override
	public UnionElement getParent() {
		return parent;
	}

	@Override
	public void setRoot(UnionElement x) {
		this.root = (Vertice)x;		
	}

	@Override
	public void setParent(UnionElement x) {
		this.parent = (Vertice)x;		
	}
	
	@Override
	public boolean equals(Object obj) {
		return nome.equals(((Vertice)obj).nome );
	}

}
