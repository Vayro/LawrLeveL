package com.lawranta.frames.internal;

import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.MouseInfo;
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
import javax.swing.border.LineBorder;

import com.lawranta.canvas.Tool;
import com.lawranta.globals.GLOBAL;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.Dimension;

public class Toolbar extends JInternalFrame {

	public static ActionListener actionListener;
	public static ColorChooser j;
	static toolButton colorButton;
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
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getButton() == 2)
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

		
		
		
		
		
		
		
		 actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				switch(e.getActionCommand())
				{
				case "Brush": 
					Tool.setInkDropTool();
					System.out.println("Selected Tool: " + Tool.selectedTool);
					
					break;
					
					
				case "Aa":
					Tool.setTextTool();
					System.out.println("Selected Tool: " + Tool.selectedTool);
					break;
					
				case "Color": 
					
					j = new ColorChooser();
					
					
					
					break;
					
					
					
				case "Canvas":
					
					
					
					
					break;
				
				case "Eraser":
					break;
				
				default:
				break;
				
				}
				
				
				
	
				
				
			}
			
			
			
		};
		
		
		
		
		
		
		
		
		
		
		
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		toolButton brushToolButton = new toolButton("Brush", panel);
	
	

		toolButton textToolButton = new toolButton("Aa", panel);

	

		toolButton canvasSizeButton = new toolButton("Canvas", panel);
	

		

		toolButton eraserButton = new toolButton("Eraser", panel);
	
		

		colorButton = new toolButton("Color", panel);
		toolButton.colorBorder(colorButton);
		
		
		setBounds(100, 100, 450, 300);

	}

	class toolButton extends JButton {

		public toolButton(String s, JPanel p) {
			// TODO Auto-generated constructor stub
			setFont(GLOBAL.toolFont);
			setText(s);
			setPreferredSize(new Dimension(64, 64));
			setMinimumSize(new Dimension(64, 64));
			setMaximumSize(new Dimension(64, 64));
			setMargin(new Insets(0, 0, 0, 0));
			setActionCommand(s);
			addActionListener(Toolbar.actionListener);
	
			p.add(this);
			
		}
			
		public static void colorBorder(toolButton c){ 
			
			
			c.setBorder(BorderFactory.createLineBorder(Tool.selectedColor, 2, true));
		}
			
			
			
			
		}

		/**
		 * 
		 */
		private static final long serialVersionUID = 3704138041001210203L;

	}

