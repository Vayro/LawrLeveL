package com.lawranta.canvas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import com.lawranta.edit.DoListItem;
import com.lawranta.globals.GLOBAL;
import com.lawranta.panels.CanvasPanel;

public class InkDrop extends JPanel implements Paint, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7177229581515071838L;
	boolean selected;
	int id, x, y, xSize, ySize, offsetX, offsetY, origX, origY;
	int unscaledXSize, unscaledYSize, unscaledX, unscaledY;
	boolean createdWhileZoomed;

	/**
	 * @return 
	 * @return the id
	 */
	
	@Override
	public boolean isFocusTraversable(){
		
		return true;
	}
	
	public int getId() {
		return id;
	}

	public int getUnscaledXSize() {
		return unscaledXSize;
	}

	public void setUnscaledXSize(int unscaledXSize) {
		this.unscaledXSize = unscaledXSize;
	}

	public int getUnscaledYSize() {
		return unscaledYSize;
	}

	public void setUnscaledYSize(int unscaledYSize) {
		this.unscaledYSize = unscaledYSize;
	}

	public int getUnscaledX() {
		return unscaledX;
	}

	public void setUnscaledX(int unscaledX) {
		this.unscaledX = unscaledX;
	}

	public int getUnscaledY() {
		return unscaledY;
	}

	public void setUnscaledY(int unscaledY) {
		this.unscaledY = unscaledY;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void setRemoved(boolean removed) {
		this.removed = removed;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	boolean removed = false;
	Color color = SelectedTool.selectedColor;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!removed) {
			g.setColor(this.color);
			g.fillRect(0, 0, (int) (xSize * Zoom.factor), (int) (ySize * Zoom.factor));
			if(selected) {
				g.setColor(new Color(255,255,0,155));
				g.drawRoundRect(1, 1, (int) (xSize * Zoom.factor)-4, (int) (ySize * Zoom.factor)-4, 2, 2);
			}
			g.setColor(this.color);
			

		} else {

			g.clearRect(0, 0, (int) (xSize * Zoom.factor), (int) (ySize * Zoom.factor));
		}

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getxSize() {
		return xSize;
	}

	public void setxSize(int xSize) {
		this.xSize = xSize;
	}

	public int getySize() {
		return ySize;
	}

	public void setySize(int ySize) {
		this.ySize = ySize;
	}

	public InkDrop(int x, int y, int xSize, int ySize, int offsetX, int offsetY, Color color) {
		this.color = color;

		setRequestFocusEnabled(false);
		setBackground(this.color);
		requestFocus();
		// setBackground(new Color(255, 0, 0)); // debug color
		// TODO Auto-generated constructor stub
		this.selected=false;
		this.unscaledXSize = xSize;
		this.unscaledYSize = ySize;
		this.xSize = (int) (xSize * Zoom.factor);
		this.ySize = (int) (ySize * Zoom.factor);
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.origX = x;
		this.origY = y;

		if (Zoom.factor > 1) {

			this.createdWhileZoomed = true;
		}

		int gridSnapx = (int) (x % this.xSize);
		int gridSnapy = (int) (y % this.ySize);

		this.x = (int) ((x - (gridSnapx)));

		this.y = (int) ((y - (gridSnapy)));

		this.x -= this.offsetX;
		this.y -= this.offsetY;

		if (this.x + xSize < origX / Zoom.factor) {

			this.x += xSize * Zoom.factor;
		}

		if (this.y + xSize < origY / Zoom.factor) {

			this.y += ySize * Zoom.factor;
		}

		this.unscaledX = (int) (this.x / Zoom.factor);
		this.unscaledY = (int) (this.y / Zoom.factor);

		draw();
		setLayout(null);
		System.out.println("inkDrop created at " + this.x + "," + this.y + " Size(" + xSize + "," + ySize + ")");

		for (int i = 0; i < CanvasPanel.canvasContainer.size(); i++) {

			System.out.println(i + " out of " + CanvasPanel.canvasContainer.size());
			if (x == ((Component) CanvasPanel.canvasContainer.get(i)).getX()
					&& y == ((Component) CanvasPanel.canvasContainer.get(i)).getY()) {
				GLOBAL.CP.remove((Component) CanvasPanel.canvasContainer.get(i));
				CanvasPanel.canvasContainer.remove(i);
				// i--;
				GLOBAL.CP.repaint();
			}

		}
		
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Key Typed");
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Key Pressed");
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Key Released");
			}

		});


		MouseMotionListener listener = new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("dragged");
				if (CanvasPanel.mouse == 3 || e.getButton() == 3) {
					startDeleting();
				}

				if (e.getButton() == 1) {
					// CanvasPanel.mouse =1;
					if (SelectedTool.selectedTool == 3) {
						CanvasPanel.startErasing();

					} else if (SelectedTool.selectedTool == 1) {

						CanvasPanel.startPainting();

					}

				}

			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				CanvasPanel.mouse = 0;
			}

		};

		MouseListener clickListener = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				CanvasPanel.mouse = e.getButton();
				if (e.getButton() == 3) {
					startDeleting();

				}
				if (e.getButton() == 1) {
					if (SelectedTool.selectedTool == 3) {
						CanvasPanel.mouse = 1;
						CanvasPanel.startErasing();

					} else if (SelectedTool.selectedTool == 1) {

						CanvasPanel.startPainting();

					}else if (SelectedTool.selectedTool == 4) {
						
						selected=!selected;
						draw();
					}
					
					
					

				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				CanvasPanel.mouse = 0;

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Stop painting here?j");

			}
		};

		addMouseListener(clickListener);
		addMouseMotionListener(listener);

		this.origX = (int) (origX / Zoom.factor);
		this.origY = (int) (origY / Zoom.factor);

		checkCollision();

	}

	public void destroy(boolean removeFromContainer) {
		// TODO Auto-generated method stub
		System.out.println(" deleting mySelf... :( ");

		// x=0;y=0;xSize=0; ySize=0;
		setVisible(false);
		removed = true;
		revalidate();
		repaint();
		CanvasPanel.contentPanel.remove(this);

		if (removeFromContainer) {
			CanvasPanel.canvasContainer.remove(this.id);
			for (int i = 0; i < CanvasPanel.canvasContainer.size(); i++) {

				CanvasPanel.canvasContainer.get(i).setId(i);
			}
		}

	}

	private void startDeleting() {

//
		destroy(true);
		DoListItem item = new DoListItem("inkDeleted", this);
		for (int i = 0; i < CanvasPanel.canvasContainer.size(); i++) {

			CanvasPanel.canvasContainer.get(i).setId(i);

		}

		CanvasPanel.revalidateAndRepaint();

	}

	private void checkCollision() {

		for (int i = 0; i < CanvasPanel.canvasContainer.size(); i++) {

			System.out.println("Checking: " + i);

			if (CanvasPanel.canvasContainer.get(i).getClass() == InkDrop.class) {
				// first check if inkdrop

				if (this.x == CanvasPanel.canvasContainer.get(i).getX()
						&& this.y == CanvasPanel.canvasContainer.get(i).getY()
						&& this.xSize == CanvasPanel.canvasContainer.get(i).getxSize()
						&& this.ySize == CanvasPanel.canvasContainer.get(i).getySize())

				{

					System.out.println("collision detected");
					((InkDrop) CanvasPanel.canvasContainer.get(i)).destroy(true);

					CanvasPanel.revalidateAndRepaint();

					;
				}
			}
		}

	}

	public void draw() {

		setBounds((int) (this.x), (int) (this.y), (int) (unscaledXSize * (Zoom.factor)),
				(int) (unscaledYSize * (Zoom.factor)));

		revalidate();
		repaint();

	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Key Typed");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Key Pressed");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Key Released");
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return selected;
	}

	@Override
	public void setSelected(boolean selected) {
		// TODO Auto-generated method stub
		this.selected=selected;
		
	}

	@Override
	public Runnable refresh() {
		// TODO Auto-generated method stub
		return new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				draw();
				CanvasPanel.revalidateAndRepaint();
			}

		};
	}

}
