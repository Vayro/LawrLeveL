package com.lawranta.canvas;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.SwingUtilities;

import com.lawranta.globals.GLOBAL;

public class CreateGrid {

	int xSize, ySize, roomWidth, roomHeight, offsetx, offsety;

	public CreateGrid(Graphics g, int xSize, int ySize, int roomWidth, int roomHeight, Color c) {
		// TODO Auto-generated constructor stub

		this.xSize = xSize;
		this.ySize = ySize;
		this.roomWidth = roomWidth;
		this.roomHeight = roomHeight;
		this.offsetx=-(GLOBAL.OFFSETX/2);
		this.offsety=-(GLOBAL.OFFSETY/2);
		// Graphics square = new Graphics();

		g.setColor(c);

		for (int i = 0+offsetx; i < roomWidth-offsetx; i += xSize) {

			for (int y = 0+offsety; y < roomHeight-offsety; y += ySize) {
				g.drawRect(i+offsetx-1, y+offsety-1, xSize, ySize);
			}
		}

	}

}
