package com.lawranta.canvas;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Iterator;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import com.lawranta.globals.GLOBAL;
import com.lawranta.panels.CanvasPanel;

public class Zoom {
	public static double factor = 1;
	public static boolean zooming = false;

	public Zoom() {
		// TODO Auto-generated constructor stub
	}

	public static void zoomIn() {

		factor += 1;
		zooming = true;

		for (int i = 0; i < CanvasPanel.canvasContainer.size(); i++) {

			if (CanvasPanel.canvasContainer.get(i).getClass() == InkDrop.class) {
				int x = ((InkDrop) CanvasPanel.canvasContainer.get(i)).getUnscaledX();
				int y = ((InkDrop) CanvasPanel.canvasContainer.get(i)).getUnscaledY();

				int xSize = ((InkDrop) CanvasPanel.canvasContainer.get(i)).getUnscaledXSize();
				int ySize = ((InkDrop) CanvasPanel.canvasContainer.get(i)).getUnscaledYSize();

				((InkDrop) CanvasPanel.canvasContainer.get(i)).setX((int) (x * factor));
				((InkDrop) CanvasPanel.canvasContainer.get(i)).setY((int) (y * factor));
				((InkDrop) CanvasPanel.canvasContainer.get(i)).setxSize((int) (xSize * factor));
				((InkDrop) CanvasPanel.canvasContainer.get(i)).setySize((int) (ySize * factor));
				((InkDrop) CanvasPanel.canvasContainer.get(i)).draw();
			} else if(CanvasPanel.canvasContainer.get(i).getClass() == TextNode.class) {
				((TextNode) CanvasPanel.canvasContainer.get(i)).setLocation();

			//	((TextNode) CanvasPanel.canvasContainer.get(i)).setLocation((int)( CanvasPanel.canvasContainer.get(i).getX()*factor*factor),(int)( CanvasPanel.canvasContainer.get(i).getY()*factor*factor));
				
			}
		}

	//	CanvasPanel.setCanvasSize();
		

	resize();
		
	}

	public static void zoomOut() {

		if (factor > 1) {
			factor -= 1;
			zooming = true;

			/*
			 * 
			 * Iterator<Paint> it = CanvasPanel.canvasContainer.iterator();
			 * while(it.hasNext()) { it.next().draw(); }
			 */
			for (int i = 0; i < CanvasPanel.canvasContainer.size(); i++) {

				if (CanvasPanel.canvasContainer.get(i).getClass() == InkDrop.class) {
					int x = ((InkDrop) CanvasPanel.canvasContainer.get(i)).getUnscaledX();
					int y = ((InkDrop) CanvasPanel.canvasContainer.get(i)).getUnscaledY();

					int xSize = ((InkDrop) CanvasPanel.canvasContainer.get(i)).getUnscaledXSize();
					int ySize = ((InkDrop) CanvasPanel.canvasContainer.get(i)).getUnscaledYSize();

					((InkDrop) CanvasPanel.canvasContainer.get(i)).setX((int) (x* factor));
					((InkDrop) CanvasPanel.canvasContainer.get(i)).setY((int) (y* factor));
					((InkDrop) CanvasPanel.canvasContainer.get(i)).setxSize((int) (xSize * factor));
					((InkDrop) CanvasPanel.canvasContainer.get(i)).setySize((int) (ySize * factor));
					((InkDrop) CanvasPanel.canvasContainer.get(i)).draw();
				} else if(CanvasPanel.canvasContainer.get(i).getClass() == TextNode.class) {
					((TextNode) CanvasPanel.canvasContainer.get(i)).setLocation();
				//	((TextNode) CanvasPanel.canvasContainer.get(i)).setLocation((int)( CanvasPanel.canvasContainer.get(i).getX()*factor*factor),(int)( CanvasPanel.canvasContainer.get(i).getY()*factor*factor));
					
				}

			}

		resize();
			


		}
	}

	public static void zoomDefault() {

		factor = 1;
		zooming = true;

		for (int i = 0; i < CanvasPanel.canvasContainer.size(); i++) {

			if (CanvasPanel.canvasContainer.get(i).getClass() == InkDrop.class) {
				int x = ((InkDrop) CanvasPanel.canvasContainer.get(i)).getUnscaledX();
				int y = ((InkDrop) CanvasPanel.canvasContainer.get(i)).getUnscaledY();

				int xSize = ((InkDrop) CanvasPanel.canvasContainer.get(i)).getUnscaledXSize();
				int ySize = ((InkDrop) CanvasPanel.canvasContainer.get(i)).getUnscaledYSize();

				((InkDrop) CanvasPanel.canvasContainer.get(i)).setX((int) (x));
				((InkDrop) CanvasPanel.canvasContainer.get(i)).setY((int) (y));
				((InkDrop) CanvasPanel.canvasContainer.get(i)).setxSize((int) (xSize));
				((InkDrop) CanvasPanel.canvasContainer.get(i)).setySize((int) (ySize));
				((InkDrop) CanvasPanel.canvasContainer.get(i)).draw();
			} else if(CanvasPanel.canvasContainer.get(i).getClass() == TextNode.class) {
				((TextNode) CanvasPanel.canvasContainer.get(i)).setLocation();

			//	((TextNode) CanvasPanel.canvasContainer.get(i)).setLocation((int)( CanvasPanel.canvasContainer.get(i).getX()*factor*factor),(int)( CanvasPanel.canvasContainer.get(i).getY()*factor*factor));
				
			}

		}

		CanvasPanel.setCanvasSize();
		CanvasPanel.contentPanel.requestFocus();

	}
	
	private static void resize() {
		CanvasPanel.contentPanel.setSize((int) (GLOBAL.CANVAS_WIDTH*factor),(int) (GLOBAL.CANVAS_HEIGHT*factor));
		CanvasPanel.contentPanel.setPreferredSize(new Dimension((int) (GLOBAL.CANVAS_WIDTH*factor),(int) (GLOBAL.CANVAS_HEIGHT*factor)));
		CanvasPanel.rebuildGrid();
		SwingUtilities.updateComponentTreeUI(GLOBAL.MAINFRAME);
		CanvasPanel.contentPanel.setVisible(true);
		CanvasPanel.contentPanel.requestFocus();
		GLOBAL.CP.clearCircle();
		CanvasPanel.revalidateAndRepaint() ;
		SwingUtilities.invokeLater(refresh());
	}
	
	private static Runnable refresh() {
		return new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				CanvasPanel.revalidateAndRepaint() ;
			}
			
			
			
		};
		
		
	}

}
