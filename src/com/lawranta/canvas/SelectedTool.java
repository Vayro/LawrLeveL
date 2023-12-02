package com.lawranta.canvas;

import java.awt.Color;

public class SelectedTool {
	// should be singleton class
	public static int selectedTool;
	public static Color selectedColor = new Color(0, 0, 0);
	public static int brushSize = 32;
	/*
	 * 1: InkDrop  
	 * 2: Text
	 * 3: Eraser
	 * 4: Selection
	 * 5: Eyedropper
	 * 6: true Brush
	 * 
	 * 
	 * 
	 * 
	 */

	/**
	 * @return the selectedTool
	 */
	public int getSelectedTool() {
		return selectedTool;
	}

	/**
	 * @param selectedTool the selectedTool to set
	 */
	public void setSelectedTool(int selectedTool) {
		SelectedTool.selectedTool = selectedTool;
	}

	public SelectedTool() {
	}

	public static void setToolDefault() {
		selectedTool = 1;
		selectedColor = new Color(0, 0, 0);
	}

	public static void setTextTool() {
		selectedTool = 2;

	}

	public static void setInkDropTool() {
		selectedTool = 1;

	}

	public static void setEraserTool() {
		selectedTool = 3;

	}
	
	public static void setSelectionTool() {
		selectedTool = 4;

	}

	public static void setEyeDropperTool() {
		// TODO Auto-generated method stub
		selectedTool = 5;
	}
	
	public static void setBrushTool() {
		// TODO Auto-generated method stub
		selectedTool = 6;
	}

	public static int getBrushSize() {
		// TODO Auto-generated method stub
		return brushSize;
	}

}
