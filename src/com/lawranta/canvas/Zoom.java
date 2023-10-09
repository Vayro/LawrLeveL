package com.lawranta.canvas;

import java.awt.Component;
import java.util.Iterator;
import javax.swing.JComponent;

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
			}
		}

	//	CanvasPanel.setCanvasSize();
		CanvasPanel.contentPanel.requestFocus();
		
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
				}

			}

		//	CanvasPanel.setCanvasSize();
			CanvasPanel.contentPanel.requestFocus();
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
			}

		}

		CanvasPanel.setCanvasSize();
		CanvasPanel.contentPanel.requestFocus();

	}

}
