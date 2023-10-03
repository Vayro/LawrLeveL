package com.lawranta.frames.internal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import com.lawranta.file.Export;
import com.lawranta.file.Open;
import com.lawranta.file.Save;
import com.lawranta.globals.GLOBAL;
import com.lawranta.panels.CanvasPanel;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;
import java.awt.Component;

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

	
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(GLOBAL.darkColor);
 
        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

    }
	
	
	
	/**
	 * Create the frame.
	 * 
	 * @return
	 */

	public Menu() {
		setForeground(new Color(255, 255, 204));
		//setForeground(new Color(255, 255, 255));
		setBackground(new Color(69, 69, 69));
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.setOpaque(false);
		
		fileMenu = new JMenu("File");
		fileMenu.setBackground(new Color(69, 69, 69));
		fileAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				switch (e.getActionCommand()) {
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
					GLOBAL.setDefault();
					CanvasPanel.setCanvasSize();

					break;
				case "export":
					Export.export();
					break;
				case "exit":
					System.exit(0);
					break;
				default:
					
					break;

				}
			}
		};

		add(fileMenu);

		FileMenuItem newMenuItem = new FileMenuItem("New", "new");

		FileMenuItem openMenuItem = new FileMenuItem("Open", "open");

		FileMenuItem SaveMenuItem = new FileMenuItem("Save", "save");

		FileMenuItem SaveAsMenuItem = new FileMenuItem("Save As...", "saveAs");
		FileMenuItem ExportMenuItem = new FileMenuItem("Export as PNG", "export");

		JMenuItem refreshMenuItem = new JMenuItem("Refresh");
		fileMenu.add(refreshMenuItem);

		FileMenuItem exitMenuItem = new FileMenuItem("Exit :(", "exit");
		

		JMenu editMenu = new JMenu("Edit");
		add(editMenu);

		JMenu helpMenu = new JMenu("Help");
		add(helpMenu);

	}

	public class FileMenuItem extends JMenuItem {

		public FileMenuItem(String text, String command) {
			setText(text);
			setActionCommand(command);
			addActionListener(fileAction);
			fileMenu.add(this);

		}
	}

}
