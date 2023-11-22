package com.lawranta.frames.internal;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.lawranta.frames.MainFrame;
import com.lawranta.globals.GLOBAL;
import com.lawranta.layers.Layer;
import com.lawranta.layers.LayerContainer;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Component;

public class LayersDialog extends JDialog {

	private final JPanel contentPanel = new JPanel(), superPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LayersDialog dialog = new LayersDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LayersDialog() {
		setSize(new Dimension(175, 0));
		setMinimumSize(new Dimension(128, 400));
		setAlwaysOnTop(true);
		setUndecorated(true);
		contentPanel.setAlignmentY(Component.TOP_ALIGNMENT);

		contentPanel.setSize(new Dimension(175, 400));
		contentPanel.setPreferredSize(new Dimension(160, 355));
		contentPanel.setMinimumSize(new Dimension(128, 10));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		Dimension buttonSize = new Dimension(128, 32);
		Dimension topButtonSize = new Dimension(34, 34);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setMinimumSize(new Dimension(128, 23));
		getContentPane().add(superPanel);

		JPanel topPanel = new JPanel();
		topPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		topPanel.setAlignmentX(0.0f);

		Icon icon = new ImageIcon(MainFrame.class.getResource(GLOBAL.moveIconPath));
		JButton moveButton = new JButton(icon);
		Icon exitIcon = new ImageIcon(MainFrame.class.getResource(GLOBAL.exitIconPath));
		JButton xButton = new JButton(exitIcon);
		moveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		moveButton.setFocusable(false);
		moveButton.setHorizontalAlignment(SwingConstants.RIGHT);
		moveButton.setPreferredSize(topButtonSize);
		moveButton.setMaximumSize(topButtonSize);
		moveButton.setBorderPainted(false);
		moveButton.setContentAreaFilled(false);
		moveButton.setFocusPainted(false);
		moveButton.setOpaque(false);
		xButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		xButton.setFocusable(false);
		xButton.setHorizontalAlignment(SwingConstants.RIGHT);
		xButton.setMaximumSize(topButtonSize);
		xButton.setPreferredSize(topButtonSize);
		xButton.setBorderPainted(false);
		xButton.setContentAreaFilled(false);
		xButton.setFocusPainted(false);
		xButton.setOpaque(false);
		xButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}

		});

		topPanel.setLayout(new BorderLayout(0, 0));

		topPanel.add(moveButton, BorderLayout.CENTER);
		topPanel.add(xButton, BorderLayout.EAST);
		moveButton.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("moving");

				int x = (int) (MouseInfo.getPointerInfo().getLocation().getX() - moveButton.getLocation().getX()
						- (moveButton.getWidth() / 2));
				int y = (int) (MouseInfo.getPointerInfo().getLocation().getY() - moveButton.getLocation().getY()
						- (moveButton.getHeight() / 2));

				setLocation(new Point(x - 80, y));
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		superPanel.setLayout(new BoxLayout(superPanel, BoxLayout.Y_AXIS));

		superPanel.add(topPanel);
		
		JLabel lblNewLabel = new JLabel("Layers");
		topPanel.add(lblNewLabel, BorderLayout.WEST);

		superPanel.add(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));

		contentPanel.add(scrollPane);

		JPanel layerPanel = new JPanel();
		layerPanel.setMaximumSize(new Dimension(128, 32767));

		//populate with one button for each existing later
		
		for (int i = 0; i < LayerContainer.getLayerArraySize(); i++) {

			Layer layer = LayerContainer.getLayerContainer().get(i);
			JButton lButton = null;

			if (layer.isActive()) {
				lButton = new JButton(layer.getLayerName() + " (active)");

			} else {
				lButton = new JButton(layer.getLayerName());

			}
			lButton.setPreferredSize(buttonSize);
			lButton.setMaximumSize(buttonSize);
			
			
			//action listener for each button
			lButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(layer.isActive())
					{
						System.out.println("This is the active layer.");
						
					}
					else
					{
						System.out.println("Set " + layer.getLayerName() + " to active layer");
						LayerContainer.setActive(layer);
						dispose();

						
					}
				}
				
				
			});
			
			
			
			
			layerPanel.add(lButton);

		}

		JButton newButton = new JButton("New Layer");
		newButton.setPreferredSize(buttonSize);
		newButton.setMaximumSize(buttonSize);
		newButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LayerContainer.newLayer();
				dispose();

			}

		});

		JButton clearButton = new JButton("Clear Layer");
		clearButton.setPreferredSize(buttonSize);
		clearButton.setMaximumSize(buttonSize);
		JButton deleteButton = new JButton("Delete Layer");
		deleteButton.setPreferredSize(buttonSize);
		deleteButton.setMaximumSize(buttonSize);
		layerPanel.setLayout(new BoxLayout(layerPanel, BoxLayout.PAGE_AXIS));
		layerPanel.add(new JLabel("-"));
		layerPanel.add(newButton);
		layerPanel.add(clearButton);
		layerPanel.add(deleteButton);

		scrollPane.setViewportView(layerPanel);
		scrollPane.setPreferredSize(new Dimension(128, 300));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		setVisible(true);
	}

}
