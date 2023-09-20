package com.lawranta.frames.internal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lawranta.canvas.Tool;
import com.lawranta.frames.internal.Toolbar.toolButton;

import java.awt.Color;
import javax.swing.border.TitledBorder;

public class ColorChooser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	ActionListener actionListener;

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
		getContentPane().setBackground(new Color(255, 255, 255));
		((JComponent) getContentPane()).setBorder(new TitledBorder(null, "Pick Color", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		setUndecorated(true);
		setAlwaysOnTop(true);
		setLocation((int) MouseInfo.getPointerInfo().getLocation().getX(),(int) MouseInfo.getPointerInfo().getLocation().getY());
		setVisible(true);
		setMinimumSize(new Dimension(700, 420));
		setPreferredSize(new Dimension(700, 420));
		getContentPane().setLayout(new BorderLayout());
		getContentPane().setMinimumSize(new Dimension(520, 420));
		contentPanel.setBackground(new Color(42, 42, 42));
		contentPanel.setLayout(new FlowLayout());
		
getContentPane().add(contentPanel);
		setModal(true);

		JColorChooser j = new JColorChooser();
		j.setBackground(new Color(78, 78, 78));
		contentPanel.add(j);

		
		
		
		actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switch (e.getActionCommand()){
				case "OK":
					Tool.selectedColor=j.getColor();
					toolButton.colorBorder(Toolbar.colorButton);
					dispose();
	
					break;
					
				case "Cancel":
					dispose();
					break;
				}
				
				
				

			}
		};
		
		
		
		
		
		
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
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
	}

}
