package com.lawranta.panels;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import com.lawranta.canvas.CreateGrid;
import com.lawranta.canvas.Zoom;
import com.lawranta.globals.GLOBAL;

public class GridPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9031806317814487197L;

	/**
	 * Create the panel.
	 */

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
 
		if (!CanvasPanel.hideGrid)
			CanvasPanel.cg = new CreateGrid(g, (int) (GLOBAL.GRIDWIDTH * Zoom.factor),
					(int) (GLOBAL.GRIDHEIGHT * Zoom.factor), (int) (GLOBAL.CANVAS_WIDTH * Zoom.factor),
					(int) (GLOBAL.CANVAS_HEIGHT * Zoom.factor), GLOBAL.gridColor);

	}

	public GridPanel() {
		setLayout(null);
		//setBackground(new Color(0, 255, 23, 50));
		setOpaque(false);
		setSize(CanvasPanel.contentPanel.getSize());
		setMinimumSize(CanvasPanel.contentPanel.getMinimumSize());
		setPreferredSize(CanvasPanel.contentPanel.getPreferredSize());
		System.out.println("\u001B[31m creating grid \u001B[0m");
	}

}
