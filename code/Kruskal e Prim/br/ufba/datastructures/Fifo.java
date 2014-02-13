package br.ufba.datastructures;

public class Fifo {
	private int fifo[];
	private int i, j, tam;
	
	public Fifo(int tam){
		fifo = new int[tam];
		this.tam=0;
	}
	
	public void add(int n){
		fifo[tam]=n;
		tam++;
	}
	
	public int remove(){
		j=fifo[0];
		
		for(i=0;i<tam;i++){
			fifo[i]=fifo[i+1];
		}
		fifo[tam]=0;
		tam--;
		return j;
	}
	
	public boolean isEmpty(){
		if (tam == 0){
			return true;
		}
		return false;
	}
}
