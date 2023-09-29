package com.lawranta.canvas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.lawranta.globals.GLOBAL;
import com.lawranta.panels.CanvasPanel;

public class InkDrop extends JPanel implements Paint {
	int x, y, xSize, ySize;
	boolean removed = false;
	Color color = SelectedTool.selectedColor;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!removed) {
			g.setColor(color);
			g.fillRect(0, 0, xSize, ySize);
			System.out.println("painted " + this.toString());
		} else {

			g.clearRect(0, 0, xSize, ySize);
		}

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getxSize() {
		return xSize;
	}

	public void setxSize(int xSize) {
		this.xSize = xSize;
	}

	public int getySize() {
		return ySize;
	}

	public void setySize(int ySize) {
		this.ySize = ySize;
	}

	public InkDrop(int x, int y, int xSize, int ySize) {
		setRequestFocusEnabled(false);
		setBackground(this.color);
		// TODO Auto-generated constructor stub

		this.xSize = xSize;
		this.ySize = ySize;

		int gridSnapx = (int) (x % xSize);
		int gridSnapy = (int) (y % ySize);

		this.x = x - gridSnapx;
		this.y = y - gridSnapy;

		setBounds(this.x, this.y, this.xSize, this.ySize);
		setLayout(null);
		System.out.println("inkDrop created at " + this.x + "," + this.y + " Size(" + xSize + "," + ySize + ")");

	

		
		
		
		for (int i = 0; i < CanvasPanel.canvasContainer.size(); i++) {
			System.out.println(i + " out of " + CanvasPanel.canvasContainer.size());
			if (x == ((Component) CanvasPanel.canvasContainer.get(i)).getX() && y == ((Component) CanvasPanel.canvasContainer.get(i)).getY()) {
				GLOBAL.CP.remove((Component) CanvasPanel.canvasContainer.get(i));
				CanvasPanel.canvasContainer.remove(i);
				// i--;
				GLOBAL.CP.contentPanel.repaint();
			}

		}

		// paintComponent(getGraphics());
	}

	public void destroy(JLayeredPane contentPanel) {
		// TODO Auto-generated method stub
		System.out.println(" deleting mySelf... :( ");

		// x=0;y=0;xSize=0; ySize=0;
		setVisible(false);
		removed = true;
		revalidate();
		repaint();
		contentPanel.remove(this);

	}

}
