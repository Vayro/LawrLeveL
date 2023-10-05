package com.lawranta.canvas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.lawranta.globals.GLOBAL;
import com.lawranta.panels.CanvasPanel;

public class InkDrop extends JPanel implements Paint {
	int id, x, y, xSize, ySize, offsetX, offsetY, mouseButton=0;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void setRemoved(boolean removed) {
		this.removed = removed;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	boolean removed = false;
	Color color = SelectedTool.selectedColor;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!removed) {
			g.setColor(color);
			g.fillRect(0, 0, xSize, ySize);
			
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

	public InkDrop(int x, int y, int xSize, int ySize,int offsetX,int offsetY) {
		setRequestFocusEnabled(false);
		setBackground(this.color);
		// TODO Auto-generated constructor stub

		this.xSize = xSize;
		this.ySize = ySize;
		this.offsetX=offsetX;
		this.offsetY=offsetY;
		int origX = x;
		int origY = y;

		int gridSnapx = (int) (x % xSize);
		int gridSnapy = (int) (y % ySize);

		this.x = x - gridSnapx-this.offsetX;
		this.y = y - gridSnapy-this.offsetY;

		if(this.x+xSize<origX) {
			
			this.x+=xSize;
		}
		
		if(this.y+xSize<origY) {
			
			this.y+=ySize;
		}
		
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

		MouseMotionListener listener = new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("dragged");
				if(mouseButton==3)
				{
				startDeleting();
			}
				
				if(e.getButton()==1) {  
					if(SelectedTool.selectedTool==3) {
						CanvasPanel.startErasing();
						
						
					}
					
					
				}

			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
			
			}
			
			
			
			
		};
		
		
		
		
		MouseListener clickListener = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub	
				mouseButton=e.getButton();
				if(e.getButton()==3)
				{
				startDeleting();
				
			}
				if(e.getButton()==1) {
					if(SelectedTool.selectedTool==3) {
						CanvasPanel.mouse=1;
						CanvasPanel.startErasing();
						
						
					}
					
					
				}
				
				
				
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				mouseButton=0;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}};
		
		
		addMouseListener(clickListener);
		addMouseMotionListener(listener);
		
		
		
		 checkCollision() ;
	}

	public void destroy(boolean removeFromContainer) {
		// TODO Auto-generated method stub
		System.out.println(" deleting mySelf... :( ");

		// x=0;y=0;xSize=0; ySize=0;
		setVisible(false);
		removed = true;
		revalidate();
		repaint();
		CanvasPanel.contentPanel.remove(this);
		
		if(removeFromContainer) {
			CanvasPanel.canvasContainer.remove(this.id); 
			for(int i=0;i< CanvasPanel.canvasContainer.size(); i++) {
				
				CanvasPanel.canvasContainer.get(i).setId(i);
			}
		}

		
		
		
		
		
		
		
		
		
		
		
	}
	
	private void startDeleting() {
		
		
//
		destroy(true);
		
		
		
		for(int i =0; i< CanvasPanel.canvasContainer.size(); i++) {
			
			
			
			
			CanvasPanel.canvasContainer.get(i).setId(i); 
			
			
			
			
			
		}
			
				CanvasPanel.revalidateAndRepaint();
			
		

	}
	
	private void checkCollision() {
		
		

		for (int i = 0; i < CanvasPanel.canvasContainer.size(); i++) {

			System.out.println("Checking: " + i);
			
			if(CanvasPanel.canvasContainer.get(i).getClass()==InkDrop.class){
				//first check if inkdrop
			
			
			if (this.x ==  CanvasPanel.canvasContainer.get(i).getX() && this.y == CanvasPanel.canvasContainer.get(i).getY()
					&& this.xSize == CanvasPanel.canvasContainer.get(i).getxSize() && this.ySize == CanvasPanel.canvasContainer.get(i).getySize()) 
					
					 {

				System.out.println("collision detected");
			(	(InkDrop) CanvasPanel.canvasContainer.get(i)).destroy(true);

				CanvasPanel.revalidateAndRepaint();

				;
			}
			}
		}
		
		
		
		
		
		
	}

}
