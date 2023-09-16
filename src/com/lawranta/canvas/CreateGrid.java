package com.lawranta.canvas;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.SwingUtilities;

public class CreateGrid {

	int xSize, ySize, roomWidth, roomHeight;

	public CreateGrid(Graphics g, int xSize, int ySize, int roomWidth, int roomHeight) {
		// TODO Auto-generated constructor stub

		this.xSize = xSize;
		this.ySize = ySize;
		this.roomWidth = roomWidth;
		this.roomHeight = roomHeight;

		// Graphics square = new Graphics();

		g.setColor(Color.BLUE);

		for (int i = 0; i < roomWidth; i += xSize) {

			for (int y = 0; y < roomHeight; y += ySize) {
				g.drawRect(i, y, xSize, ySize);
			}
		}

	}

}
