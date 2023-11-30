package com.lawranta.layers;

import java.io.Serializable;
import java.util.List;

import com.lawranta.canvas.Paint;
import com.lawranta.panels.CanvasPanel;
/**
 This is the layer OBJECT class
	
 */
public class Layer implements Serializable  {
	
	

	
	
	int layerID, opacity=255;
	String layerName;
	List contains;
	boolean active = false, visible=true;

	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

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

	public int getOpacity() {
		// TODO Auto-generated method stub
		return this.opacity;
	}
	public void setOpacity(int opacity) {
		// first, set opacity in this layer object
		
		this.opacity=opacity;
		
		//next, loop through the CanvasContainer array and set all paint to have the same opacity as this layer
		
		for(int i=0;i<CanvasPanel.canvasContainer.size();i++) {
			
			Paint p = CanvasPanel.canvasContainer.get(i);
			
			//loop	
			if(p.getLayer()==this) {
				
				
				p.setOpacity(this.opacity);
				
				
			}
			
			
			
			
			
		}
		
		
		
		
		
		
	}

}
