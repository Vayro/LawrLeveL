package com.lawranta.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

import com.lawranta.canvas.CreateGrid;
import com.lawranta.globals.GLOBAL;

public class GridPanel extends JPanel {

	/**
	 * Create the panel.
	 */

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (!CanvasPanel.hideGrid)
			CanvasPanel.cg = new CreateGrid(g, GLOBAL.GRIDWIDTH, GLOBAL.GRIDHEIGHT, GLOBAL.CANVAS_WIDTH,
					GLOBAL.CANVAS_HEIGHT, Color.RED);

	}

	public GridPanel() {

		setBackground(new Color(0, 255, 255, 50));
		setOpaque(false);
		setSize(CanvasPanel.contentPanel.getSize());
		setMinimumSize(CanvasPanel.contentPanel.getMinimumSize());
		setPreferredSize(CanvasPanel.contentPanel.getPreferredSize());

	}

}
