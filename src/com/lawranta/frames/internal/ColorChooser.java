package com.lawranta.frames.internal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.lawranta.canvas.SelectedTool;
import com.lawranta.frames.internal.Toolbar.toolButton;

import java.awt.Color;
import javax.swing.border.TitledBorder;

public class ColorChooser extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	ActionListener actionListener;
	public boolean cancelled;
	public JColorChooser j;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ColorChooser dialog = new ColorChooser();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ColorChooser() {
		setUndecorated(true);
		getContentPane().setBackground(new Color(255, 255, 255));
		((JComponent) getContentPane())
				.setBorder(new TitledBorder(null, "Pick Color", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setAlwaysOnTop(true);

		setMinimumSize(new Dimension(700, 420));
		setPreferredSize(new Dimension(700, 420));
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setMinimumSize(new Dimension(520, 420));
		contentPanel.setBackground(new Color(42, 42, 42));
		contentPanel.setLayout(new FlowLayout());

		getContentPane().add(contentPanel);
		setModal(true);

		j = new JColorChooser();
		j.setBackground(new Color(78, 78, 78));
		contentPanel.add(j);

		actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switch (e.getActionCommand()) {
				case "OK":

					cancelled = false;
		
					break;

				case "Cancel":
					cancelled = true;
					dispose();
					break;
				}

			}
		};

		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(0, 0, 0));
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(actionListener);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(actionListener);
				buttonPane.add(cancelButton);
			}

		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(0, 0, 0));
			getContentPane().add(buttonPane, BorderLayout.NORTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton okButton = new JButton("✓");
				okButton.setActionCommand("OK");
				okButton.setFocusable(false);
				okButton.addActionListener(actionListener);

				buttonPane.add(okButton);
			}
			{
				JButton btnX = new JButton("X");
				btnX.setActionCommand("Cancel");
				btnX.setFocusable(false);
				btnX.addActionListener(actionListener);
				buttonPane.add(btnX);
			}

			{
				Icon icon = new ImageIcon("src/images/move.png");
				JButton moveButton = new JButton(icon);
				moveButton.setFocusable(false);
				buttonPane.add(moveButton);

				moveButton.addMouseMotionListener(new MouseMotionListener() {

					@Override
					public void mouseDragged(MouseEvent e) {
						// TODO Auto-generated method stub
						System.out.println("moving");

						int x = (int) (MouseInfo.getPointerInfo().getLocation().getX() - moveButton.getLocation().getX()
								- (moveButton.getWidth() / 2));
						int y = (int) (MouseInfo.getPointerInfo().getLocation().getY() - moveButton.getLocation().getY()
								- (moveButton.getHeight() / 2));

						setLocation(new Point(x, y));
					}

					@Override
					public void mouseMoved(MouseEvent e) {
						// TODO Auto-generated method stub

					}
				});

			}

		}
	}

}
