package com.lawranta.canvas;

public class Tool {
	//should be singleton class
	public static int selectedTool, selectedColor;
	/*
	 * 0: InkDrop
	 * 1: Text
	 * 
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




	public Tool() {
		// TODO Auto-generated constructor stub
	}




	public static void setToolDefault() {
		// TODO Auto-generated method stub
		//selectedTool=1;
		
	}




	public static void setTextTool() {
		// TODO Auto-generated method stub
		selectedTool=2;
		
	}




	public static void setInkDropTool() {
		// TODO Auto-generated method stub
		selectedTool=1;
		
	}

}
