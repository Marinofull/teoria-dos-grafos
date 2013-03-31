package br.ufba.datastructures;

/**
 * @author niltonvasques
 * 
 */
public class AdjacencyMatrix {

	private static final int MEM_BLOCK_SIZE = 32;
	int matrix[];
	int stride;
	public AdjacencyMatrix(int n) {
		stride = n;
		int size = (int)(stride * stride);
		int lenght = 1 + (size/32);
		matrix = new int[ lenght ];	
	}
	
	public void makeAdjacency(int element, int adjacency ){
		makeAdjacencyInternal(element, adjacency);
		makeAdjacencyInternal(adjacency, element);  
	}
	
	public void removeAdjacency(int element, int adjacency ){
		removeAdjacencyInternal(element, adjacency);
		removeAdjacencyInternal(adjacency, element);  
	}

	private void makeAdjacencyInternal(int element, int adjacency) {
		int bitIndex			= (element*stride+adjacency);
		int index 		= (int) (bitIndex/MEM_BLOCK_SIZE);
		int shift 		= bitIndex % MEM_BLOCK_SIZE;
		matrix[index] 	|= (0x01 << shift);
	}
	
	private void removeAdjacencyInternal(int element, int adjacency) {
		int bitIndex			= (element*stride+adjacency);
		int index 		= (int) (bitIndex/MEM_BLOCK_SIZE);
		int shift 		= bitIndex % MEM_BLOCK_SIZE;
		matrix[index] 	&= ~(0x01 << shift);
	}
	
	public int[] getAdjacencys(int element){
		int adjacencys[] = new int[stride];
		
		int bitIndex		= (element*stride);
		
		for( int i = 0; i < stride; i++){
			int x = ((bitIndex+i)/MEM_BLOCK_SIZE);
			int y = (bitIndex % MEM_BLOCK_SIZE) + i;
			
			int ret = (matrix[x] >> y) & 0x01;
			adjacencys[i] = ret;		
		}		
		
		return adjacencys;
	}
	
	public boolean checkAdjacency(int u, int v){
		
		int bitIndex		= (u*stride);
		int index 			= (int) (bitIndex/MEM_BLOCK_SIZE);
		
		int x = (v/MEM_BLOCK_SIZE) + index;
		int y = (bitIndex % MEM_BLOCK_SIZE) + v;
		
		return ((matrix[x] >> y) & 0x01) == 0x01;
	}
}
