package com.lawranta.edit;

import java.awt.Color;
import java.util.List;

import com.lawranta.canvas.InkDrop;
import com.lawranta.canvas.TextNode;

public class DoListItem {
	
	String[] action=null;
	int x, y, xSize, ySize, offset;
	Color color;
	String text;
	InkDrop inkDrop;
	TextNode TextNode;
	
	
	

	public DoListItem(String action[], InkDrop inkDrop) {
		// TODO Auto-generated constructor stub
		this.x=inkDrop.getX();
		this.y=inkDrop.getY();
		this.xSize=inkDrop.getxSize();
		this.ySize=inkDrop.getySize();
		this.color=inkDrop.getColor();
		this.action=action;
	}
	
	public DoListItem(String action[], TextNode textNode) {
		// TODO Auto-generated constructor stub
		this.x=textNode.getX();
		this.y=textNode.getY();
		this.action=action;
	}
	
	public DoListItem(String action[]) {
		// TODO Auto-generated constructor stub
		this.action=action;
	}
	

}
