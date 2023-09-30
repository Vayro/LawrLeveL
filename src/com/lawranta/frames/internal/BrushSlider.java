package com.lawranta.frames.internal;
import java.awt.EventQueue;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.PlainDocument;

import com.lawranta.canvas.SelectedTool;
import com.lawranta.globals.IntegerFilter;
import com.lawranta.panels.CanvasPanel;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Color;

public class BrushSlider extends JDialog {
	private JTextField textField; private JPanel contentPane=new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrushSlider frame = new BrushSlider();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BrushSlider() {
		setMaximumSize(new Dimension(2147483647, 400));
		setUndecorated(true);
		setAlwaysOnTop(true);
		getContentPane().setSize(new Dimension(350, 100));
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 450, 150);
		setType(Type.UTILITY);
		setVisible(true);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setLocationRelativeTo(null);
		
		JPanel sliderPanel = new JPanel();
		sliderPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		getContentPane().add(sliderPanel);
		sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
		JSlider slider = new JSlider();
		slider.setValue(32);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMinorTickSpacing(16);
		slider.setMinimum(4);
		slider.setMaximum(1024);
		slider.setMajorTickSpacing(128);
		sliderPanel.add(slider);
		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				
				textField.setText(Integer.toString(slider.getValue()));
				
				
			}
			
			
			
		});
		
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(32767, 200));
		sliderPanel.add(panel);
		
		textField = new JTextField();
		textField.setSize(new Dimension(64, 20));
		textField.setPreferredSize(new Dimension(64, 20));
		textField.setMinimumSize(new Dimension(64, 20));
		textField.setText(Integer.toString(slider.getValue()));
		textField.setHorizontalAlignment(JTextField.CENTER);
		
		
		 PlainDocument doc = (PlainDocument) textField.getDocument();
	      doc.setDocumentFilter(new IntegerFilter());
		
		
		
		

		
		
		
		
		panel.add(textField);
		
		
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		getContentPane().add(buttonPanel);
		{
			JButton okButton = new JButton("OK");
			okButton.setActionCommand("OK");
			buttonPanel.add(okButton);
			okButton.addActionListener(new ActionListener() {

				/**
				 * @param e
				 */
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					 SelectedTool.brushSize=Integer.parseInt(textField.getText());
					 
			
						SelectedTool.setEraserTool();
						System.out.println("Selected Tool: " + SelectedTool.selectedTool);
						CanvasPanel.contentPanel.setCursor(Toolbar.eraserCursor);
					 
					 
					 
					 
					 
					 
					dispose();
					
				}
				
				
				
				
				
				
				
				
			});
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
			buttonPanel.add(cancelButton);
		}
		
		
		
		revalidate();
		repaint();
		
		
		
		
		
		
		
		

	}

}
