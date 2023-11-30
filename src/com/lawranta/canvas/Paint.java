package com.lawranta.canvas;

import java.awt.Color;

import com.lawranta.layers.Layer;

public interface Paint {

	static int x = 0, y = 0, xSize = 0, ySize = 0, opacity=100;
	static Layer layer = null;
	boolean removed = false, selected = true, visible =true;
	public static Color color = SelectedTool.selectedColor;

	int getId();

	public boolean isSelected();

	public void setSelected(boolean selected);

	void setId(int id);

	public int getX();

	public void setX(int x);

	public int getY();

	public void setY(int y);

	public int getxSize();

	public void setxSize(int xSize);

	public int getySize();

	public Layer getLayer();

	public void setySize(int ySize);

	public void setColor(Color color);

	public void setOpacity(int opacity);
	
	public int getOpacity();

	public Color getColor();

	default void remove() {
	}

	default void draw() {
	}

	void destroy(boolean removeFromContainer);

	Runnable refresh();



	void toggleVisibility(boolean v);

	void setLayer(Layer layer);

}
