package com.lawranta.frames.internal;

import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import com.lawranta.canvas.SelectedTool;
import com.lawranta.canvas.Zoom;
import com.lawranta.frames.MainFrame;
import com.lawranta.globals.GLOBAL;

import com.lawranta.panels.CanvasPanel;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Dimension;

public class Toolbar extends JPanel {

	public static ActionListener actionListener;
	public static JDialog j;
	public static toolButton colorButton;
	static toolButton eyeDropperButton;
	static toolButton textToolButton;
	static toolButton canvasSizeButton;
	static toolButton gridSizeButton;
	static toolButton eraserButton;
	static toolButton layerButton;
	static toolButton zoomInButton;
	static toolButton zoomOutButton;
	static toolButton shiftButton;
	static toolButton trueBrushButton;
	static Image circle;
	static Cursor eraserCursor; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Toolbar frame = new Toolbar();
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
	@SuppressWarnings("unused")
	public Toolbar() {

		/*
		 * setFrameIcon(null); setResizable(true); try { setMaximum(true); } catch
		 * (PropertyVetoException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } setBorder(null); try { setIcon(true); } catch
		 * (PropertyVetoException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		 * setIconifiable(true); setMaximizable(true);
		 */
		setBackground(new Color(69, 69, 69));
		setFocusable(false);
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getButton() == 2)
					System.out.println("Selected Tool: " + SelectedTool.selectedTool);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				GLOBAL.MAINFRAME.requestFocus();
			}

		});

		actionListener = new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {

				if (j != null) {
					ShiftPaints.deSelectAll();
					j.dispose();
				}
				// TODO Auto-generated method stub

				switch (e.getActionCommand()) {
				case "InkDrop":
					setInkDrop();
					break;

				case "Aa":
					setText();
					break;

				case "Color":

					j = GLOBAL.CC;

					// j.setLocation(colorButton.getLocationOnScreen());
					j.setLocationRelativeTo(null);
					j.setVisible(true);

					if (GLOBAL.CC.cancelled == false) {
						SelectedTool.selectedColor = GLOBAL.CC.j.getColor();
						toolButton.colorBorder(Toolbar.colorButton);
					}

					break;

				case "Canvas":
					j = new CanvasSizer();

					break;

				case "Eraser":
					setEraser();

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
					j=new LayersDialog();
					j.setLocation((int) (getLocationOnScreen().getX()+layerButton.getLocation().getX()+layerButton.getWidth()),(int) (getLocationOnScreen().getY()+layerButton.getLocation().getY()));
					break;
				case "Eyedropper":
					setEyeDropper();
				break;
				case "tBrush":
					setBrush();
				break;
				default:
					break;

				}

			}


		};

		KeyListener keyListener = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getKeyCode()) {
				case 69:
					System.out.println("e pressed");
					break;
				case 0:
					break;

				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println("released");
			}

		};
		addKeyListener(keyListener);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		toolButton inkDropToolButton = new toolButton("InkDrop", panel);
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getButton());

				if (e.getButton() == 3) {

					j = new BrushSlider();

				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		trueBrushButton = new toolButton("tBrush", panel);
		
		textToolButton = new toolButton("Aa", panel);

		canvasSizeButton = new toolButton("Canvas", panel);

		gridSizeButton = new toolButton("Grid", panel);

		eraserButton = new toolButton("Eraser", panel);
		eraserButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getButton());

				if (e.getButton() == 3) {

					j = new BrushSlider();

				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		zoomInButton = new toolButton("ZoomIn", panel);
		zoomOutButton = new toolButton("ZoomOut", panel);

		shiftButton = new toolButton("shift", panel);
		
		layerButton = new toolButton("Layers", panel);
		eyeDropperButton = new toolButton("Eyedropper", panel);
		colorButton = new toolButton("Color", panel);
		toolButton.colorBorder(colorButton);

		setBounds(100, 100, 450, 300);

	}
	

	private void setBrush() {
		// TODO Auto-generated method stub
	//	circle = makeImage(32, 32);
	//	eraserCursor = Toolkit.getDefaultToolkit().createCustomCursor(circle, new Point(0, 0), "eraser");
		SelectedTool.setBrushTool();
		System.out.println("Selected Tool: " + SelectedTool.selectedTool);
		CanvasPanel.contentPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

	//	CanvasPanel.contentPanel.setCursor(eraserCursor);

		
	}

	protected void setEyeDropper() {
		// TODO Auto-generated method stub
		SelectedTool.setEyeDropperTool();
		System.out.println("Selected Tool: " + SelectedTool.selectedTool);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image =toolkit.getImage( MainFrame.class.getResource(GLOBAL.eyeDropperPath));
		Cursor c = toolkit.createCustomCursor(image , new Point(0, 0), "eyeDropper");
		CanvasPanel.contentPanel.setCursor(c);
		
	}

	public static void setInkDrop() {

		SelectedTool.setInkDropTool();
		System.out.println("Selected Tool: " + SelectedTool.selectedTool);
		CanvasPanel.contentPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

	}

	public static void setText() {

		SelectedTool.setTextTool();
		System.out.println("Selected Tool: " + SelectedTool.selectedTool);
		CanvasPanel.contentPanel.setCursor(new Cursor(Cursor.TEXT_CURSOR));

	}

	public static void setEraser() {
		circle = makeImage(32, 32);
		eraserCursor = Toolkit.getDefaultToolkit().createCustomCursor(circle, new Point(0, 0), "eraser");
		SelectedTool.setEraserTool();
		System.out.println("Selected Tool: " + SelectedTool.selectedTool);
		CanvasPanel.contentPanel.setCursor(eraserCursor);

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

	static public BufferedImage makeImage(int width, int height) {
		// BufferedImage is actually already transparent on my system, but that isn't
		// guaranteed across platforms.

		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = bufferedImage.createGraphics();

		// To be sure, we use clearRect, which will (unlike fillRect) totally replace
		// the current pixels with the desired color, even if it's fully transparent.
	/*	graphics.setBackground(new Color(0, true));
		graphics.clearRect(0, 0, width, height);
		graphics.setPaint(new Color(255, 255, 255, 0));
		graphics.drawOval(1, 1, width - 2, height - 2);
		graphics.setPaint(new Color(0, 0, 0, 0));
		graphics.drawOval(2, 2, width - 4, height - 4);*/
		graphics.setPaint(new Color(0, 0, 0, 255));
		graphics.fillOval(12, 12, 8, 8);
		
		graphics.dispose();

		return bufferedImage;
	}

	public class toolButton extends JButton {

		/**
		 * 
		 */
		private static final long serialVersionUID = -2900847629316298000L;

		public toolButton(String s, JPanel p) {
			// TODO Auto-generated constructor stub
			setFont(GLOBAL.toolFont);
			setText(s);
			setPreferredSize(new Dimension(64, 32));
			setMinimumSize(new Dimension(64, 32));
			setMaximumSize(new Dimension(64, 32));
			setMargin(new Insets(0, 0, 0, 0));
			setActionCommand(s);
			addActionListener(Toolbar.actionListener);

			p.add(this);

		}

		public static void colorBorder(toolButton c) {

			c.setBorder(BorderFactory.createLineBorder(SelectedTool.selectedColor, 2, true));
		}

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3704138041001210203L;

}