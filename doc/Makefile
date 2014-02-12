CC 	= pdflatex
BIB	= bibtex
REMOVE  = rm

all: biblio
	$(CC) --shell-escape Árvores\ Geradoras\ Mínimas.tex

pdflatex:
	$(CC) --shell-escape Árvores\ Geradoras\ Mínimas.tex

biblio: clean pdflatex
	$(BIB) Árvores\ Geradoras\ Mínimas.aux
	$(CC) --shell-escape Árvores\ Geradoras\ Mínimas.tex

clean:
	$(REMOVE) *.aux *.bbl *.blg *.out *.log -f
