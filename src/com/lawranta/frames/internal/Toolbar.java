package com.lawranta.frames.internal;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextPane;

import com.lawranta.canvas.Tool;

import javax.swing.JButton;
import javax.swing.Action;
import javax.swing.BoxLayout;
import java.awt.Dimension;

public class Toolbar extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Toolbar frame = new Toolbar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Toolbar() {
		setIconifiable(true);
		setMaximizable(true);
	setBackground(new Color(0, 0, 0));
		addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getButton()==2)
				System.out.println("Selected Tool: " + Tool.selectedTool);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
	
	
	
	
	
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JButton textToolButton = new JButton("AA");
		textToolButton.setPreferredSize(new Dimension(64, 64));
		textToolButton.setMinimumSize(new Dimension(64, 64));
		textToolButton.setMaximumSize(new Dimension(64, 64));
		textToolButton.addActionListener(new ActionListener(){


			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Tool.setTextTool();
				System.out.println("Selected Tool: " + Tool.selectedTool);
			}
			
			
		
		});
		panel.add(textToolButton);
		
		JButton brushToolButton = new JButton("Brush");
		brushToolButton.setPreferredSize(new Dimension(64, 64));
		brushToolButton.setMinimumSize(new Dimension(64, 64));
		brushToolButton.setMaximumSize(new Dimension(64, 64));
		brushToolButton.addActionListener(new ActionListener() {

	

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Tool.setInkDropTool();
				System.out.println("Selected Tool: " + Tool.selectedTool);
			}
			
			
		
		});
		panel.add(brushToolButton);
		setBounds(100, 100, 450, 300);

	}
	

	

}
