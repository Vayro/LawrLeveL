package com.lawranta.file;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lawranta.canvas.Zoom;
import com.lawranta.globals.GLOBAL;
import com.lawranta.panels.CanvasPanel;

import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ExportPreDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ExportPreDialog dialog = new ExportPreDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ExportPreDialog() {
		Zoom.zoomDefault();
		setModal(true);
		setBounds(100, 100, 450, 200);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setIconImage(GLOBAL.FAVICO);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		{
			JTextField txtrExportingAsPng = new JTextField();
			txtrExportingAsPng.setBorder(null);
			txtrExportingAsPng.setHorizontalAlignment(SwingConstants.CENTER);
			txtrExportingAsPng.setEditable(false);
			txtrExportingAsPng.setBackground(new Color(240, 240, 240));
			txtrExportingAsPng.setText("Exporting as PNG");
			contentPanel.add(txtrExportingAsPng);
			
			
			
		}
		
		
		
		JPanel checkBoxPane = new JPanel();
		contentPanel.add(checkBoxPane);

		JCheckBox hideGridCheckBox = new JCheckBox("Hide Grid");

		checkBoxPane.add(hideGridCheckBox);
		if (CanvasPanel.hideGrid) {
			hideGridCheckBox.setSelected(true);
		}
		hideGridCheckBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CanvasPanel.hideGrid = !CanvasPanel.hideGrid;
				CanvasPanel.revalidateAndRepaint();
			}

		});

		
		
		
		
		
		
		
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Export");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Export.export();
						dispose();
					}});
					
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						dispose();
					}
					
					
					
				});
				buttonPane.add(cancelButton);
			}
		}
	}

}
