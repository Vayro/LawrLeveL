package com.lawranta.frames.internal;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import com.lawranta.canvas.SelectedTool;
import com.lawranta.frames.internal.Toolbar.toolButton;
import com.lawranta.globals.GLOBAL;
import com.lawranta.panels.CanvasPanel;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Window.Type;

public class CanvasSizer extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField widthField;
	private JTextField heightField;

	/**
	 * Launch the application
	 * .
	 */
	public static void main(String[] args) {
		try {
			CanvasSizer dialog = new CanvasSizer();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
			dialog.setPreferredSize(new Dimension(350, 100));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CanvasSizer() {
		setUndecorated(true);
		setType(Type.UTILITY);
		setVisible(true);
		
	//	setContentPane(this);
		setModal(true);

		setMinimumSize(new Dimension(350, 100));
		setPreferredSize(new Dimension(350, 100));
		getContentPane().setSize(getPreferredSize());
		//this.setUndecorated(true);
		setAlwaysOnTop(true);
		setLocation((int) MouseInfo.getPointerInfo().getLocation().getX(),
				(int) MouseInfo.getPointerInfo().getLocation().getY());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	//	getContentPane().add(contentPanel);

		// setContentPane(contentPanel);
		ActionListener a = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switch (e.getActionCommand()) {
				case "OK":
					GLOBAL.CANVAS_WIDTH=Integer.parseInt(widthField.getText());
					GLOBAL.CANVAS_HEIGHT=Integer.parseInt(heightField.getText());
					CanvasPanel.setCanvasSize();
					dispose();

					break;

				case "Cancel":
					dispose();
					break;
				}

			}
		};

		JPanel textFieldPanel = new JPanel();
		getContentPane().add(textFieldPanel, BorderLayout.NORTH);

		textFieldPanel.add(contentPanel, BorderLayout.CENTER);
		{
			widthField = new JTextField(Integer.toString(GLOBAL.CANVAS_WIDTH));
			widthField.setBorder(new TitledBorder(
					new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Width",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(widthField);
			widthField.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("x");
			contentPanel.add(lblNewLabel);
		}
		{
			heightField = new JTextField(Integer.toString(GLOBAL.CANVAS_HEIGHT));
			heightField.setBorder(new TitledBorder(null, "Height", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(heightField);
			heightField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(a);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(a);
				buttonPane.add(cancelButton);
			}
		}

	}

}
