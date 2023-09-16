package com.lawranta.canvas;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.lawranta.globals.GLOBAL;
import com.lawranta.panels.CanvasPanel;

public class InkDrop extends JPanel {
int x, y, xSize, ySize; boolean removed=false;
	


@Override
protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	if(!removed) {
	g.setColor(Color.BLACK);
	g.fillRect(0,0,xSize, ySize);
	System.out.println("painted " + this.toString());}
	else {
		
		
		g.clearRect(0,0,xSize, ySize);
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


	
	
	public InkDrop(int d, int e, int xSize, int ySize) {
		setRequestFocusEnabled(false);
		setBackground(new Color(0, 0, 255));
		// TODO Auto-generated constructor stub
		
		this.x=(int) d;
		this.y=(int) e;
		this.xSize=xSize;
		this.ySize=ySize;
		
	int	gridSnapx=(int) (d%xSize);
	int gridSnapy=(int) (e%ySize);	
		
		this.x=d-gridSnapx;
		this.y=e-gridSnapy;
		

	setBounds(x,y,xSize, ySize);
	setLayout(null);
		System.out.println("inkDrop created at " + x + "," + y + " Size(" + xSize + "," + ySize + ")");
		
		
		
		
for(int i=0;i<CanvasPanel.canvasContainer.size();i++) {
			System.out.println(i+" out of "+CanvasPanel.canvasContainer.size());
			if(x==CanvasPanel.canvasContainer.get(i).getX() && y==CanvasPanel.canvasContainer.get(i).getY())
		{
				GLOBAL.CP.remove(CanvasPanel.canvasContainer.get(i));
				CanvasPanel.canvasContainer.remove(i);
			//	i--;
				GLOBAL.CP.contentPanel.repaint();
			}
			
			
		}
		
		
		
		
		
		
		
		
	//	paintComponent(getGraphics());
	}


	public void destroy(JPanel contentPanel) {
		// TODO Auto-generated method stub
		System.out.println(" deleting mySelf... :( ");

	//	x=0;y=0;xSize=0; ySize=0;
		setVisible(false);
		removed=true;
revalidate();
repaint();
		contentPanel.remove(this);

		
	}

}
