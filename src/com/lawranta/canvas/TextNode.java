package com.lawranta.canvas;

import java.awt.Color;

import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.lawranta.globals.GLOBAL;
import com.lawranta.layers.Layer;
import com.lawranta.layers.LayerContainer;
import com.lawranta.panels.*;

public class TextNode extends JTextField implements Paint {
	/**
	 * 
	 */
	TextNode thisNode;
	int opacity = 100;
	private static final long serialVersionUID = -4669795077837742209L;
	Layer layer;
	boolean selected;

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	int id, x, y, xSize, ySize, unscaledX, unscaledY;;

	/**
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the removed
	 */
	public boolean isRemoved() {
		return removed;
	}

	/**
	 * @param removed the removed to set
	 */
	public void setRemoved(boolean removed) {
		this.removed = removed;
	}

	boolean removed = false;

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

	/**
	 * Create the panel.
	 */
	public TextNode(int x, int y) {
		setLayout(null);
		this.x = x;
		this.y = y;
		thisNode = this;
		this.selected = false;
		this.layer = LayerContainer.getActiveLayer();
		this.opacity=layer.getOpacity();

		addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				setEditable(true);
				setBackground(new Color(255, 255, 255));
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				setEditable(false);
				setBackground(new Color(255, 255, 255, 50));

				if (getText().length()==0) {
					// setVisible(false);
					CanvasPanel.contentPanel.remove(thisNode);
					System.out.println("removed text node");
				}

				CanvasPanel.contentPanel.revalidate();
				CanvasPanel.contentPanel.repaint();

			}

		});

		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == 10) {
					System.out.println("enter pressed");
					GLOBAL.MAINFRAME.requestFocus();

				}
				if (e.getKeyCode() == 17) {
					System.out.println("ctrl pressed");
					GLOBAL.MAINFRAME.requestFocus();
					GLOBAL.ctrlPressed = true;
				}

				if (e.getKeyCode() == 127) {
					System.out.println("Delete pressed");
					GLOBAL.MAINFRAME.requestFocus();
					CanvasPanel.contentPanel.remove(thisNode);

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

		setDragEnabled(true);
		setBackground(new Color(255, 255, 255, 50));
		setSize(new Dimension(128, 32));
		setMaximumSize(new Dimension(128, 128));
		setPreferredSize(new Dimension(128, 32));
		setMinimumSize(new Dimension(128, 128));

		this.x = x;
		this.y = y;

		// this.setBackground(new Color(0,0,0,100));
		this.setLocation(x, y);
		this.setText("New Text");
		this.setVisible(true);
		this.requestFocus();
		this.grabFocus();
		System.out.print("new text node");

	}

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub

	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setLocation() {
		// TODO Auto-generated method stub
		String placeholder = thisNode.getText();
		CanvasPanel.contentPanel.remove(thisNode);
		thisNode = new TextNode((int) (x * Zoom.factor), (int) (y * Zoom.factor));
		thisNode.setText(placeholder);
		CanvasPanel.contentPanel.add(thisNode, 2, 0);
	}

	@Override
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

	@Override
	public Runnable refresh() {
		// TODO Auto-generated method stub
		return new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				draw();
			}

		};
	}

	@Override
	public Layer getLayer() {
		// TODO Auto-generated method stub
		return this.layer;
	}

	@Override
	public void toggleVisibility(boolean v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLayer(Layer layer) {
		// TODO Auto-generated method stub
		this.layer = layer;

	}

	@Override
	public void setOpacity(int opacity) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getOpacity() {
		// TODO Auto-generated method stub
		return 0;
	}

}
