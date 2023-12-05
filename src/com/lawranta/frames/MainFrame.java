package com.lawranta.frames;

import java.awt.EventQueue;

import com.lawranta.frames.internal.HeaderBar;
import com.lawranta.frames.internal.Menu;
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
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Toolbar toolbar;
	HeaderBar headerbar;
	public static JScrollPane scrollPane;

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
		JFrame pointerToThisFrame=this;
		setIconImage(GLOBAL.FAVICO);
		setTitle("LawrLeveler (" + GLOBAL.fileInfo.getFileName() + ")");
		setFocusable(true);
		GLOBAL.CP = new CanvasPanel();
		setMinimumSize(new Dimension(320, 240));
		setSize(new Dimension(1024, 768));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		// setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(69, 69, 69));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 32, 32, 32, 32, 32, 32, 32, 32, 32, 32 };
		gbl_contentPane.rowHeights = new int[] { 32, 32, 32, 32, 32, 32, 32, 32, 32, 32 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		GLOBAL.MENU = new Menu();
		GLOBAL.MENU.setVisible(true);
		GLOBAL.MENU.setFocusable(true);
		GridBagConstraints gbc_menu = new GridBagConstraints();
		gbc_menu.fill = GridBagConstraints.HORIZONTAL;
		gbc_menu.gridwidth = 10;
		gbc_menu.insets = new Insets(0, 0, 0, 5);
		gbc_menu.gridx = 0;
		gbc_menu.gridy = 0;
		contentPane.add(GLOBAL.MENU, gbc_menu);

		
		
		headerbar = new HeaderBar();
		headerbar.setMaximumSize(new Dimension(500, 64));
		GridBagConstraints gbc_headerbar = new GridBagConstraints();
		gbc_headerbar.gridx = 1;
		gbc_headerbar.gridy = 1;
		contentPane.add(headerbar, gbc_headerbar);
		
		toolbar = new Toolbar();
		toolbar.setMaximumSize(new Dimension(64, 2147483647));
		toolbar.setMinimumSize(new Dimension(64, 34));
		// toolbar.setNormalBounds(new Rectangle(100, 100, 64, 300));
		// toolbar.setResizable(true);
		toolbar.setSize(new Dimension(64, 300));
		toolbar.setVisible(true);
		GridBagConstraints gbc_toolbar = new GridBagConstraints();
		gbc_toolbar.fill = GridBagConstraints.VERTICAL;
		gbc_toolbar.gridheight = 9;
		gbc_toolbar.insets = new Insets(0, 0, 0, 5);
		gbc_toolbar.gridx = 0;
		gbc_toolbar.gridy = 2;
		contentPane.add(toolbar, gbc_toolbar);
		scrollPane = new JScrollPane() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		};

		MouseListener mListener = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

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
				System.out.println("Attempting frame grabFocus");
				pointerToThisFrame.requestFocus();
				CanvasPanel.mouse = 0;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				System.out.println("Attempting frame grabFocus");
				pointerToThisFrame.requestFocus();
				// TODO Auto-generated method stub

			}

		};

		scrollPane.addMouseListener(mListener);
		scrollPane.setPreferredSize(new Dimension(648, 480));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.anchor = GridBagConstraints.NORTHWEST;
		gbc_scrollPane.gridwidth = 9;
		gbc_scrollPane.gridheight = 9;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);
		scrollPane.setViewportView(GLOBAL.CP);

	}

}
