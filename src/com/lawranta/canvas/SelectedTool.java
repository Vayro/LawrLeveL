package com.lawranta.canvas;

import java.awt.Color;

public class SelectedTool {
	//should be singleton class
	public static int selectedTool;
	public static Color selectedColor=new Color(0,0,0);
	/*
	 * 1: InkDrop
	 * 2: Text
	 * 3: Eraser
	 * 
	 * 
	 * 
	 * 
	 * 
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
		this.selectedTool = selectedTool;
	}




	public SelectedTool() {
		// TODO Auto-generated constructor stub
	}




	public static void setToolDefault() {
		// TODO Auto-generated method stub
		selectedTool=1;
		selectedColor=new Color(0,0,0);
	}




	public static void setTextTool() {
		// TODO Auto-generated method stub
		selectedTool=2;
		
	}




	public static void setInkDropTool() {
		// TODO Auto-generated method stub
		selectedTool=1;
		
	}




	public static void setEraserTool() {
		// TODO Auto-generated method st
		selectedTool=3;
		
	}

}
