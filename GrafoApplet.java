import graph.Grafo;
import graph.algorithm.GraphAlgorithm;
import graph.algorithm.arvoregeradoraminima.Kruskal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;

import ui.GraphDrawer;


public class GrafoApplet extends JApplet {

	/*
	 * Interface
	 */
	private static final int APPLET_HEIGHT = 500;
	private static final int APPLET_WIDTH = 500;
	
	JPanel panel;
	JButton calcularBtn;
	JButton reiniciarBtn;
	JButton playBtn;

	/*
	 * Controle
	 */

	Grafo mGrafo;
	GraphDrawer mGraphDrawer;
	GraphAlgorithm mAlgorithm;

	public void init() {
		mGrafo 			= new Grafo( 100, 200 );
		mGraphDrawer 	= new GraphDrawer(mGrafo, getGraphics());
		mAlgorithm 		= new Kruskal(mGrafo);
		
		panel = new JPanel(true);

		setSize(APPLET_WIDTH, APPLET_HEIGHT);

		JPanel buttonsPanel = new JPanel();
		calcularBtn 	= new JButton("Next");
		reiniciarBtn 	= new JButton("Recargar");
		playBtn			= new JButton("Play");

		panel.setBackground(Color.lightGray);

		calcularBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				performStep();
			}
		});
		
		reiniciarBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				loadGraph();
			}

		});
		
		playBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				while( !performStep() ){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}				
			}
		});
		
		buttonsPanel.add(playBtn);
		buttonsPanel.add(calcularBtn);
		buttonsPanel.add(reiniciarBtn);
		add(panel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
		
		loadGraph();
	}	
	
	private boolean performStep() {
		boolean last = mAlgorithm.performStep();
		mGraphDrawer.desenharGrafo();
		if( last ){
			calcularBtn.setEnabled(false);
			mAlgorithm.refresh();
			playBtn.setEnabled(false);
		}
		return last;
	}

	private void loadGraph() {
		playBtn.setEnabled(true);
		calcularBtn.setEnabled(true);
		panel.paint(getGraphics());
		mGrafo.criarVerticesAleatorios();
		mGraphDrawer.desenharGrafo();
	}

	
}
