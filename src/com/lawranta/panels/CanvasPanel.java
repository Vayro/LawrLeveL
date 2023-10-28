package com.lawranta.panels;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import com.lawranta.canvas.CreateGrid;
import com.lawranta.canvas.InkDrop;
import com.lawranta.canvas.Paint;
import com.lawranta.canvas.TextNode;
import com.lawranta.canvas.Zoom;
import com.lawranta.edit.DoListItem;
import com.lawranta.frames.MainFrame;
import com.lawranta.frames.internal.Menu;
import com.lawranta.canvas.SelectedTool;
import com.lawranta.globals.GLOBAL;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import java.awt.FlowLayout;

public class CanvasPanel extends JPanel {

	public static int canvasWidthDefault = 1024;
	public static int canvasHeightDefault = 1024;
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
	public static JComponent canvas;
	Graphics2D g2 = (Graphics2D) getGraphics();
	Point p;
	MouseEvent e;
	Point point;
	Boolean draggingGrid = false;
	private static Shape m_circle = null;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (Zoom.zooming == true) {
			AffineTransform at = new AffineTransform();
			at.scale(Zoom.factor, Zoom.factor);
			Zoom.zooming = false;
			g2.transform(at);
		}
	}

	public CanvasPanel() {
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		canvas = this;
		setAlignmentY(Component.BOTTOM_ALIGNMENT);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setBackground(GLOBAL.bgColor);
		setFocusable(true);
		requestFocus(true);
		SelectedTool.setToolDefault();
		getCP = this;

		contentPanel = new JLayeredPane() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 7384584610102161622L;

			public boolean isFocusTraversable() {
				return true;
			}

		};
		contentPanel.setFocusable(true);
		;
		contentPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);

		contentPanel.setBackground(GLOBAL.gridBGColor);
		contentPanel.setOpaque(true);
		contentPanel.setSize(new Dimension(canvasWidthDefault, canvasHeightDefault));
		contentPanel.setMinimumSize(new Dimension(canvasWidthDefault, canvasHeightDefault));
		contentPanel.setPreferredSize(new Dimension(canvasWidthDefault, canvasHeightDefault));
		contentPanel.setLocation(32, 32);
		gridPane = new GridPanel();

		add(contentPanel);

		contentPanel.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == 17) {
					GLOBAL.MENU.requestFocus();
					GLOBAL.ctrlPressed = true;
					System.out.println("ctrl..");
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == 17) {
					GLOBAL.ctrlPressed = false;
				}
			}

		});

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

				if (e.getButton() == 2) {

					System.out.println("Started Dragging");
					draggingGrid = true;
					point = MouseInfo.getPointerInfo().getLocation();

				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				mouse = 0;
				if (e.getButton() == 2) {

					System.out.println("Stopped Dragging");
					draggingGrid = false;
					point = null;

				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				GLOBAL.MAINFRAME.requestFocus();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("clearing circle as mouse exits");
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

				if (mouse == 2) {

					{// change offset of grid
						int max = GLOBAL.GRIDHEIGHT;

						double dragX = MouseInfo.getPointerInfo().getLocation().getX() - point.getX();
						double dragY = MouseInfo.getPointerInfo().getLocation().getY() - point.getY();
						;
						System.out.println(MouseInfo.getPointerInfo().getLocation() + "-" + point);
						System.out.println(dragX + " , " + dragY);

						if (dragX > max) {
							dragX = max;

						}

						GLOBAL.OFFSETX = (int) dragX;

						if (GLOBAL.OFFSETX < 0) {
							GLOBAL.OFFSETX = 0;
						}

						if (dragY > max) {
							dragY = max;

						}

						GLOBAL.OFFSETY = (int) dragY;

						if (GLOBAL.OFFSETY < 0) {
							GLOBAL.OFFSETY = 0;
						}

						System.out.println("GLOBAL.OFFSETX=" + GLOBAL.OFFSETX);
						/*
						 * if (Math.abs(dragX) < max) { GLOBAL.OFFSETY += (int) dragY; }
						 */
						revalidateAndRepaint();

					}

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

					// System.out.println("Selected Tool: " + SelectedTool.selectedTool);
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

	public void clearCircle() {
		if (m_circle != null) {
			if(g2!=null) {
			g2.setXORMode(XOR_COLOR);
			g2.draw(m_circle);
			g2.setPaintMode();

			m_circle = null;
			
		}}
		revalidateAndRepaint();
	}

	public static void setCanvasSize() {

		int sizeX = (int) (GLOBAL.CANVAS_WIDTH * Zoom.factor);
		int sizeY = (int) (GLOBAL.CANVAS_HEIGHT * Zoom.factor);
		contentPanel.setVisible(false);
		contentPanel.setSize(new Dimension(sizeX, sizeY));
		contentPanel.setMinimumSize(new Dimension(sizeX, sizeY));
		contentPanel.setPreferredSize(new Dimension(sizeX, sizeY));
		// MainFrame.scrollPane.setViewportView(null);
		// getCP.setSize(new Dimension(sizeX, sizeY));
		System.out.println("Testing zoom");
		// MainFrame.scrollPane.setViewportView(getCP);

		contentPanel.getParent().revalidate();
		contentPanel.getParent().repaint();
		contentPanel.getParent().getParent().revalidate();
		contentPanel.getParent().getParent().repaint();
		contentPanel.getParent().getParent().getParent().revalidate();
		contentPanel.getParent().getParent().getParent().repaint();
		rebuildGrid();

		revalidateAndRepaint();
		contentPanel.setVisible(true);
	}

	public static void rebuildGrid() {
		contentPanel.remove(gridPane);
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

		// add to undo stack
		DoListItem item = new DoListItem("nodeCreated", node);

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

	public static void startPainting() {
		// TODO Auto-generated method stub
		Point p = MouseInfo.getPointerInfo().getLocation();
		Point p2 = contentPanel.getLocationOnScreen();
		int x = (int) (p.getX() - p2.getX()), y = (int) (p.getY() - p2.getY());



		InkDrop kkk = new InkDrop(x, y, GLOBAL.GRIDHEIGHT, GLOBAL.GRIDWIDTH, GLOBAL.OFFSETX, GLOBAL.OFFSETY,
				SelectedTool.selectedColor);

		canvasContainer.add(kkk);
		kkk.setId(canvasContainer.size() - 1);
		// kkk.paintComponents(kkk.getGraphics());

		kkk.setVisible(true);
		contentPanel.add(kkk, 1, 0);
		DoListItem item = new DoListItem("inkCreated", kkk);
		revalidateAndRepaint();
	}

	public static void ReloadFromCanvasContainer(ArrayList<Paint> loadedTanFile) {
		contentPanel.removeAll();
		revalidateAndRepaint();
		
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

		
		
		loadedTanFile.sort((o1, o2)
                 -> Integer.compare(o1.getId(),o2.getId()));
   
		
		
		
		
		
		for (int i = 0; i < loadedTanFile.size(); i++) {

			if (loadedTanFile.get(i).getClass() == InkDrop.class) {// check if loaded element is an inkdrop

				int x = ((InkDrop) loadedTanFile.get(i)).getX();
				int y = ((InkDrop) loadedTanFile.get(i)).getY();
				int ySize = ((InkDrop) loadedTanFile.get(i)).getySize();
				int xSize = ((InkDrop) loadedTanFile.get(i)).getxSize();
				int offsetX = ((InkDrop) loadedTanFile.get(i)).getOffsetX();
				int offsetY = ((InkDrop) loadedTanFile.get(i)).getOffsetY();
				Color color = loadedTanFile.get(i).getColor();

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

						new InkDrop(x, y, ySize, xSize, offsetX, offsetY, color);

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

			int ix = (int) (((JComponent) canvasContainer.get(i)).getX()); // x coordinate of i
			int iy = (int) (((JComponent) canvasContainer.get(i)).getY()); // y coordinate of i
			int iWidth = ((JComponent) canvasContainer.get(i)).getWidth(); // width of i
			int iHeight = ((JComponent) canvasContainer.get(i)).getHeight(); // height of i
			int ix2 = ix + iWidth; // width of i
			int iy2 = iy + iHeight; // height of i

			// need to check if item being erased has a coordinate that falls within the
			// coordinate of the eraser brushSize

			if (checkOverlap(iy, y2, iy2, y, ix2, x, ix, x2))

			{
				((Paint) canvasContainer.get(i)).destroy(false);
				// CanvasPanel.canvasContainer.remove(i);
				System.out.println("removed " + i + " at " + x + "," + y);
				System.out.println("canvas Container size: " + canvasContainer.size());
				revalidateAndRepaint();

				if (canvasContainer.get(i).getClass() == InkDrop.class) {
					DoListItem item = new DoListItem("inkDeleted", (InkDrop) canvasContainer.get(i));
				} else if (canvasContainer.get(i).getClass() == TextNode.class) {
					DoListItem item = new DoListItem("nodeDeleted", (TextNode) canvasContainer.get(i));
				}

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

		// g2.setXORMode(XOR_COLOR);
		g2.draw(circle);
		g2.setPaintMode();

		m_circle = circle;

	}

	private static boolean checkOverlap(int iy, int y2, int iy2, int y, int ix2, int x, int ix, int x2) {
		// TODO Auto-generated method stub

		if (ix2 < x || ix > x2) {
			// System.out.println("no x overlap");
			return false;
		}

		if (iy > y2 || iy2 < y) {
			// System.out.println("no y overlap");
			return false;
		}

		return true;
	}

	public static void revalidateAndRepaint() {
		// System.out.println("Revalidating and Repainting");
		contentPanel.setBackground(GLOBAL.gridBGColor);
		canvas.setBackground(GLOBAL.bgColor);
		
		if(gridPane.getParent()!=null) {
		gridPane.getParent().getParent().revalidate();
		gridPane.getParent().getParent().repaint();
		}
		
		
		gridPane.revalidate();
		gridPane.repaint();
		contentPanel.revalidate();
		contentPanel.repaint();
		GLOBAL.DEBUGFRAME.refresh();
	}

}