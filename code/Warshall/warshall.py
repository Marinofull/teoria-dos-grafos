#!/usr/bin/python
# vim: set fileencoding: utf-8 :

#Encontra para cada elemento do grafo, as arestas que chegam e saem
#Para cada par de aresta de chegada e saida, coloca 1 na matriz de saída

def warshall(p, n):
	for k in range(n):
		for i in range(n):
			for j in range(n):
				p[i][j]=max(p[i][j],(p[i][k]&p[k][j]))
	return

def max(a,b):
	if(a>b):
		return(a)
	else:
		return(b)


print("\n Quantidade de vertices e arestas, respectivamente:")
n= int(input(""))
e= int(input(""))

p = [[0 for i in range(n)] for i in range(n)]
for i in range(e):
	print("\n Vertices que a aresta incide %d:" % (i+1))
	u=int(input(""))
	v=int(input(""))
	p[(u-1)][(v-1)]=1

print("\n Matrix adjacencia:")
for i in range(n):
	print(p[i])

warshall(p,n)

for i in range(n):
	for j in range(n):
		if(i==j):
			p[i][j]=1

print("\n Fechamento transitivo: \n")
for i in range(n):
	print(p[i])
