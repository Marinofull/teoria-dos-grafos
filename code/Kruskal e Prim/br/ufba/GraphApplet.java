package br.ufba;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

import br.ufba.graph.Graph;
import br.ufba.graph.algorithm.Dijkstra;
import br.ufba.graph.algorithm.GraphAlgorithm;
import br.ufba.graph.algorithm.minimumspanningtree.Kruskal;
import br.ufba.graph.algorithm.minimumspanningtree.Prim;
import br.ufba.graph.algorithm.search.BFS;
import br.ufba.graph.algorithm.search.DFS;
import br.ufba.ui.GraphDrawer;



/**
 * @author niltonvasques
 *
 */
public class GraphApplet extends JApplet {

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

	Graph mGrafo;
	GraphDrawer mGraphDrawer;
	GraphAlgorithm mAlgorithm;

	public void init() {
		mGrafo 			= new Graph( 100, 200 );
		mGraphDrawer 	= new GraphDrawer(mGrafo, getGraphics());
		mAlgorithm 		= new Kruskal(mGrafo);
		
		panel = new JPanel(true);

		setSize(APPLET_WIDTH, APPLET_HEIGHT);

		JPanel buttonsPanel = new JPanel();
		calcularBtn 	= new JButton("Next");
		reiniciarBtn 	= new JButton("New");
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
		
		 //Create the radio buttons.
	    JRadioButton kruskalButton = new JRadioButton("Kruskal");
	    kruskalButton.setMnemonic(KeyEvent.VK_B);
	    kruskalButton.setActionCommand("Kruskal");
	    kruskalButton.setSelected(true);

	    JRadioButton primButton = new JRadioButton("Prim");
	    primButton.setMnemonic(KeyEvent.VK_C);
	    primButton.setActionCommand("Prim");
	    
	    JRadioButton dfsButton = new JRadioButton("DFS");
	    dfsButton.setMnemonic(KeyEvent.VK_D);
	    dfsButton.setActionCommand("DFS");
	    
	    JRadioButton bfsButton = new JRadioButton("BFS");
	    bfsButton.setMnemonic(KeyEvent.VK_E);
	    bfsButton.setActionCommand("BFS");
	    JRadioButton dijkstraButton = new JRadioButton("Dijkstra");
	    dijkstraButton.setMnemonic(KeyEvent.VK_D);
	    dijkstraButton.setActionCommand("Dijkstra");

	    //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(kruskalButton);
	    group.add(primButton);
	    group.add(dfsButton);
	    group.add(bfsButton);
	    group.add(dijkstraButton);

	    //Register a listener for the radio buttons.
	    kruskalButton.addActionListener(alghs);
	    primButton.addActionListener(alghs);
	    dfsButton.addActionListener(alghs);
	    bfsButton.addActionListener(alghs);
	    dijkstraButton.addActionListener(alghs);
	
	    
		
		buttonsPanel.add(playBtn);
		buttonsPanel.add(calcularBtn);
		buttonsPanel.add(reiniciarBtn);
		buttonsPanel.add(kruskalButton);
		buttonsPanel.add(primButton);
		buttonsPanel.add(dfsButton);
		buttonsPanel.add(bfsButton);
		buttonsPanel.add(dijkstraButton);
		add(panel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
		
		loadGraph();
	}	
	
	private boolean performStep() {
		boolean last = mAlgorithm.performStep();
		mGraphDrawer.desenharGrafo();
		if( last ){
			calcularBtn.setEnabled(false);
			playBtn.setEnabled(false);
			System.out.println(mAlgorithm);
		}
		return last;
	}

	private void loadGraph() {
		playBtn.setEnabled(true);
		calcularBtn.setEnabled(true);
		panel.paint(getGraphics());
		mGrafo.makeRandomGraph();
		mAlgorithm.init();
		mGraphDrawer.desenharGrafo();
	}
	
	ActionListener alghs = new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
			if( e.getActionCommand().equals("Kruskal")){
				mAlgorithm = new Kruskal(mGrafo);
			}else if(e.getActionCommand().equals("Prim") ){
				mAlgorithm = new Prim(mGrafo);
			}else if(e.getActionCommand().equals("DFS") ){
				mAlgorithm = new DFS(mGrafo);
			}else if(e.getActionCommand().equals("BFS") ){
				mAlgorithm = new BFS(mGrafo);
			}else if(e.getActionCommand().equals("Dijkstra") ){
				mAlgorithm = new Dijkstra(mGrafo);
			}
			loadGraph();			
		}
	};

	
}
