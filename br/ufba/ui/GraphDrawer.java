package br.ufba.ui;


import java.awt.Color;
import java.awt.Graphics;

import br.ufba.graph.Aresta;
import br.ufba.graph.Grafo;
import br.ufba.graph.Vertice;

public class GraphDrawer {
	
	private Grafo mGrafo;
	private Graphics mGraphics;
	
	public GraphDrawer(Grafo grafo, Graphics graphics) {
		mGrafo = grafo;
		mGraphics = graphics;
	}
	
	public void desenharGrafo() {
		for (int i = 0; i < mGrafo.getVerticesCount(); i++)
			desenharVertice(mGraphics, mGrafo.getVertices()[i]);

		for (int i = 0; i < mGrafo.getArestasCount(); i++)
			desenharAresta(mGraphics, mGrafo.getArestas()[i]);
	}

	private void desenharVertice(Graphics g, Vertice vertice) {
		int x = vertice.x;
		int y = vertice.y;
		int w = g.getFontMetrics().stringWidth(vertice.nome) + 10;
		int h = g.getFontMetrics().getHeight() + 4;
		vertice.width = w;
		vertice.height = h;
		g.setColor(Color.black);
		g.drawOval((x - w / 2) - 1, (y - h / 2) - 1, w + 1, h + 1);
		g.setColor(Color.ORANGE);
		g.fillOval(x - w / 2, y - h / 2, w, h);
		g.setColor(Color.BLACK);
		g.drawString(vertice.nome, x - (w - 10) / 2, (y - (h - 4) / 2)
				+ g.getFontMetrics().getAscent());
	}

	private void desenharAresta(Graphics g, Aresta aresta) {
		Vertice vInicio = mGrafo.getVertices()[aresta.positivo];
		Vertice vFim = mGrafo.getVertices()[aresta.negativo];

		int a = vInicio.x - vFim.x;
		int b = vInicio.y - vFim.y;

		int x1[] = retornarCoordenadas(-a, -b, vInicio.width, vInicio.height);
		int x2[] = retornarCoordenadas(a, b, vFim.width, vFim.height);

		if (aresta.selecionada == -1)
			g.setColor(Color.blue);
		else if (aresta.selecionada == -2)
			g.setColor(Color.gray);
		else if (aresta.selecionada == 1)
			g.setColor(Color.orange);
		else
			g.setColor(Color.green);
		g.drawLine(vInicio.x + x1[0], vInicio.y + x1[1], vFim.x + x2[0], vFim.y + x2[1]);

		int w = g.getFontMetrics().stringWidth(String.valueOf(aresta.longitude));
		int h = g.getFontMetrics().getHeight();
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(((vInicio.x + vFim.x) - w) / 2, ((vInicio.y + vFim.y) - h) / 2, w, h);

		if (aresta.selecionada == -1)
			g.setColor(Color.white);
		else if (aresta.selecionada == -2)
			g.setColor(Color.darkGray);
		else if (aresta.selecionada == 1)
			g.setColor(Color.orange);
		else
			g.setColor(Color.red);

		g.drawString(String.valueOf(aresta.longitude), ((vInicio.x + vFim.x) - w) / 2,
				((vInicio.y + vFim.y) - h) / 2 + g.getFontMetrics().getAscent());
	}

	private int[] retornarCoordenadas(int a, int b, int w, int h) {
		int x[] = new int[2];
		if (Math.abs(w * b) >= Math.abs(h * a)) {
			x[0] = ((b >= 0 ? 1 : -1) * a * h) / b / 2;
			x[1] = ((b >= 0 ? 1 : -1) * h) / 2;
		} else {
			x[0] = ((a >= 0 ? 1 : -1) * w) / 2;
			x[1] = ((a >= 0 ? 1 : -1) * b * w) / a / 2;
		}
		return x;
	}
}
