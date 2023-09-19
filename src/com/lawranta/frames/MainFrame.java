package com.lawranta.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import com.lawranta.frames.internal.Toolbar;
import com.lawranta.globals.GLOBAL;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.lawranta.panels.*;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import java.awt.Rectangle;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	Toolbar toolbar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		
		GLOBAL.CP = new CanvasPanel();
		setMinimumSize(new Dimension(320, 240));
		setSize(new Dimension(1024, 768));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	
		toolbar = new Toolbar();
		toolbar.setMaximumSize(new Dimension(64, 2147483647));
		toolbar.setMinimumSize(new Dimension(64, 34));
		toolbar.setNormalBounds(new Rectangle(100, 100, 64, 300));
		toolbar.setResizable(true);
		toolbar.setSize(new Dimension(64, 300));
		toolbar.setVisible(true);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		contentPane.add(toolbar);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(648, 480));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(GLOBAL.CP);

		

		
	}

}
