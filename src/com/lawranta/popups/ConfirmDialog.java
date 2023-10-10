package com.lawranta.popups;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dialog;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.lawranta.globals.GLOBAL;

import javax.swing.JTextField;
import javax.swing.JTextArea;

public class ConfirmDialog extends JDialog {
ActionEvent a;
	
	private final JPanel contentPanel = new JPanel();
	private JTextArea textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConfirmDialog dialog = new ConfirmDialog("");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param string 
	 */
	public  ConfirmDialog(String string) {
		setIconImage(GLOBAL.FAVICO);
		setUndecorated(true);
		setSize(new Dimension(300,100));
		setPreferredSize(new Dimension(300,100));
		setMinimumSize(new Dimension(300,100));

		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				a = e ;
				dispose();
		
				

			}
		};

		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			textField = new JTextArea();
			textField.setPreferredSize(new Dimension(400, 36));
			textField.setEditable(false);
			
			textField.setColumns(10);
			textField.setText(string);
			
			
			
			JScrollPane scroll= new JScrollPane();
			scroll.setViewportView(textField);
			textField.setCaretPosition(0) ;
			contentPanel.add(scroll);
			
			
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(action);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(action);
			}
		}
	}

	/**
	 * @return the a
	 */
	public ActionEvent getA() {
		return a;
	}

	/**
	 * @param a the a to set
	 */
	public void setA(ActionEvent a) {
		this.a = a;
	}

}
