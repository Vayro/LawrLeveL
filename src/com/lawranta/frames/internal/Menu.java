package com.lawranta.frames.internal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import com.lawranta.file.Open;
import com.lawranta.file.Save;
import com.lawranta.panels.CanvasPanel;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Menu extends JMenuBar {
	
	JMenu fileMenu;
	ActionListener fileAction;

	
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @return 
	 */
	
		
	public Menu() {
		
	
		
		 fileMenu = new JMenu("File");
		fileAction=new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				switch(e.getActionCommand()) {
				case "save":
					Save.save();
					break;
				case "saveAs":
					Save.saveAs();
					break;
				case "open":
					Open.open();
					break;
				case "new":
					
					
					CanvasPanel.canvasContainer.clear();
					CanvasPanel.contentPanel.removeAll();
					CanvasPanel.contentPanel.add(CanvasPanel.gridPane, 3, 0);
					
					break;
				default:break;
				
				
				
				
				}}};
		
		
		
		
		
		add(fileMenu);
		
		FileMenuItem newMenuItem = new FileMenuItem("New", "new");

		
		FileMenuItem openMenuItem = new FileMenuItem("Open","open");
		
		FileMenuItem SaveMenuItem = new FileMenuItem("Save", "save");

		
		FileMenuItem SaveAsMenuItem = new FileMenuItem("Save As...", "saveAs");
		
		
		JMenuItem refreshMenuItem = new JMenuItem("Refresh");
		fileMenu.add(refreshMenuItem);
		
		JMenuItem exitMenuItem = new JMenuItem("Exit =(");
		fileMenu.add(exitMenuItem);
		
		JMenu editMenu = new JMenu("Edit");
		add(editMenu);
		
		JMenu helpMenu = new JMenu("Help");
		add(helpMenu);
		
		
		
		
		
		
			
			
			
			
		}
		
		
		
	public class FileMenuItem extends JMenuItem{

		
	public FileMenuItem(String text, String command)  {
		setText(text);
		setActionCommand(command);
		addActionListener(fileAction);
		fileMenu.add(this);
		
		
		
		
	}}
		
		

		
		

	}


