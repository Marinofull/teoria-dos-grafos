CC 	= pdflatex
BIB	= bibtex

all: biblio
	$(CC) --shell-escape Árvores\ Geradoras\ Mínimas.tex

pdflatex:
	$(CC) --shell-escape Árvores\ Geradoras\ Mínimas.tex

biblio: pdflatex
	$(BIB) Árvores\ Geradoras\ Mínimas.aux
