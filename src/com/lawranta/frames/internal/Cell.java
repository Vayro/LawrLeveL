/**
 * 
 */
package com.lawranta.frames.internal;

import com.lawranta.globals.ConsoleColors;

/**
 * 
 */
public class Cell {
	int x, y, xSize, ySize, roomWidth, roomHeight, offsetx, offsety, x2, y2;

	public Cell(int x, int y, int xSize, int ySize, int roomWidth, int roomHeight, int offsetx, int offsety) {

		this.x = x;
		this.y = y;
		this.xSize = xSize;
		this.ySize = ySize;
		this.roomWidth = roomWidth;
		this.roomHeight = roomHeight;
		this.offsetx = offsetx;
		this.offsety = offsety;
		this.x2=x+xSize;
		this.y2=y+ySize;
		// System.out.println(ConsoleColors.GREEN + this + ConsoleColors.RESET);
	}

	public String toString() {

		return ("Cell at: (" + this.x + ", " + this.y + ") with a size of " + this.xSize);
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the xSize
	 */
	public int getxSize() {
		return xSize;
	}

	/**
	 * @param xSize the xSize to set
	 */
	public void setxSize(int xSize) {
		this.xSize = xSize;
	}

	/**
	 * @return the ySize
	 */
	public int getySize() {
		return ySize;
	}

	/**
	 * @param ySize the ySize to set
	 */
	public void setySize(int ySize) {
		this.ySize = ySize;
	}

	/**
	 * @return the roomWidth
	 */
	public int getRoomWidth() {
		return roomWidth;
	}

	/**
	 * @param roomWidth the roomWidth to set
	 */
	public void setRoomWidth(int roomWidth) {
		this.roomWidth = roomWidth;
	}

	/**
	 * @return the roomHeight
	 */
	public int getRoomHeight() {
		return roomHeight;
	}

	/**
	 * @param roomHeight the roomHeight to set
	 */
	public void setRoomHeight(int roomHeight) {
		this.roomHeight = roomHeight;
	}

	/**
	 * @return the offsetx
	 */
	public int getOffsetx() {
		return offsetx;
	}

	/**
	 * @param offsetx the offsetx to set
	 */
	public void setOffsetx(int offsetx) {
		this.offsetx = offsetx;
	}

	/**
	 * @return the offsety
	 */
	public int getOffsety() {
		return offsety;
	}

	/**
	 * @param offsety the offsety to set
	 */
	public void setOffsety(int offsety) {
		this.offsety = offsety;
	}

	/**
	 * @return the x2
	 */
	public int getX2() {
		return x2;
	}

	/**
	 * @param x2 the x2 to set
	 */
	public void setX2(int x2) {
		this.x2 = x2;
	}

	/**
	 * @return the y2
	 */
	public int getY2() {
		return y2;
	}

	/**
	 * @param y2 the y2 to set
	 */
	public void setY2(int y2) {
		this.y2 = y2;
	}
}
