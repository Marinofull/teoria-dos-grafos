#-*- coding: utf-8 -*-
from pygraphviz import *
from random import *
from threading import Thread
import Image
import os



class Dijkstra(Thread):

	def menorDistancias(self, grafo, v):

		#1º passo: iniciam-se os valores:
		distancia = {}
	
		for no in grafo:
			if no in grafo.neighbors(v): #se o no esta na lista dos vizinhos de v.
				distancia[no] = (float(grafo.get_edge(v, no).attr['label']), v) # armazena o peso da aresta
			else: # nao vizinho ou o mesmo vertice
				distancia[no] = (float('+inf'), None) # distancia = + infinito
	
		# Armazena a distância partindo de v até os outros vértices
		distancias = distancia.keys()
		vertices = [v]
		distanciaCopy = distancia.copy()
		listaDist = [distanciaCopy]

				
		while (len(distancias) > len(vertices)):
			ditems = distancia.items()
			daux = []
			for a in ditems:
				if a[0] not in vertices:
					daux += [a]

			menor = daux[0]
			for a in daux:
				if a[1][0]<menor[1][0]:
					menor = a

			vertices += [menor[0]]

			for no in grafo.neighbors(menor[0]):
				if no not in vertices:
					if distancia[no][0] > (menor[1][0]+float(grafo.get_edge(menor[0], no).attr['label'])):
						distancia[no] = (menor[1][0]+float(grafo.get_edge(menor[0], no).attr['label']), menor[0])

			
			distanciaCopy = distancia.copy()
			listaDist += [distanciaCopy]


		return (vertices, distancia, listaDist)


def criarGrafo():
	grafo = AGraph(strict=False)  # grafo simples = falso
	grafo.add_edge('a','b' , label="7")
	grafo.add_edge('a','c' , label="9")
	grafo.add_edge('b','c' , label="10")
	grafo.add_edge('b','d' , label="15")
	grafo.add_edge('c','d' , label="11")
	grafo.add_edge('d','e' , label="6")
	grafo.add_edge('e','f' , label="9")
	grafo.add_edge('f','c' , label="2")
	grafo.add_edge('a','f' , label="14")
	
	
	return grafo


def desenharCaminho(grafo, distancia, origem, destino):

	for el in grafo.edges():
		el.attr['color'] = 'black'

	destinoAux = destino

	while True:
		ptr = distancia.get(destinoAux)[1]
		grafo.get_edge(destinoAux, ptr).attr['color'] = 'red'
		destinoAux = ptr
		if ptr == origem:
			break


	grafo.draw("grafo.jpg", "jpg", "dot")
	Image.open("grafo.jpg").show();


def calcularDistancias(grafo):
	matriz = {}
	for v in grafo:
		dijk = Dijkstra()
		#calcula menor distancia de v para todos os outros vertices
		MenorDistancias = dijk.menorDistancias(grafo, v)
		# Armazena a  menor distância partindo de v até os outros vértices
		matriz[v] = MenorDistancias

	return matriz

		

def main():

	# criao grafo
	grafo = criarGrafo() 
	grafo.draw("grafo.jpg", "jpg", "dot")
	#exibe o grafo
	Image.open("grafo.jpg").show();
	#calcula as distancias
	matriz = None
	matriz = calcularDistancias(grafo)
	#seleção dos vertices
	o = raw_input("\nEscolha um vertice de origem: ")
	#res = matriz.get(o)
	d = raw_input("Escolha um vertice destino: ")
	desenharCaminho(grafo, matriz[o][1], o, d)

	

main()
