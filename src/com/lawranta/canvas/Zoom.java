package com.lawranta.canvas;

import java.util.Iterator;

import com.lawranta.panels.CanvasPanel;

public class Zoom {
	public static double factor=1;
	public static boolean zooming=false;

	public Zoom() {
		// TODO Auto-generated constructor stub
	}
	
	public static void zoomIn() {
		
		 factor+=1;
		 zooming=true;
		 CanvasPanel.setCanvasSize();
		 CanvasPanel.rebuildGrid();
		 CanvasPanel.revalidateAndRepaint();
		
	}
	
	public static void zoomOut() {
		
		if(factor>1) {
		factor-=1;
		 zooming=true;
		 CanvasPanel.setCanvasSize();
		 CanvasPanel.rebuildGrid();
		 CanvasPanel.revalidateAndRepaint();}
	}
	
	public static void zoomDefault() {
		
		factor=1;
		 zooming=true;
		 CanvasPanel.setCanvasSize();
		 CanvasPanel.rebuildGrid();
		 CanvasPanel.revalidateAndRepaint();
	}
	

}
