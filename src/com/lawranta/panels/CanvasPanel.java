package com.lawranta.panels;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.lawranta.canvas.CreateGrid;
import com.lawranta.canvas.InkDrop;
import com.lawranta.canvas.TextNode;
import com.lawranta.canvas.Tool;
import com.lawranta.frames.internal.Toolbar;
import com.lawranta.globals.GLOBAL;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class CanvasPanel extends JPanel {

	int canvasWidthDefault = 1024, canvasHeightDefault = 768;
	static int brushSize = 32;
	int px;
	int py;
	public static JLayeredPane contentPanel;
	int mouse = 0;
	public static ArrayList<InkDrop> canvasContainer = new ArrayList<InkDrop>();
	private static final long serialVersionUID = 0L;
	private static final int TIMER_DELAY = 35;

	/**
	 * Create the panel.
	 */

	public CanvasPanel() {
		setBackground(new Color(23, 23, 23));
		Tool.setToolDefault();

		contentPanel = new JLayeredPane() {

			/**
						 * 
						 */
			private static final long serialVersionUID = -2898210498458640693L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);

				CreateGrid cg = new CreateGrid(g, GLOBAL.GRIDWIDTH, GLOBAL.GRIDHEIGHT, GLOBAL.CANVAS_WIDTH,
						GLOBAL.CANVAS_HEIGHT);

			}

		};
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setOpaque(true);
		contentPanel.setSize(new Dimension(canvasWidthDefault, canvasHeightDefault));
		contentPanel.setMinimumSize(new Dimension(canvasWidthDefault, canvasHeightDefault));
		contentPanel.setPreferredSize(new Dimension(canvasWidthDefault, canvasHeightDefault));
		add(contentPanel);
		contentPanel.setLayout(null);
		contentPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				if(e.getButton()==1) {
					if(Tool.selectedTool==2) {
						newTextNode();
						
					}

				}
				

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				mouse = e.getButton();
				System.out.println("Mouse button: " + mouse);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				mouse = 0;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		// the timer variable must be a javax.swing.Timer
		// TIMER_DELAY is a constant int and = 35;
		new javax.swing.Timer(TIMER_DELAY, new ActionListener() {
			public void actionPerformed(ActionEvent tick) {
				/*
				 * contentPanel.revalidate(); contentPanel.repaint();
				 */

				switch (mouse) {
				case 1: {
					chooseStartActionByTool();
					break;
				}
				case 2: {

					System.out.println("Selected Tool: " + Tool.selectedTool);
					break;
				}
				case 3: {
					chooseStopActionByTool();
					break;

				}

				}

			}
		}).start();

	}

	void chooseStartActionByTool() {

		switch (Tool.selectedTool) {
		case 1: {
			startPainting();
			break;
		}
		case 2: {

		
			break;
		}
		case 3: {

			break;

		}
		}
	}

	private void newTextNode() {

		Point p = MouseInfo.getPointerInfo().getLocation();
		Point p2 = contentPanel.getLocationOnScreen();
		int x = (int) (p.getX() - p2.getX()), y = (int) (p.getY() - p2.getY());

		TextNode node = new TextNode(x, y);
		node.setVisible(true);
		node.requestFocusInWindow();
	contentPanel.add(node,2,0);
		
		
		
		

		
		
		contentPanel.revalidate();
		contentPanel.repaint();

	}

	void chooseStopActionByTool() {

		switch (Tool.selectedTool) {
		case 1: {
			startDeleting();
			break;
		}
		case 2: {

			break;
		}
		case 3: {

			break;

		}
		}

	}

	private static void startPainting() {
		// TODO Auto-generated method stub
		Point p = MouseInfo.getPointerInfo().getLocation();
		Point p2 = contentPanel.getLocationOnScreen();
		int x = (int) (p.getX() - p2.getX()), y = (int) (p.getY() - p2.getY());

		for (int i = 0; i < canvasContainer.size(); i++) {

			System.out.println("Checking: " + i);
			if (x == canvasContainer.get(i).getX() && y == canvasContainer.get(i).getY()) {

				System.out.println("collision detected");
				contentPanel.remove(canvasContainer.get(i)); // canvasContainer.remove(i); //
				;
				contentPanel.repaint()

				;
			}

		}

		InkDrop kkk = new InkDrop(x, y, brushSize, brushSize);

		canvasContainer.add(kkk);
		// kkk.paintComponents(kkk.getGraphics());
		kkk.setVisible(true);
		contentPanel.add(kkk,1,0);
		System.out.println("Penis");
		contentPanel.repaint();
	}

	private static void startDeleting() {
		Point p = MouseInfo.getPointerInfo().getLocation();
		Point p2 = contentPanel.getLocationOnScreen();
		int x = (int) (p.getX() - p2.getX()), y = (int) (p.getY() - p2.getY());
		int gridSnapx = (int) (x % GLOBAL.GRIDWIDTH);
		int gridSnapy = (int) (y % GLOBAL.GRIDHEIGHT);
		x -= gridSnapx;
		y -= gridSnapy;

		for (int i = 0; i < canvasContainer.size(); i++) {

			// canvasContainer.get(i).destroy(contentPanel);

			if (x == canvasContainer.get(i).getX() && y == canvasContainer.get(i).getY()) {
				canvasContainer.get(i).destroy(contentPanel);
				System.out.println("removed " + i + " at " + x + "," + y);
				System.out.println("canvast Container size: " + canvasContainer.size());
				canvasContainer.remove(i); //
				contentPanel.revalidate();
				contentPanel.repaint();
			}

		}

	}
}
