# FILE

LATEX_FILE 	= Relatorio_Algoritmos_Grafos.tex
LATEX_AUX 	=  Relatorio_Algoritmos_Grafos.aux


# TOOLCHAINS

CC 	= pdflatex
BIB	= bibtex
REMOVE  = rm



all: biblio
	$(CC) --shell-escape $(LATEX_FILE)

pdflatex:
	$(CC) --shell-escape $(LATEX_FILE)


biblio: clean pdflatex
	$(BIB) $(LATEX_AUX)
	$(CC) --shell-escape $(LATEX_FILE)
clean:
	$(REMOVE) *.aux *.bbl *.blg *.out *.log -f
