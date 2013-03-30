package br.ufba.datastructures;

public class UnionFind {

	public interface UnionElement{

		public UnionElement getRoot();
		public UnionElement getParent();
		public void setRoot(UnionElement x);
		public void setParent(UnionElement x);

	}

	public static void makeSet( UnionElement x ){
		x.setParent(x);		
	}

	public static UnionElement find( UnionElement x){
		if ( x.getParent() == x )
			return x;
		else
			return find(x.getParent());
	}

	public static void union( UnionElement x, UnionElement y){
		x.setRoot( find(x) );
		y.setRoot( find(y) );		
		x.getRoot().setParent( y.getRoot() );
	}

}
