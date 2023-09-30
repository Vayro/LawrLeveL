package com.lawranta.canvas;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.SwingUtilities;

import com.lawranta.globals.GLOBAL;

public class CreateGrid {

	int xSize, ySize, roomWidth, roomHeight, offsetx, offsety;

	public CreateGrid(Graphics g, int xSize, int ySize, int roomWidth, int roomHeight) {
		// TODO Auto-generated constructor stub

		this.xSize = xSize;
		this.ySize = ySize;
		this.roomWidth = roomWidth;
		this.roomHeight = roomHeight;
		this.offsetx=-GLOBAL.OFFSETX;
		this.offsety=-GLOBAL.OFFSETY;
		// Graphics square = new Graphics();

		g.setColor(Color.BLUE);

		for (int i = 0+offsetx; i < roomWidth-offsetx; i += xSize) {

			for (int y = 0+offsety; y < roomHeight-offsety; y += ySize) {
				g.drawRect(i+offsetx, y+offsety, xSize, ySize);
			}
		}

	}

}
