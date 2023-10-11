package com.lawranta.file;

import java.awt.Dimension;
import java.io.Serializable;

import com.lawranta.globals.GLOBAL;

public class FileInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5937948156098489479L;
	protected String fileName, absPath;
	Dimension canvasSize;
	boolean newFile, saved;
	protected int gridSize, offsetX, offsetY;
	double version;
	
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the newFile
	 */
	public boolean isNewFile() {
		return newFile;
	}

	/**
	 * @param newFile the newFile to set
	 */
	public void setNewFile(boolean newFile) {
		this.newFile = newFile;
	}

	/**
	 * @return the version
	 */
	public double getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(double version) {
		this.version = version;
	}

	/**
	 * @return the absPath
	 */
	public String getAbsPath() {
		return absPath;
	}

	/**
	 * @param absPath the absPath to set
	 */
	public void setAbsPath(String absPath) {
		this.absPath = absPath;
	}

	/**
	 * @return the saved
	 */
	public boolean getSaved() {
		return saved;
	}

	/**
	 * @param saved the saved to set
	 */
	public void setSaved(boolean saved) {
		this.saved = saved;
	}

	/**
	 * @return the canvasSize
	 */
	public Dimension getCanvasSize() {
		return canvasSize;
	}

	/**
	 * @param canvasSize the canvasSize to set
	 */
	public void setCanvasSize(Dimension canvasSize) {
		this.canvasSize = canvasSize;
	}

	/**
	 * @return the gridSize
	 */
	public int getGridSize() {
		return gridSize;
	}

	/**
	 * @param gridSize the gridSize to set
	 */
	public void setGridSize(int gridSize) {
		this.gridSize = gridSize;
	}

	/**
	 * @return the offsetX
	 */
	public int getOffsetX() {
		return offsetX;
	}

	/**
	 * @param offsetX the offsetX to set
	 */
	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	/**
	 * @return the offsetY
	 */
	public int getOffsetY() {
		return offsetY;
	}

	/**
	 * @param offsetY the offsetY to set
	 */
	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}

	public FileInfo() {
		// TODO Auto-generated constructor stub
		
		//default
		version=GLOBAL.VERSION;
		newFile=true;
		canvasSize=new Dimension(GLOBAL.CANVAS_WIDTH,GLOBAL.CANVAS_HEIGHT);
		saved=false;
		fileName="Emilia.tan";
		absPath=null;
		gridSize=GLOBAL.DEFAULTGRIDSIZE;
		offsetX=0;
		offsetY=0;
		
		
		
		
	}

}
