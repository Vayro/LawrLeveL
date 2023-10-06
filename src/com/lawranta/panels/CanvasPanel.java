package com.lawranta.panels;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.lawranta.canvas.CreateGrid;
import com.lawranta.canvas.InkDrop;
import com.lawranta.canvas.Paint;
import com.lawranta.canvas.TextNode;
import com.lawranta.canvas.Zoom;
import com.lawranta.canvas.SelectedTool;
import com.lawranta.frames.MainFrame;
import com.lawranta.frames.internal.Toolbar;
import com.lawranta.globals.GLOBAL;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.Timer;

public class CanvasPanel extends JPanel {

	public static int canvasWidthDefault = 1024;
	public static int canvasHeightDefault = 768;
	public static int canvasWidth = canvasWidthDefault;
	public static int canvasHeight = canvasHeightDefault;
	public static boolean hideGrid = false;
	double mouse_x, mouse_y;
	int px;
	int py;
	static CreateGrid cg;
	public static JLayeredPane contentPanel;
	public static JPanel gridPane;
	public static int mouse = 0;
	public static ArrayList<Paint> canvasContainer = new ArrayList<Paint>();
	private static final long serialVersionUID = 0L;
	private static final int TIMER_DELAY = 35;
	private static final int MOUSE_TIMER = 35;
	static CanvasPanel getCP;
	private static double DIAMETER = SelectedTool.brushSize;
	private static final Color XOR_COLOR = Color.yellow;
	Graphics2D g2 = (Graphics2D) getGraphics();
	Point p;
	MouseEvent e;
	private static Shape m_circle = null;


	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D) g;
	    if(Zoom.zooming==true){    
	    	AffineTransform at = new AffineTransform();
	        at.scale(Zoom.factor,Zoom.factor);
	        Zoom.zooming=false;
	        g2.transform(at);
	    }}
	
	
	public CanvasPanel() {
		setBackground(new Color(23, 2, 64));
		SelectedTool.setToolDefault();
		getCP = this;

		contentPanel = new JLayeredPane();

		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setOpaque(true);
		contentPanel.setSize(new Dimension(canvasWidthDefault, canvasHeightDefault));
		contentPanel.setMinimumSize(new Dimension(canvasWidthDefault, canvasHeightDefault));
		contentPanel.setPreferredSize(new Dimension(canvasWidthDefault, canvasHeightDefault));
		gridPane = new GridPanel();

		add(contentPanel);

		contentPanel.add(gridPane, 3, 0);

		contentPanel.setLayout(null);
		contentPanel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		g2 = (Graphics2D) contentPanel.getGraphics();
		contentPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

				if (e.getButton() == 1) {
					if (SelectedTool.selectedTool == 2) {
						newTextNode();

					}

				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				mouse = e.getButton();
				System.out.println("Mouse button: " + mouse);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				mouse = 0;
				// clearCircle(g2);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

				clearCircle();
				revalidateAndRepaint();
			}

		});

		contentPanel.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				if (SelectedTool.selectedTool == 3) {
					event();
					clearCircle();
				}
				;
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

				mousePosUpdate();

				if (SelectedTool.selectedTool == 3) {
					event();

					clearCircle();
				}
				;
			}

		});

		// the timer variable must be a javax.swing.Timer
		// TIMER_DELAY is a constant int and = 35;
		new javax.swing.Timer(TIMER_DELAY, new ActionListener() {
			public void actionPerformed(ActionEvent tick) {
				/*
				 * contentPanel.revalidate(); contentPanel.repaint();
				 */

				switch (mouse) {
				case 1: {
					chooseStartActionByTool();
					break;
				}
				case 2: {

					System.out.println("Selected Tool: " + SelectedTool.selectedTool);
					break;
				}
				case 3: {
					chooseStopActionByTool();
					break;

				}

				}

				// System.out.print("h");

			}
		}).start();

		new javax.swing.Timer(MOUSE_TIMER, new ActionListener() {
			public void actionPerformed(ActionEvent tick) {
				/*
				 * contentPanel.revalidate(); contentPanel.repaint();
				 */
				if (SelectedTool.selectedTool == 3)
					event();
			}
		}).start();

	}

	protected void mousePosUpdate() {
		// TODO Auto-generated method stub
		Point p = MouseInfo.getPointerInfo().getLocation();
		Point p2 = contentPanel.getLocationOnScreen();
		mouse_x = (p.getX() - p2.getX());
		;
		mouse_y = (int) (p.getY() - p2.getY());
		GLOBAL.MAINFRAME
				.setTitle("LawrLeveler (" + GLOBAL.fileInfo.getFileName() + ")" + " | " + mouse_x + "," + mouse_y);
	}

	private void clearCircle() {
		if (m_circle != null) {
			g2.setXORMode(XOR_COLOR);
			g2.draw(m_circle);
			g2.setPaintMode();

			m_circle = null;
			revalidateAndRepaint();
		}

	}

	public static void setCanvasSize() {
		int sizeX=(int) (GLOBAL.CANVAS_HEIGHT*Zoom.factor);
		int sizeY=(int) (GLOBAL.CANVAS_HEIGHT*Zoom.factor);
		contentPanel.setSize(new Dimension(sizeX, sizeY));
		contentPanel.setMinimumSize(new Dimension(sizeX, sizeY));
		contentPanel.setPreferredSize(new Dimension(sizeX, sizeY));
		MainFrame.scrollPane.setViewportView(null);
		getCP.setSize(new Dimension(sizeX, sizeY));
		System.out.println("setting canvas size");
		MainFrame.scrollPane.setViewportView(getCP);
		rebuildGrid();

		revalidateAndRepaint();

	}

	public static void rebuildGrid() {
		gridPane = new GridPanel();
		contentPanel.add(gridPane, 3, 0);

	}

	void chooseStartActionByTool() {

		switch (SelectedTool.selectedTool) {
		case 1: {
			startPainting();
			break;
		}
		case 2: {

			break;
		}
		case 3: { // eraser tool
			startErasing();
			break;

		}
		}
	}

	private void newTextNode() {

		Point p = MouseInfo.getPointerInfo().getLocation();
		Point p2 = contentPanel.getLocationOnScreen();
		int x = (int) (p.getX() - p2.getX()), y = (int) (p.getY() - p2.getY());

		TextNode node = new TextNode(x, y);
		node.setVisible(true);
		node.requestFocusInWindow();
		contentPanel.add(node, 2, 0);
		canvasContainer.add(node);
		revalidateAndRepaint();

	}

	void chooseStopActionByTool() {

		switch (SelectedTool.selectedTool) {
		case 1: {
			// startDeleting();
			break;
		}
		case 2: {

			break;
		}
		case 3: { // eraser tool

			break;

		}
		}

	}

	private static void startPainting() {
		// TODO Auto-generated method stub
		Point p = MouseInfo.getPointerInfo().getLocation();
		Point p2 = contentPanel.getLocationOnScreen();
		int x = (int) (p.getX() - p2.getX()), y = (int) (p.getY() - p2.getY());

		/*
		 * for (int i = 0; i < canvasContainer.size(); i++) {
		 * 
		 * System.out.println("Checking: " + i);
		 * 
		 * if (x == canvasContainer.get(i).getX() && y == canvasContainer.get(i).getY())
		 * {
		 * 
		 * System.out.println("collision detected"); contentPanel.remove((Component)
		 * canvasContainer.get(i)); // canvasContainer.remove(i); //
		 * 
		 * revalidateAndRepaint();
		 * 
		 * ; }
		 * 
		 * }
		 */

		InkDrop kkk = new InkDrop(x, y, GLOBAL.GRIDHEIGHT, GLOBAL.GRIDWIDTH, GLOBAL.OFFSETX, GLOBAL.OFFSETY);

		canvasContainer.add(kkk);
		kkk.setId(canvasContainer.size() - 1);
		// kkk.paintComponents(kkk.getGraphics());

		kkk.setVisible(true);
		contentPanel.add(kkk, 1, 0);
		System.out.println("Penis");
		revalidateAndRepaint();
	}

	public static void ReloadFromCanvasContainer(ArrayList<Paint> loadedTanFile) {

		if (canvasContainer.size() > 1) {
			canvasContainer.clear();
		}

		// set info from loaded FileInfo object
		GLOBAL.CANVAS_HEIGHT = (int) GLOBAL.fileInfo.getCanvasSize().getHeight();
		GLOBAL.CANVAS_WIDTH = (int) GLOBAL.fileInfo.getCanvasSize().getWidth();
		GLOBAL.OFFSETX = GLOBAL.fileInfo.getOffsetX();
		GLOBAL.OFFSETX = GLOBAL.fileInfo.getOffsetY();
		setCanvasSize();
		rebuildGrid();
		GLOBAL.DEBUGFRAME.refresh();

		for (int i = 0; i < loadedTanFile.size(); i++) {

			if (loadedTanFile.get(i).getClass() == InkDrop.class) {// check if loaded element is an inkdrop

				int x = ((InkDrop) loadedTanFile.get(i)).getX();
				int y = ((InkDrop) loadedTanFile.get(i)).getY();
				int ySize = ((InkDrop) loadedTanFile.get(i)).getySize();
				int xSize = ((InkDrop) loadedTanFile.get(i)).getxSize();
				int offsetX = ((InkDrop) loadedTanFile.get(i)).getOffsetX();
				int offsetY = ((InkDrop) loadedTanFile.get(i)).getOffsetY();

				for (int c = 0; c < canvasContainer.size(); c++) {

					System.out.println("Checking: " + c);
					if (x == ((JComponent) canvasContainer.get(c)).getX()
							&& y == ((JComponent) canvasContainer.get(c)).getY()) {

						System.out.println("collision detected");
						contentPanel.remove((Component) canvasContainer.get(c));

						revalidateAndRepaint();

						;
					}

				}

				InkDrop kkk =

						new InkDrop(x, y, ySize, xSize, offsetX, offsetY);

				canvasContainer.add(kkk);
				kkk.setVisible(true);
				contentPanel.add(kkk, 1, 0);

			} else if (loadedTanFile.get(i).getClass() == TextNode.class) {
				// check if it is a TextNode
				int x = ((TextNode) loadedTanFile.get(i)).getX();
				int y = ((TextNode) loadedTanFile.get(i)).getY();
				String text = ((TextNode) loadedTanFile.get(i)).getText();
				TextNode node = new TextNode(x, y);
				node.setText(text);
				node.setVisible(true);
				node.requestFocusInWindow();
				contentPanel.add(node, 2, 0);
				canvasContainer.add(node);

			}

			revalidateAndRepaint();

		}

	}

	public static void startErasing() {
		// TODO Auto-generated method stub

		Point p = MouseInfo.getPointerInfo().getLocation();
		Point p2 = contentPanel.getLocationOnScreen();
		int x = (int) ((p.getX() - p2.getX()) - ((DIAMETER / 2)) + 16),
				y = (int) ((p.getY() - p2.getY()) - ((DIAMETER / 2)) + 16); // x and y coordinates of top-left
		// boundary of pointer
		int x2 = x + SelectedTool.brushSize, y2 = y + SelectedTool.brushSize;

		for (int i = 0; i < canvasContainer.size(); i++) {

			// canvasContainer.get(i).destroy(contentPanel);

			int ix = (int) (((JComponent) canvasContainer.get(i)).getX()); // x coordinate of i
			int iy = (int) (((JComponent) canvasContainer.get(i)).getY()); // y coordinate of i
			int iWidth = ((JComponent) canvasContainer.get(i)).getWidth(); // width of i
			int iHeight = ((JComponent) canvasContainer.get(i)).getHeight(); // height of i
			int ix2 = ix + iWidth; // width of i
			int iy2 = iy + iHeight; // height of i

			// need to check if item being erased has a coordinate that falls within the
			// coordinate of the eraser brushSize

			// (iy<y2 || iy2>y) // not overlapping Y
			// (ix2<x || ix>x2) //

			if (checkOverlap(iy, y2, iy2, y, ix2, x, ix, x2))

			{
				((InkDrop) canvasContainer.get(i)).destroy(false);
				CanvasPanel.canvasContainer.remove(i);
				System.out.println("removed " + i + " at " + x + "," + y);
				System.out.println("canvast Container size: " + canvasContainer.size());
				revalidateAndRepaint();

			}

		}

	}

	public void event() {
		DIAMETER = SelectedTool.brushSize;
		g2 = (Graphics2D) contentPanel.getGraphics();

		Point p1 = MouseInfo.getPointerInfo().getLocation();
		Point p2 = contentPanel.getLocationOnScreen();

		p = new Point((int) ((p1.x - p2.x) - (DIAMETER / 2)) + 16, (int) ((p1.y - p2.y) - (DIAMETER / 2)) + 16);

		// p = e.getPoint();

		Shape circle = new Rectangle2D.Double(p.getX(), p.getY(), DIAMETER, DIAMETER);

		g2.setXORMode(XOR_COLOR);
		g2.draw(circle);
		g2.setPaintMode();

		m_circle = circle;

	}

	private static boolean checkOverlap(int iy, int y2, int iy2, int y, int ix2, int x, int ix, int x2) {
		// TODO Auto-generated method stub

		if (ix2 < x || ix > x2) {
			System.out.println("no x overlap");
			return false;
		}

		if (iy > y2 || iy2 < y) {
			System.out.println("no y overlap");
			return false;
		}

		return true;
	}

	private static void startDeleting() { // legacy
		Point p = MouseInfo.getPointerInfo().getLocation();
		Point p2 = contentPanel.getLocationOnScreen();
		int x = (int) (p.getX() - p2.getX()), y = (int) (p.getY() - p2.getY());
		int gridSnapx = (int) (x % GLOBAL.GRIDWIDTH);
		int gridSnapy = (int) (y % GLOBAL.GRIDHEIGHT);
		x -= gridSnapx;
		y -= gridSnapy;

		for (int i = 0; i < canvasContainer.size(); i++) {

			// canvasContainer.get(i).destroy(contentPanel);

			if (x == ((JComponent) canvasContainer.get(i)).getX()
					&& y == ((JComponent) canvasContainer.get(i)).getY()) {
				((InkDrop) canvasContainer.get(i)).destroy(true);
				System.out.println("removed " + i + " at " + x + "," + y);
				System.out.println("canvast Container size: " + canvasContainer.size());
				canvasContainer.remove(i); //
				revalidateAndRepaint();
			}

		}

	}

	public static void revalidateAndRepaint() {
		gridPane.revalidate();
		gridPane.repaint();
		contentPanel.revalidate();
		contentPanel.repaint();
		GLOBAL.DEBUGFRAME.refresh();
	}

}