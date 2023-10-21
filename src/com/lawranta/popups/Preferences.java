package com.lawranta.popups;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import com.lawranta.frames.internal.JGradientButton;

import com.lawranta.globals.GLOBAL;
import com.lawranta.initializer.PropertiesCFG;
import com.lawranta.panels.CanvasPanel;

public class Preferences extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1012521979584272496L;
	private final JPanel contentPanel = new JPanel();
	private ActionListener action;
	JGradientButton gridColorButton, gridBGButton, bgColorButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Preferences dialog = new Preferences();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Preferences() {

		setTitle("Preferences");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setModal(true);
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		contentPanel.setLayout(new GridLayout(3, 1, 0, 0));

		gridColorButton = new JGradientButton("Grid Color", GLOBAL.gridColor);
		gridColorButton.setActionCommand("gridColor");

		contentPanel.add(gridColorButton);

		gridBGButton = new JGradientButton("Content Default Color", GLOBAL.gridBGColor);
		gridBGButton.setActionCommand("gridBGColor");

		contentPanel.add(gridBGButton);

		bgColorButton = new JGradientButton("Canvas Color", GLOBAL.bgColor);
		bgColorButton.setActionCommand("bgColor");

		contentPanel.add(bgColorButton);

		action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getActionCommand());
				switch (e.getActionCommand()) {

				case "Close":
					dispose();
					break;
				case "gridColor": {

					GLOBAL.CC.setLocationRelativeTo(null);
					GLOBAL.CC.setVisible(true);

					if (GLOBAL.CC.cancelled == false) {

						System.out.println("color selected");
						GLOBAL.gridColor = GLOBAL.CC.j.getColor();
						gridColorButton.setColor(GLOBAL.CC.j.getColor());
						String hex = String.format("%02x%02x%02x", GLOBAL.gridColor.getRed(),
								GLOBAL.gridColor.getGreen(), GLOBAL.gridColor.getBlue());
						gridColorButton.setText(hex);
						CanvasPanel.revalidateAndRepaint();
						try {
							PropertiesCFG.persistSetting("gridColor", hex);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

					break;
				}
				case "bgColor": {
					GLOBAL.CC.setLocationRelativeTo(null);
					GLOBAL.CC.setVisible(true);
					if (GLOBAL.CC.cancelled == false) {
						GLOBAL.bgColor = GLOBAL.CC.j.getColor();
						bgColorButton.setColor(GLOBAL.CC.j.getColor());
						String hex = String.format("%02x%02x%02x", GLOBAL.bgColor.getRed(), GLOBAL.bgColor.getGreen(),
								GLOBAL.bgColor.getBlue());
						bgColorButton.setText(hex);
						CanvasPanel.revalidateAndRepaint();
						try {
							PropertiesCFG.persistSetting("bgColor", hex);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
					break;
				
				
			case "gridBGColor": {
				GLOBAL.CC.setLocationRelativeTo(null);
				GLOBAL.CC.setVisible(true);
				if (GLOBAL.CC.cancelled == false) {
					GLOBAL.gridBGColor = GLOBAL.CC.j.getColor();
					gridBGButton.setColor(GLOBAL.CC.j.getColor());
					String hex = String.format("%02x%02x%02x", GLOBAL.gridBGColor.getRed(), GLOBAL.gridBGColor.getGreen(),
							GLOBAL.gridBGColor.getBlue());
					gridBGButton.setText(hex);
					CanvasPanel.revalidateAndRepaint();
					try {
						PropertiesCFG.persistSetting("gridBGColor", hex);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
				break;
			}
			
				

			}

		};

		gridColorButton.addActionListener(action);
		gridBGButton.addActionListener(action);
		bgColorButton.addActionListener(action);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Close");
				okButton.setActionCommand("Close");
				okButton.addActionListener(action);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}

		}

	}

}
