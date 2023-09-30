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
import com.lawranta.globals.GLOBAL;
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
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;

public class GridSlider extends JDialog {
	private JTextField textField;
	private JPanel contentPane = new JPanel();
	static JPanel offsetSliders;
	ChangeListener offsetChange;
	int defaultOffsetX, defaultOffsetY, defaultGridSize;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GridSlider frame = new GridSlider();
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
	public GridSlider() {

		defaultOffsetX = GLOBAL.OFFSETX;
		defaultOffsetY = GLOBAL.OFFSETY;
		defaultGridSize = GLOBAL.GRIDHEIGHT;

		setUndecorated(true);
		setAlwaysOnTop(true);
		getContentPane().setSize(new Dimension(350, 200));
		getContentPane().setBackground(new Color(255, 255, 255));
		setPreferredSize(new Dimension(450, 250));
		setBounds(100, 100, 450, 250);
		setType(Type.UTILITY);
		setVisible(true);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setLocationRelativeTo(null);

		JPanel sliderPanel = new JPanel();
		sliderPanel.setBorder(new TitledBorder(null, "grid size", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sliderPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		getContentPane().add(sliderPanel);
		sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
		JSlider slider = new JSlider();
		slider.setValue(GLOBAL.GRIDWIDTH);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMinorTickSpacing(4);
		slider.setMinimum(0);
		slider.setMaximum(128);
		slider.setMajorTickSpacing(16);
		sliderPanel.add(slider);
		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub

				textField.setText(Integer.toString(slider.getValue()));

				if (Integer.parseInt(textField.getText()) < 1) {

					slider.setValue(1);
					textField.setText("1");

				}

				GLOBAL.GRIDWIDTH = Integer.parseInt(textField.getText());
				GLOBAL.GRIDHEIGHT = Integer.parseInt(textField.getText());
				SelectedTool.brushSize = Integer.parseInt(textField.getText());
				CanvasPanel.contentPanel.revalidate();
				CanvasPanel.contentPanel.repaint();

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

		offsetSliders = new JPanel();
		offsetSliders
				.setBorder(new TitledBorder(null, "x/y offset", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(offsetSliders);
		offsetSliders.setLayout(new GridLayout(1, 0, 0, 0));

		JSlider xOffset = new offsetSlider();
		xOffset.setBorder(new TitledBorder(null, "x-offset", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		xOffset.setValue(GLOBAL.OFFSETX);
		xOffset.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				System.out.println(xOffset.getValue());
				GLOBAL.OFFSETX = xOffset.getValue();
				CanvasPanel.contentPanel.revalidate();
				CanvasPanel.contentPanel.repaint();
			}

		});

		JSlider yOffset = new offsetSlider();
		yOffset.setBorder(new TitledBorder(null, "y-offset", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		yOffset.setValue(GLOBAL.OFFSETY);
		yOffset.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				System.out.println(yOffset.getValue());
				GLOBAL.OFFSETY = yOffset.getValue();
				CanvasPanel.contentPanel.revalidate();
				CanvasPanel.contentPanel.repaint();
			}

		});

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

					// CHECK FOR ZERO

					if (Integer.parseInt(textField.getText()) < 1) {

						slider.setValue(1);
						textField.setText("1");

					}

					GLOBAL.GRIDWIDTH = Integer.parseInt(textField.getText());
					GLOBAL.GRIDHEIGHT = Integer.parseInt(textField.getText());
					SelectedTool.brushSize = Integer.parseInt(textField.getText());
					CanvasPanel.contentPanel.revalidate();
					CanvasPanel.contentPanel.repaint();

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

					GLOBAL.OFFSETX = defaultOffsetX;
					GLOBAL.OFFSETY = defaultOffsetY;
					GLOBAL.GRIDHEIGHT = defaultGridSize;
					CanvasPanel.contentPanel.revalidate();
					CanvasPanel.contentPanel.repaint();
					dispose();
				}

			});
			buttonPanel.add(cancelButton);
		}

		revalidate();
		repaint();

	}

	class offsetSlider extends JSlider {

		/**
		 * 
		 */
		private static final long serialVersionUID = -6982540389421184065L;

		public offsetSlider() {

			setPaintTicks(true);
			setPaintLabels(true);
			setMinorTickSpacing(1);
			setMinimum(0);
			setMaximum(32);
			setMajorTickSpacing(4);
			GridSlider.offsetSliders.add(this);

		}

	}

}
