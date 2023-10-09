package com.lawranta.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.lawranta.canvas.CreateGrid;
import com.lawranta.canvas.Zoom;
import com.lawranta.globals.GLOBAL;

public class GridPanel extends JPanel {

	/**
	 * Create the panel.
	 */

	/*
	 * @Override protected void paintComponent(Graphics g) {
	 * super.paintComponent(g);
	 * 
	 * if (!CanvasPanel.hideGrid) CanvasPanel.cg = new CreateGrid(g,
	 * (int)(GLOBAL.GRIDWIDTH*Zoom.factor), (int)(GLOBAL.GRIDHEIGHT*Zoom.factor),
	 * (int)(GLOBAL.CANVAS_WIDTH*Zoom.factor),
	 * (int)(GLOBAL.CANVAS_HEIGHT*Zoom.factor), Color.RED);
	 * 
	 * }
	 */

	public GridPanel() {
		setLayout(null);
		setBackground(new Color(0, 255, 255, 50));
		setOpaque(false);
		setSize(CanvasPanel.contentPanel.getSize());
		setMinimumSize(CanvasPanel.contentPanel.getMinimumSize());
		setPreferredSize(CanvasPanel.contentPanel.getPreferredSize());

		int offsetx = GLOBAL.OFFSETX, offsety = GLOBAL.OFFSETY, roomWidth = GLOBAL.CANVAS_WIDTH,
				roomHeight = GLOBAL.CANVAS_HEIGHT, xSize = GLOBAL.GRIDWIDTH, ySize = GLOBAL.GRIDHEIGHT;

		for (int i = 0 + offsetx; i < roomWidth - offsetx; i += xSize) {

			for (int y = 0 + offsety; y < roomHeight - offsety; y += ySize) {

			//	g.drawRect((int) ((i + offsetx - 1) * 1), (int) ((y + offsety - 1) * 1), (int) ((xSize) * 1),
			//			(int) ((ySize) * 1));
				
				
				JPanel gridSquare=new JPanel() {
					private static final long serialVersionUID = -6986974777547343664L;
					
					public void paintComponent(Graphics g) {
						 super.paintComponent(g);
							g.setColor(Color.RED);
						 g.drawRect(0,0,GLOBAL.GRIDHEIGHT,GLOBAL.GRIDWIDTH);
					
						 
					}
					
				};
				gridSquare.setLayout(null);
				gridSquare.setBounds((int) ((i + offsetx - 1) * 1), (int) ((y + offsety - 1) * 1), (int) ((xSize) * 1),
						(int) ((ySize) * 1));
				gridSquare.setBackground(new Color(0, 255, 255, 50));
				gridSquare.setOpaque(true);
				gridSquare.setLocation(i, y);
			//	gridSquare.setBorder(BorderFactory.createLineBorder(Color.yellow));
				add(gridSquare);
				System.out.println("made " +i+ "x"+y +  " grid");
				
			}
		}
		revalidate();
		repaint();

	}

}
