INF = 999999999

def printSolution(distGraph):

    string = "inf"

    nodes =distGraph.keys()

    for n in nodes:

        print "\t%6s"%(n),

    print " "

    for i in nodes:

        print"%s"%(i),

        for j in nodes:

            if distGraph[i][j] == INF:

                print "%10s"%(string),

            else:

                print "%10s"%(distGraph[i][j]),

        print" "

def floydWarshall(grafo):

    nos = grafo.keys()

    distancia = {}
	
	#armezena a distancia do no n para o no k
    for n in nos:

        distancia[n] = {}

        for k in nos:

            distancia[n][k] = grafo[n][k]
	# Floyd-warshal - programacao dinamica
    for k in nos:

        for i in nos:

            for j in nos:

                if distancia[i][k] + distancia[k][j] < distancia[i][j]:

                    distancia[i][j] = distancia[i][k]+distancia[k][j]
	# imprime a tabela com resultado
    printSolution(distancia)

if __name__ == '__main__':
	
	#Cria o grafo

    grafo = {'A':{'A':0,'B':INF,'C':12,'D':6,'E':4},

             'B':{'A':INF,'B':0,'C':INF,'D':1,'E':INF},

             'C':{'A':INF,'B':INF,'C':0,'D':15,'E':3},

             'D':{'A':INF,'B':INF,'C':15,'D':0,'E':4},

             'E':{'A':INF,'B':7,'C':INF,'D':INF,'E':0}

             }

    floydWarshall(grafo)
