package com.lawranta.edit;

import java.awt.Color;
import java.util.List;

import com.lawranta.canvas.InkDrop;
import com.lawranta.canvas.TextNode;
import com.lawranta.edit.DoListStack.RedoListStack;

public class DoListItem {
	
	String action=null;
	int x, y, xSize, ySize, offset, id;
	Color color;
	String text;
	InkDrop inkDrop;
	TextNode textNode;
	
	
	

	public DoListItem(String action, InkDrop inkDrop) {
		// TODO Auto-generated constructor stub
		this.inkDrop=inkDrop;
		this.x=inkDrop.getX();
		this.y=inkDrop.getY();
		this.xSize=inkDrop.getxSize();
		this.ySize=inkDrop.getySize();
		this.color=inkDrop.getColor();
		this.id=inkDrop.getId();
		this.action=action;
		DoListStack.undoList.add(this);
		DoListStack.redoList.clear();
	}
	
	public DoListItem(String action, TextNode textNode) {
		// TODO Auto-generated constructor stub
		this.textNode=textNode;
		this.x=textNode.getX();
		this.y=textNode.getY();
		this.id=textNode.getId();
		this.action=action;
		DoListStack.undoList.add(this);
		DoListStack.redoList.clear();
	}
	
	public DoListItem(String action) {
		// TODO Auto-generated constructor stub
		this.action=action;
		DoListStack.undoList.add(this);
		DoListStack.redoList.clear();
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
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
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
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
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the inkDrop
	 */
	public InkDrop getInkDrop() {
		return inkDrop;
	}

	/**
	 * @param inkDrop the inkDrop to set
	 */
	public void setInkDrop(InkDrop inkDrop) {
		this.inkDrop = inkDrop;
	}

	/**
	 * @return the textNode
	 */
	public TextNode getTextNode() {
		return textNode;
	}

	/**
	 * @param textNode the textNode to set
	 */
	public void setTextNode(TextNode textNode) {
		this.textNode = textNode;
	}
	

}
