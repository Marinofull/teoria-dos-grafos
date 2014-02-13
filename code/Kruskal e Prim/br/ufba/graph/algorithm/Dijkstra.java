package br.ufba.graph.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.ufba.datastructures.AdjacencyMatrix;
import br.ufba.graph.Graph;
import br.ufba.graph.Vertice;

//class Dijkstra(Thread):
//
//	def menorDistancias(self, grafo, v):
//
//		#1º passo: iniciam-se os valores:
//		distancia = {}
//	
//		for no in grafo:
//			if no in grafo.neighbors(v): #se o no esta na lista dos vizinhos de v.
//				distancia[no] = (float(grafo.get_edge(v, no).attr['label']), v) # armazena o peso da aresta
//			else: # nao vizinho ou o mesmo vertice
//				distancia[no] = (float('+inf'), None) # distancia = + infinito
//	
//		# Armazena a distância partindo de v até os outros vértices
//		distancias = distancia.keys()
//		vertices = [v]
//		distanciaCopy = distancia.copy()
//		listaDist = [distanciaCopy]
//
//				
//		while (len(distancias) > len(vertices)):
//			ditems = distancia.items()
//			daux = []
//			for a in ditems:
//				if a[0] not in vertices:
//					daux += [a]
//
//			menor = daux[0]
//			for a in daux:
//				if a[1][0]<menor[1][0]:
//					menor = a
//
//			vertices += [menor[0]]
//
//			for no in grafo.neighbors(menor[0]):
//				if no not in vertices:
//					if distancia[no][0] > (menor[1][0]+float(grafo.get_edge(menor[0], no).attr['label'])):
//						distancia[no] = (menor[1][0]+float(grafo.get_edge(menor[0], no).attr['label']), menor[0])
//
//			
//			distanciaCopy = distancia.copy()
//			listaDist += [distanciaCopy]
//
//
//		return (vertices, distancia, listaDist)
//
//
//def criarGrafo():
//	grafo = AGraph(strict=False)  # grafo simples = falso
//	grafo.add_edge('a','b' , label="7")
//	grafo.add_edge('a','c' , label="9")
//	grafo.add_edge('b','c' , label="10")
//	grafo.add_edge('b','d' , label="15")
//	grafo.add_edge('c','d' , label="11")
//	grafo.add_edge('d','e' , label="6")
//	grafo.add_edge('e','f' , label="9")
//	grafo.add_edge('f','c' , label="2")
//	grafo.add_edge('a','f' , label="14")
//	
//	
//	return grafo
//
//
//def desenharCaminho(grafo, distancia, origem, destino):
//
//	for el in grafo.edges():
//		el.attr['color'] = 'black'
//
//	destinoAux = destino
//
//	while True:
//		ptr = distancia.get(destinoAux)[1]
//		grafo.get_edge(destinoAux, ptr).attr['color'] = 'red'
//		destinoAux = ptr
//		if ptr == origem:
//			break
//
//
//	grafo.draw("grafo.jpg", "jpg", "dot")
//	Image.open("grafo.jpg").show();
//
//
//def calcularDistancias(grafo):
//	matriz = {}
//	for v in grafo:
//		dijk = Dijkstra()
//		#calcula menor distancia de v para todos os outros vertices
//		MenorDistancias = dijk.menorDistancias(grafo, v)
//		# Armazena a  menor distância partindo de v até os outros vértices
//		matriz[v] = MenorDistancias
//
//	return matriz
//
//		



public class Dijkstra implements GraphAlgorithm{
	
	private static final int INFINITY = Integer.MAX_VALUE;
	private static final int UNDEFINED = -1;
	
	private Graph graph;
	private AdjacencyMatrix matrix;
	private int[][] distanciasMatrix;

	public Dijkstra(Graph graph) {
		this.graph = graph;
	}

	@Override
	public void init() {
		distanciasMatrix = new int[graph.getVerticesCount()][graph.getVerticesCount()];
		matrix = graph.createAdjacencyMatrix();
	}

	@Override
	public boolean performStep() {
		execute(graph.getVertices()[0]);
		return false;
	}
	
	
	private void execute(Vertice source){
		int[] dist = new int[graph.getVerticesCount()];
		int[] previous = new int[graph.getVerticesCount()];
		
		for(int i = 0; i < graph.getVerticesCount(); i++){
			dist[i] = INFINITY;
			
			previous[i] = UNDEFINED;
		}
		
		dist[source.index] = 0;
		
		List<Vertice> q = new ArrayList<Vertice>();
		for (int i = 0; i < graph.getVerticesCount(); i++) {
			q.add(graph.getVertices()[i]);
		}
		
		
		while(!q.isEmpty()){
			
			Vertice u = menorDistancia(dist, q);
			q.remove(u);
			
			if(dist[u.index] == INFINITY)
				break;
			
			int[] adj = matrix.getAdjacencys(u.index);
			for(int v = 0; v < adj.length; v++){
				if(adj[v] == 1 && q.contains(graph.getVertices()[v])){
					int alt = dist[u.index] + graph.getAresta(u.index, v).weight;
					if(alt < dist[v]){
						dist[v] = alt;
						previous[v] = u.index;
						q.remove(graph.getVertices()[v]);
						q.add(graph.getVertices()[v]);
					}
					
				}
			}
		}
		
		for(int i = 0; i < dist.length; i++){
			System.out.println(source.index+" -> "+i+" = "+dist[i]);
		}
	}

	private Vertice menorDistancia(int[] dist, List<Vertice> q) {
		Vertice menor = q.get(0);
		
		int menorDist = INFINITY;
		for(Vertice v : q){
			if(dist[v.index] < menorDist){
				menor = v;
				menorDist = dist[v.index];
			}
		}
		return menor;
	}
	
//	function Dijkstra(Graph, source):
//		 2      for each vertex v in Graph:                                // Initializations
//		 3          dist[v]  := infinity ;                                  // Unknown distance function from 
//		 4                                                                 // source to v
//		 5          previous[v]  := undefined ;                             // Previous node in optimal path
//		 6      end for                                                    // from source
//		 7      
//		 8      dist[source]  := 0 ;                                        // Distance from source to source
//		 9      Q := the set of all nodes in Graph ;                       // All nodes in the graph are
//		10                                                                 // unoptimized – thus are in Q
//		11      while Q is not empty:                                      // The main loop
//		12          u := vertex in Q with smallest distance in dist[] ;    // Source node in first case
//		13          remove u from Q ;
//		14          if dist[u] = infinity:
//		15              break ;                                            // all remaining vertices are
//		16          end if                                                 // inaccessible from source
//		17          
//		18          for each neighbor v of u:                              // where v has not yet been 
//		19                                                                 // removed from Q.
//		20              alt := dist[u] + dist_between(u, v) ;
//		21              if alt < dist[v]:                                  // Relax (u,v,a)
//		22                  dist[v]  := alt ;
//		23                  previous[v]  := u ;
//		24                  decrease-key v in Q;                           // Reorder v in the Queue
//		25              end if
//		26          end for
//		27      end while
//		28      return dist;
//		29  endfunction

//	
//	private void menorDistancias(Vertice u){
//		Set<Vertice> vertices = new HashSet<Vertice>();
//		vertices.add(u);
//		
//		Map<Vertice, Integer> distancias = new HashMap<Vertice, Integer>();
//		
//		int numDistancias = 0;
//		for(int v = 0; v < graph.getVerticesCount(); v++){
//			int weight = INFINITY;
//			if(matrix.checkAdjacency(v, u.index)){
////				distanciasMatrix[u.index][v] = graph.getAresta(u.index, v).weight;
//				weight = graph.getAresta(u.index, v).weight;
//			}
//			distancias.put(graph.getVertices()[v], weight);
//			numDistancias++;
//		}
//		
//		List<Vertice> daux = new ArrayList<Vertice>();
//		
//		while(numDistancias > vertices.size()){
//			for (Vertice vertice : distancias.keySet()) {
//				if(!vertices.contains(vertice)){
//					daux.add(vertice);
//				}
//			}
//			
////			menor = daux[0]
////			for a in daux:
////				if a[1][0]<menor[1][0]:
////					menor = a
//			
//			Vertice menor = daux.get(0);
//			for (Vertice vertice : daux) {
//				if(vertice)
//			}
//		}
//	}
	

}
