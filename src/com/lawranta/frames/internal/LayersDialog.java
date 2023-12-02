package com.lawranta.frames.internal;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JSlider;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.lawranta.canvas.Paint;
import com.lawranta.frames.MainFrame;
import com.lawranta.globals.GLOBAL;
import com.lawranta.layers.Layer;
import com.lawranta.layers.LayerContainer;
import com.lawranta.panels.CanvasPanel;
import com.lawranta.popups.ConfirmDialog;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Component;
import javax.swing.border.BevelBorder;

public class LayersDialog extends JDialog {

	private final JPanel contentPanel = new JPanel(), superPanel = new JPanel();
	Dimension topButtonSize = new Dimension(34, 34);

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
		setSize(new Dimension(128, 0));
		setMinimumSize(new Dimension(128, 400));
		setAlwaysOnTop(true);
		setUndecorated(true);
		setFocusable(false);
		contentPanel.setAlignmentY(Component.TOP_ALIGNMENT);

		contentPanel.setSize(new Dimension(120, 400));
		contentPanel.setPreferredSize(new Dimension(120, 355));
		contentPanel.setMinimumSize(new Dimension(100, 10));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		Dimension buttonSize = new Dimension(128, 32);
		Dimension smallerButtonSize = new Dimension(100, 32);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setMinimumSize(new Dimension(120, 23));
		superPanel.setPreferredSize(new Dimension(128, 400));
		getContentPane().add(superPanel);

		JPanel topPanel = new JPanel();
		topPanel.setMinimumSize(new Dimension(128, 10));
		topPanel.setPreferredSize(new Dimension(128, 10));
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

				setLocation(new Point(x - 40, y));
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		superPanel.setLayout(new BoxLayout(superPanel, BoxLayout.Y_AXIS));

		superPanel.add(topPanel);

		superPanel.add(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

		contentPanel.add(scrollPane);

		JPanel layerPanel = new JPanel();
		layerPanel.setMaximumSize(new Dimension(120, 32767));

		// populate with one button for each existing later

		// set icons for visbility, clear, delete
		Icon visibleIcon = new ImageIcon(MainFrame.class.getResource(GLOBAL.openEyeIconPath));
		Icon invisibleIcon = new ImageIcon(MainFrame.class.getResource(GLOBAL.closedEyeIconPath));
		Icon clearIcon = new ImageIcon(MainFrame.class.getResource(GLOBAL.clearIconPath));
		Icon trashIcon = new ImageIcon(MainFrame.class.getResource(GLOBAL.trashEyeIconPath));
		Icon greyTrashIcon = new ImageIcon(MainFrame.class.getResource(GLOBAL.greyTrashIconPath));

		for (int i = 0; i < LayerContainer.getLayerArraySize(); i++) {

			Layer layer = LayerContainer.getLayerContainer().get(i);
			JButton lButton = null;

			if (layer.isActive()) {
				lButton = new JButton("✔ " + layer.getLayerName());

			} else {
				lButton = new JButton(layer.getLayerName());

			}
			lButton.setPreferredSize(smallerButtonSize);
			lButton.setMaximumSize(smallerButtonSize);
			lButton.setToolTipText("id: " + layer.getLayerID());

			// action listener for each button
			lButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (layer.isActive()) {
						System.out.println("This is the active layer.");

					} else {
						System.out.println("Set " + layer.getLayerName() + " to active layer");
						LayerContainer.setActive(layer);
						dispose();

					}
				}

			});

			JPanel buttonPanel = new JPanel();

			buttonPanel.setPreferredSize(new Dimension(100, 64));
			buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
			buttonPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
			buttonPanel.setMinimumSize(new Dimension(100, 64));

			buttonPanel.add(lButton);

			JPanel iconButtonPanel = new JPanel();
			if (i % 2 == 0) {
				buttonPanel.setBackground(new Color(255, 255, 255, 255));
				iconButtonPanel.setBackground(new Color(100, 100, 100, 255));

			} else {

				buttonPanel.setBackground(new Color(200, 200, 200, 255));
				iconButtonPanel.setBackground(new Color(150, 150, 150, 255));

			}

			iconButtonPanel.setPreferredSize(new Dimension(200, 32));
			iconButtonPanel.setSize(new Dimension(200, 32));
			iconButtonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
			// iconButtonPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
			// null, null));
			iconButtonPanel.setLayout(new BoxLayout(iconButtonPanel, BoxLayout.X_AXIS));

			JButton clearButton = new iconButton(clearIcon, "clear");
			clearButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

					ConfirmDialog confirm = new ConfirmDialog("Clear entire layer of its contents?");
					confirm.setVisible(true);
					ActionEvent c = confirm.getA();

					switch (c.getActionCommand()) {

					case "OK":

						LayerContainer.clearLayer(layer);

						break;
					case "Cancel":
						System.out.println("cancelled");

						break;

					}

				}

			});

			JButton trashButton = new iconButton(trashIcon, "delete layer");
			if (layer.getLayerID() == 0) {

				trashButton.setIcon(greyTrashIcon);
				trashButton.setToolTipText("Cannot delete base layer. (" + layer.getLayerName() + ")");
			} else {

				trashButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub

						// TODO Auto-generated method stub

						ConfirmDialog confirm = new ConfirmDialog("Delete Layer? This cannot be undone.");
						confirm.setVisible(true);
						ActionEvent c = confirm.getA();

						switch (c.getActionCommand()) {

						case "OK":

							LayerContainer.clearLayer(layer);

							LayerContainer.deleteLayer(layer);
							dispose();
							break;
						case "Cancel":
							System.out.println("cancelled");

							break;

						}
					}

				});

			}

			JButton visibleButton;

			if (layer.isVisible()) {
				visibleButton = new iconButton(visibleIcon, "visible");

			} else {
				visibleButton = new iconButton(invisibleIcon, "invisible");

			}
			visibleButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					LayerContainer.toggleVisibility(layer);
					dispose();
				}

			});

			iconButtonPanel.add(visibleButton);
			iconButtonPanel.add(clearButton);
			iconButtonPanel.add(trashButton);

			
			
			//Opacity Panel goes here
			
			
			JPanel opacityPanel = new JPanel();
			opacityPanel.setPreferredSize(new Dimension(100, 64));
			opacityPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
			opacityPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			opacityPanel.setLayout(new BoxLayout(opacityPanel, BoxLayout.Y_AXIS));
			opacityPanel.setMinimumSize(new Dimension(100, 64));
			JSlider opacitySlider = new JSlider();
			
			opacitySlider.setPaintTicks(true);
			opacitySlider.setPaintLabels(true);
			opacitySlider.setMinorTickSpacing(25);
			opacitySlider.setMinimum(0);
			opacitySlider.setMaximum(100);
			opacitySlider.setMajorTickSpacing(50);
			opacitySlider.setPreferredSize(new Dimension(100, 64));
			opacitySlider.setValue(layer.getOpacity());
			opacitySlider.addChangeListener(new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					
					double opacity = ((double)opacitySlider.getValue()/(double)100)*255;
					
					System.out.println(layer.getLayerName() + " opacity is set at :");
					System.out.println((int) opacity);
					
					layer.setOpacity((int) opacity);

				}

			});
			
			
			
			
			opacityPanel.add(opacitySlider);
			buttonPanel.add(iconButtonPanel);
			buttonPanel.add(opacityPanel);
			layerPanel.add(buttonPanel);
			layerPanel.add(new JLabel(" "));

		}

		JButton newButton = new JButton("New Layer");
		newButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		newButton.setPreferredSize(smallerButtonSize);
		newButton.setMaximumSize(smallerButtonSize);
		newButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LayerContainer.newLayer();
				dispose();

			}

		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		buttonPanel.setPreferredSize(smallerButtonSize);
		// buttonPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null,
		// null));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

		layerPanel.setLayout(new BoxLayout(layerPanel, BoxLayout.Y_AXIS));
		layerPanel.add(buttonPanel);

		scrollPane.setViewportView(layerPanel);
		scrollPane.setPreferredSize(new Dimension(120, 300));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPanel.add(newButton);
		setVisible(true);
	}

	class iconButton extends JButton {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1905845524455077544L;

		iconButton(Icon icon, String toolTip) {
			setIcon(icon);
			setToolTipText(toolTip);
			setAlignmentX(Component.CENTER_ALIGNMENT);
			setFocusable(false);
			setHorizontalAlignment(SwingConstants.CENTER);
			setPreferredSize(new Dimension(32, 32));
			setMaximumSize(new Dimension(32, 32));
			setBorderPainted(false);
			setContentAreaFilled(false);
			setFocusPainted(false);
			setOpaque(false);

		}

	}

}
