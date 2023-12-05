package com.lawranta.frames.internal;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JDialog;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.PlainDocument;

import com.lawranta.canvas.SelectedTool;
import com.lawranta.globals.IntegerFilter;
import com.lawranta.panels.CanvasPanel;

import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Color;

public class BrushSlider extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5757691635233262953L;
	private JTextField textField;
	private JPanel contentPane = new JPanel();
	private JSlider js = new JSlider();

	/**
	 * Create the frame.
	 */
	public BrushSlider() {
		setUndecorated(true);
		setBounds(0, 0, 350, 64);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

		contentPane.setPreferredSize(new Dimension(350, 64));

		js.setMaximum(512);
		js.setMajorTickSpacing(64);
		js.setMinorTickSpacing(8);
		js.setMinimum(0);
		js.setPaintTicks(true);
		js.setPaintLabels(true);
		js.setValue(SelectedTool.brushSize);

		js.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if (js.getValue() < 1) {
					js.setValue(1);

				}

				SelectedTool.brushSize = js.getValue();
				HeaderBar.brushSizetextField.setText(Integer.toString(js.getValue()));

			}

		});

		contentPane.add(js);

		getContentPane().add(contentPane);
		
		
		
		
		
		
		addWindowFocusListener( new WindowFocusListener() {
	        int c=0;
	        @Override
	        public void windowLostFocus(WindowEvent e) {
	         dispose();


	        }

	        @Override
	        public void windowGainedFocus(WindowEvent e) {
	            //System.out.println(c);
	            // TODO Auto-generated method stub

	        }
	    });
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
