#include<stdio.h>
#include<stdlib.h>

int r;

void criaMatAdjacencia(int a[][100], int qtdVert){
    int i,j;
    for(i = 0;i < qtdVert; i++)
    {
        for(j = 0;j < i; j++)
        {
				a[i][j] = rand()%2;
				a[j][i] = rand()%2;
        }
    a[i][i] = 0;
    }
    
    printf("\nMatriz Adjacencia \n");
    
    for(i=0;i<qtdVert;i++)
    {
        for(j=0;j<qtdVert;j++)
        {
            printf("%d\t",a[i][j]);
        }
        printf("\n");
    }
}

 
void buscaprofundidade(int matriz[][100],int qtdVert,int *vetorBP,int u,int *q){
 
    int v;
    vetorBP[u]=1;
    for(v = 0;v < qtdVert; v++)
    {
		
        if(matriz[u][v] == 1 && vetorBP[v] == 0)
        {
            q[++r] = v;
            buscaprofundidade(matriz,qtdVert,vetorBP,v,q);
        }
    }
}
void printProfundidade(int matriz[][100],int qtdVert,int *q){
    int i,origem,vetorBP[100];
    printf("\nQual e o vertice origem, menor que %d: ",qtdVert);
    scanf("%d",&origem);
    
    for(i=0;i<qtdVert;i++)
    vetorBP[i]=0;
     
    q[0]=origem;
    buscaprofundidade(matriz,qtdVert,vetorBP,origem,q);
    
    printf("\nVetor ordenado por Busca Profundidade:\n");
    for(i=0;i<qtdVert;i++)
    {
        if(vetorBP[i]!=0)
        {
        printf(" -> %d ",q[i]);
        }
    }
}
 
int main()
{
    int matriz[100][100],qtdVert,*q;
    printf("Quantidade de vertices\n");
    scanf("%d",&qtdVert);
    q = (int *)malloc(sizeof(int)*qtdVert);
    criaMatAdjacencia(matriz,qtdVert);
    printProfundidade(matriz,qtdVert,q);
    
return 0;
}
