package graph.algorithm;

import graph.Aresta;
import graph.Grafo;
import graph.Vertice;

public abstract class GraphAlgorithm {

	protected int idx[];
	protected int u;
	protected int usel;
	protected Grafo mGrafo;
	private boolean started = false;
	
	public GraphAlgorithm(Grafo grafo) {
		mGrafo = grafo;
		idx = new int[200];
	}
	
	public void iniciar() {
		for (int i = 0; i < mGrafo.getArestasCount(); i++)
			idx[i] = i;
	
		for (int i = 0; i < mGrafo.getArestasCount(); i++)
			mGrafo.getArestas()[i].selecionada = -1;
	
		qsort(0, mGrafo.getArestasCount() - 1);
		for (int i = 0; i < mGrafo.getArestasCount(); i++)
			mGrafo.getArestas()[i].selecionada = -1;
	
		for (int i = 0; i < mGrafo.getVerticesCount(); i++) {
			mGrafo.getVertices()[i].set = i;
			mGrafo.getVertices()[i].first = i;
			mGrafo.getVertices()[i].next = -1;
		}
	
		usel = u = 0;
		
		started = true;
	}
	
	public void refresh(){
		for( ; u < mGrafo.getArestasCount(); u++){
			mGrafo.getArestas()[idx[u]].selecionada = -2;
		}
	}
	
	public boolean isStarted(){
		return started;
	}
	
	/**
	 * @return true if last step
	 */
	public abstract boolean performStep();
	
	private int partition(int left, int right) {
		int pivot = mGrafo.getArestas()[idx[(left + right) / 2]].longitude;
		while (left <= right) {
			while (mGrafo.getArestas()[idx[left]].longitude < pivot)
				left++;
			while (mGrafo.getArestas()[idx[right]].longitude > pivot)
				right--;

			if (left <= right) {
				int i = left++;
				int j = right--;
				int k = idx[i];
				idx[i] = idx[j];
				idx[j] = k;
			}
		}
		return left;
	}

	protected void qsort(int left, int right) {
		if (left >= right)
			return;

		int i = partition(left, right);
		qsort(left, i - 1);
		qsort(i, right);
	}

}
