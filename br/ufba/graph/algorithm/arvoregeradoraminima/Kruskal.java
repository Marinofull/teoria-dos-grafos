package br.ufba.graph.algorithm.arvoregeradoraminima;

import br.ufba.graph.Grafo;
import br.ufba.graph.algorithm.GraphAlgorithm;


public class Kruskal extends GraphAlgorithm{
	
	private int step = 1;

	public Kruskal(Grafo grafo) {
		super(grafo);
	}	
	
	@Override
	public void refresh() {	
		super.refresh();
		step = 1;
	}

	@Override
	public boolean performStep() {
		if( step == 1 ){
			iniciar();
			step++;
		} else if (usel >= mGrafo.getVerticesCount() - 1) {
			step = 1;
			return true;
		} else if (step == 3) {
			filtrarArestas();
			step = 2;
		} else {
			mGrafo.getArestas()[idx[u]].selecionada = 1;
			step = 3;
		}
		return false;
	}
	
	void filtrarArestas() {
		int vl = mGrafo.getArestas()[idx[u]].positivo;
		int vr = mGrafo.getArestas()[idx[u]].negativo;
		if (mGrafo.getVertices()[vl].set == mGrafo.getVertices()[vr].set) {
			mGrafo.getArestas()[idx[u++]].selecionada = -2;
			return;
		}
		usel++;
		mGrafo.getArestas()[idx[u++]].selecionada = 2;
		int i;
		for (i = vl; mGrafo.getVertices()[i].next >= 0; i = mGrafo.getVertices()[i].next)
			;
		mGrafo.getVertices()[i].next = mGrafo.getVertices()[vr].first;
		int j = mGrafo.getVertices()[vl].first;
		int k = mGrafo.getVertices()[vl].set;
		for (i = mGrafo.getVertices()[vr].first; i >= 0; i = mGrafo.getVertices()[i].next) {
			mGrafo.getVertices()[i].first = j;
			mGrafo.getVertices()[i].set = k;
		}

	}	
}
