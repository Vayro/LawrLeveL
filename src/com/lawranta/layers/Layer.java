package com.lawranta.layers;

import java.util.List;
/**
 This is the layer OBJECT class
	
 */
public class Layer {
	
	

	
	
	int layerID;
	String layerName;
	List contains;
	boolean active = false;

	/**
	 * @return the layerID
	 */
	public int getLayerID() {
		return layerID;
	}

	/**
	 * @return the layerName
	 */
	public String getLayerName() {
		return layerName;
	}

	/**
	 * @param layerName the layerName to set
	 */
	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}

	/**
	 * @return the contains
	 */
	public List getContains() {
		return contains;
	}

	/**
	 * @param contains the contains to set
	 */
	public void setContains(List contains) {
		this.contains = contains;
	}

	/**
	 * @param layerID the layerID to set
	 */
	public void setLayerID(int layerID) {
		this.layerID = layerID;
	}
	
	public String toString()
	{
		return "Layer ID: " + getLayerID() + ", Layer name: " + getLayerName();
		
		
	}

	public boolean isActive() {
		// TODO Auto-generated method stub
		return this.active;
	}

	public void setActive(boolean b) {
		// TODO Auto-generated method stub
		this.active=b;
	}

}
