package com.lawranta.canvas;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.lawranta.panels.*;

public class TextNode extends JTextField implements Paint {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4669795077837742209L;
	int id, x, y, xSize, ySize; /**
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


	/**
	 * @return the removed
	 */
	public boolean isRemoved() {
		return removed;
	}


	/**
	 * @param removed the removed to set
	 */
	public void setRemoved(boolean removed) {
		this.removed = removed;
	}







	boolean removed=false;


	
	
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







/**
 * Create the panel.
 */
public TextNode(int x, int y) {
	
	TextNode thisNode=this;
	addFocusListener(new FocusListener() {

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			setEditable(true);
			setBackground(new Color(255,255,255));
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			setEditable(false);
			setBackground(new Color(255,255,255, 50));
			
			
			if(getText().isBlank())
			{
				//setVisible(false);
				CanvasPanel.contentPanel.remove(thisNode);
				System.out.println("removed text node");
			}
			
			
			
			
			
			
			
			CanvasPanel.contentPanel.revalidate();
			CanvasPanel.contentPanel.repaint();
			
		}
		
		
	
	});
	
	
	
addKeyListener(new KeyListener() {

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==10) {
			CanvasPanel.contentPanel.requestFocus();
		
		}
		
		if(e.getKeyCode()==127) {
			CanvasPanel.contentPanel.requestFocus();
			CanvasPanel.contentPanel.remove(thisNode);
		
		}
		
		
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
});
	
	
	setDragEnabled(true);
	setBackground(new Color(255,255,255, 50));
	setSize(new Dimension(128, 32));
	setMaximumSize(new Dimension(128, 128));
	setPreferredSize(new Dimension(128, 32));
	setMinimumSize(new Dimension(128, 128));

	
	
	this.x=x; this.y=y;
	
	//this.setBackground(new Color(0,0,0,100));
	this.setLocation(x, y);
	this.setText("New Text");
	this.setVisible(true);
	this.requestFocus();
	this.grabFocus();
	System.out.print("new text node");

	
}

}
