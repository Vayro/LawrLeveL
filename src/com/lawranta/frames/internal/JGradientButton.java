package com.lawranta.frames.internal;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.border.TitledBorder;

public final class JGradientButton extends JButton {
	boolean mouseEntered=false;
	Color color, hoverColor=Color.LIGHT_GRAY;
    public JGradientButton(String title, Color color) {

    	
        super();
    	String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());  
    	setText(hex);
        setContentAreaFilled(false);
        setFocusPainted(true); // used for demonstration
        setBorder(new TitledBorder(title));
        this.color=color;
        addMouseListener(new MouseListener(){
        	

        	public void mouseClicked(MouseEvent e) {
        		// TODO Auto-generated method stub
        		
        	}

        	public void mousePressed(MouseEvent e) {
        		// TODO Auto-generated method stub
        		
        	}

        	
        	public void mouseReleased(MouseEvent e) {
        		// TODO Auto-generated method stub
        		
        	}

        	
        	public void mouseEntered(MouseEvent e) {
        		// TODO Auto-generated method stub
        		mouseEntered=true;
        		hoverColor=Color.WHITE;
        		System.out.println("mouse entered: " + mouseEntered);
        	}

        	
        	public void mouseExited(MouseEvent e) {
        		// TODO Auto-generated method stub
        		mouseEntered=false;
        		hoverColor=Color.LIGHT_GRAY;
        		System.out.println("mouse entered: " + mouseEntered);
        	}
        	
        	
        	
        	
        });
        
        
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        final Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(new GradientPaint(
                new Point(0, 0), 
                hoverColor, 
                new Point(0, getHeight()), 
               color.darker()));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();

        super.paintComponent(g);
    }

	public void setColor(Color color) {
		// TODO Auto-generated method stub
		this.color=color;
	//	hoverColor=color;
		setText(String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()));
	}




}