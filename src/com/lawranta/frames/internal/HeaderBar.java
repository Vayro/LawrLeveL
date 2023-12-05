package com.lawranta.frames.internal;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import com.lawranta.canvas.SelectedTool;
import com.lawranta.canvas.Zoom;
import com.lawranta.frames.internal.Toolbar.toolButton;
import com.lawranta.globals.GLOBAL;
import com.lawranta.globals.IntegerFilter;
import com.lawranta.panels.CanvasPanel;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.border.EtchedBorder;

public class HeaderBar extends JPanel {

	JPanel panel = this, brushSizePanel;
public static	JTextField  brushSizetextField;
	static ActionListener actionListeners;
	headerButton canvasSizeButton, gridSizeButton, zoomInButton, zoomOutButton, shiftButton, layerButton,
			brushSizeButton;
	public static JDialog j;

	/**
	 * Create the panel.
	 */
	public HeaderBar() {
		setPreferredSize(new Dimension(500, 48));
		setBackground(new Color(69, 69, 69));
		setFocusable(false);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		// setup action listener here
		actionListeners = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (j != null) {
					ShiftPaints.deSelectAll();
					j.dispose();
				}
				// TODO Auto-generated method stub

				switch (e.getActionCommand()) {

				case "Canvas":
					j = new CanvasSizer();

					break;

				case "Grid":
					j = new GridSlider();

					break;
				case "ZoomIn":
					Zoom.zoomIn();
					System.out.println("Zoom: " + Zoom.factor);

					CanvasPanel.revalidateAndRepaint();

					break;
				case "ZoomOut":
					Zoom.zoomOut();
					System.out.println("Zoom: " + Zoom.factor);

					CanvasPanel.revalidateAndRepaint();

					break;
				case "shift":
					setSelection();

					break;
				case "Layers":
					j = new LayersDialog();
					j.setLocation((int) (getLocationOnScreen().getX() + layerButton.getLocation().getX()),
							(int) (getLocationOnScreen().getY() + layerButton.getLocation().getY()));
					break;
				case "brushsize":
			
					j = new BrushSlider();
					j.dispose();
					j.setLocation((int) (getLocationOnScreen().getX() + brushSizePanel.getLocation().getX()),
							(int) (getLocationOnScreen().getY() + brushSizePanel.getLocation().getY()+brushSizePanel.getHeight()));
					j.setVisible(true);
					break;
				default:
					break;

				}

			}

		};

		JButton btnNewButton = new JButton("New button");
		// add(btnNewButton);

		layerButton = new headerButton("Layers");
		canvasSizeButton = new headerButton("Canvas");
		gridSizeButton = new headerButton("Grid");
		zoomInButton = new headerButton("ZoomIn");
		zoomOutButton = new headerButton("ZoomOut");
		shiftButton = new headerButton("shift");

		brushSizePanel = new JPanel();
		brushSizePanel.setForeground(new Color(0, 0, 0));
		brushSizePanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Brush size", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		brushSizePanel.setBackground(new Color(69, 69, 69));
		{
		    brushSizetextField = new JTextField();
			brushSizetextField.setSize(new Dimension(32, 20));
			brushSizetextField.setPreferredSize(new Dimension(32, 20));
			brushSizetextField.setMinimumSize(new Dimension(32, 20));
			brushSizetextField.setMaximumSize(new Dimension(32, 20));
			// brushSizetextField.setText(Integer.toString(slider.getValue()));
			brushSizetextField.setHorizontalAlignment(JTextField.CENTER);
			brushSizetextField.setText(Integer.toString(SelectedTool.brushSize));
			PlainDocument doc = (PlainDocument) brushSizetextField.getDocument();
			doc.setDocumentFilter(new IntegerFilter());

			// Listen for changes in the text
			brushSizetextField.getDocument().addDocumentListener(new DocumentListener() {
				public void changedUpdate(DocumentEvent e) {
					update();
					System.out.println(brushSizetextField.getText());
				}

				public void removeUpdate(DocumentEvent e) {
					update();
					System.out.println(brushSizetextField.getText());
				}

				public void insertUpdate(DocumentEvent e) {
					update();
					System.out.println(brushSizetextField.getText());
				}

				public void update() {
					if ((!brushSizetextField.getText().isEmpty())
							&& Integer.parseInt(brushSizetextField.getText()) > 0) {
						SelectedTool.brushSize = Integer.parseInt(brushSizetextField.getText());
					}
				}
			});

			brushSizePanel.add(brushSizetextField);

			JButton expandSizer = new JButton("<->");
			expandSizer.setPreferredSize(new Dimension(16, 16));
			expandSizer.setToolTipText("Slider");
			expandSizer.setActionCommand("brushsize");
			expandSizer.addActionListener(actionListeners);
			brushSizePanel.add(expandSizer);

		}

		panel.add(brushSizePanel);

	}

	public static void setSelection() {
		j = new ShiftPaints();
		j.setUndecorated(true);
		j.setVisible(true);
		j.setLocation(
				(int) (MouseInfo.getPointerInfo().getLocation().getX() - j.getLocationOnScreen().getX()
						+ j.getWidth() / 2),
				(int) (MouseInfo.getPointerInfo().getLocation().getY() - j.getLocationOnScreen().getY())
						+ j.getHeight() / 2);
		;
		CanvasPanel.contentPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		SelectedTool.setSelectionTool();
		CanvasPanel.revalidateAndRepaint();
	}

	public class headerButton extends JButton {

		/**
		 * 
		 */
		private static final long serialVersionUID = -2900847629316298000L;

		public headerButton(String s) {
			// TODO Auto-generated constructor stub
			setFont(GLOBAL.toolFont);
			setText(s);
			setPreferredSize(new Dimension(64, 32));
			setMinimumSize(new Dimension(64, 32));
			setMaximumSize(new Dimension(64, 32));
			setMargin(new Insets(0, 0, 0, 0));
			setActionCommand(s);
			addActionListener(actionListeners);

			panel.add(this);

		}

		public  void colorBorder(toolButton c) {

			c.setBorder(BorderFactory.createLineBorder(SelectedTool.selectedColor, 2, true));
		}

	}

}
