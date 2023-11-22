package com.lawranta.frames.internal;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.PointerInfo;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.text.PlainDocument;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import com.lawranta.canvas.Paint;
import com.lawranta.frames.MainFrame;
import com.lawranta.globals.GLOBAL;
import com.lawranta.globals.IntegerFilter;
import com.lawranta.panels.CanvasPanel;

public class ShiftPaints extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9055024543327250443L;
	private final JPanel contentPanel;
	private JTextField shiftSizeField;
	private ActionListener actionListener;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ShiftPaints dialog = new ShiftPaints();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ShiftPaints() {
		setUndecorated(true);
		setBounds(100, 100, 250, 225);
		Container gCP = getContentPane();
		getContentPane().setLayout(new BorderLayout(0, 0));
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setAlwaysOnTop(true);
		setAutoRequestFocus(false);
		selectAll();
		SwingUtilities.invokeLater(refresh());
		JPanel mainBodyPanel = new JPanel();
		gCP.add(mainBodyPanel, BorderLayout.CENTER);

		JPanel topPanel = new JPanel();
		getContentPane().add(topPanel, BorderLayout.NORTH);
		{
			Icon icon = new ImageIcon(MainFrame.class.getResource(GLOBAL.moveIconPath));
			topPanel.setLayout(new BorderLayout(0, 0));
			JButton moveButton = new JButton(icon);
			moveButton.setFocusable(false);
			moveButton.setHorizontalAlignment(SwingConstants.RIGHT);
			topPanel.add(moveButton, BorderLayout.EAST);
			moveButton.addMouseMotionListener(new MouseMotionListener() {

				@Override
				public void mouseDragged(MouseEvent e) {
					// TODO Auto-generated method stub
					System.out.println("moving");

					int x = (int)(MouseInfo.getPointerInfo().getLocation().getX() - moveButton.getLocation().getX()-(moveButton.getWidth()/2));
					int y = (int)(MouseInfo.getPointerInfo().getLocation().getY() - moveButton.getLocation().getY()-(moveButton.getHeight()/2));

					setLocation(new Point(x, y));
				}

				@Override
				public void mouseMoved(MouseEvent e) {
					// TODO Auto-generated method stub

				}
			});

		}

		mainBodyPanel.setLayout(new BorderLayout(0, 0));
		mainBodyPanel.add(contentPanel, BorderLayout.NORTH);
		actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				shift(e.getActionCommand(), Integer.parseInt(shiftSizeField.getText()));
			}

		};

		contentPanel.setLayout(new GridLayout(0, 3, 0, 0));
		{
			JButton btnNewButton = new JButton("");
			btnNewButton.setEnabled(false);
			contentPanel.add(btnNewButton);
		}
		{
			JButton upButton = new JButton("Up");
			upButton.setActionCommand("up");
			upButton.addActionListener(actionListener);
			contentPanel.add(upButton);
		}
		{
			JButton btnNewButton_2 = new JButton("");
			btnNewButton_2.setEnabled(false);
			contentPanel.add(btnNewButton_2);
		}
		{
			JButton leftButton = new JButton("Left");
			leftButton.setActionCommand("left");
			leftButton.addActionListener(actionListener);
			contentPanel.add(leftButton);
		}
		{
			JButton btnNewButton_4 = new JButton("");
			btnNewButton_4.setEnabled(false);
			contentPanel.add(btnNewButton_4);
		}
		{
			JButton rightButton = new JButton("Right");
			rightButton.setActionCommand("right");
			rightButton.addActionListener(actionListener);
			contentPanel.add(rightButton);
		}
		{
			JButton btnNewButton_6 = new JButton("");
			btnNewButton_6.setEnabled(false);
			contentPanel.add(btnNewButton_6);
		}
		{
			JButton downButton = new JButton("Down");
			downButton.setActionCommand("down");
			downButton.addActionListener(actionListener);
			contentPanel.add(downButton);
		}
		{
			JButton btnNewButton_8 = new JButton("");
			btnNewButton_8.setEnabled(false);
			contentPanel.add(btnNewButton_8);
		}

		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Shift size:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			mainBodyPanel.add(panel, BorderLayout.CENTER);
			{
				shiftSizeField = new JTextField();
				panel.add(shiftSizeField);
				shiftSizeField.setColumns(10);
				shiftSizeField.setText(Integer.toString(GLOBAL.GRIDHEIGHT));
				{
					JPanel moreButtons = new JPanel();
					mainBodyPanel.add(moreButtons, BorderLayout.SOUTH);
					{
						JButton selectAllButton = new JButton("Select All");
						selectAllButton.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								selectAll();
							}
							
						});
						moreButtons.add(selectAllButton);
					}
					{
						JButton clearAllButton = new JButton("Clear All");
						clearAllButton.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								deSelectAll();
							}
							
						});
						moreButtons.add(clearAllButton);
					}
				}
				PlainDocument doc = (PlainDocument) shiftSizeField.getDocument();
				doc.setDocumentFilter(new IntegerFilter());

			}
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			gCP.add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("close");
				okButton.setActionCommand("close");
				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if (e.getActionCommand() == "close") {
							deSelectAll();
							dispose();
						}
					}

				});
				getRootPane().setDefaultButton(okButton);
			}

		}

	}

	public static void deSelectAll() {
		// TODO Auto-generated method stub
		for (int i = 0; i < CanvasPanel.canvasContainer.size(); i++) {
			Paint p = CanvasPanel.canvasContainer.get(i);
			p.setSelected(false);
			p.draw();
		}

	}

	private void selectAll() {
		// TODO Auto-generated method stub
		for (int i = 0; i < CanvasPanel.canvasContainer.size(); i++) {
			Paint p = CanvasPanel.canvasContainer.get(i);
			p.setSelected(true);
			p.draw();

		}

	}

	void shift(String direction, int shiftSize) {
		for (int i = 0; i < CanvasPanel.canvasContainer.size(); i++) {
			Paint p = CanvasPanel.canvasContainer.get(i);

			if (p.isSelected() == true) {
				switch (direction) {
				case "up":
					p.setY(p.getY() - shiftSize);
					p.draw();
					break;
				case "down":
					p.setY(p.getY() + shiftSize);
					p.draw();
					break;

				case "left":
					p.setX(p.getX() - shiftSize);
					p.draw();
					break;

				case "right":
					p.setX(p.getX() + shiftSize);
					p.draw();
					break;

				}
			}

		}
	}

	private static Runnable refresh() {
		return new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				CanvasPanel.revalidateAndRepaint();
			}

		};

	}

}
