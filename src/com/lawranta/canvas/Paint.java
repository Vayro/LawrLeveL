package com.lawranta.canvas;

import java.awt.Color;

public interface Paint {

	static int x = 0, y = 0, xSize = 0, ySize = 0;
	boolean removed = false;
	public static Color color = SelectedTool.selectedColor;

	int getId();

	void setId(int id);

	public int getX();

	public void setX(int x);

	public int getY();

	public void setY(int y);

	public int getxSize();

	public void setxSize(int xSize);

	public int getySize();

	public void setySize(int ySize);

	public void setColor(Color color);

	public Color getColor();

	default void remove() {
	}

	default void draw() {
	}

	void destroy(boolean removeFromContainer);

}
