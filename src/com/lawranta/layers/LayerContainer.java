/**
 * 
 */
package com.lawranta.layers;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JDialog;

import com.lawranta.canvas.Paint;
import com.lawranta.panels.CanvasPanel;
import com.lawranta.popups.ConfirmDialog;

/**
 * This class contains all the layers of the program in an arraylist. It is
 * SINGLETON , there should only be ONE instance of LayerContainer
 */
public class LayerContainer implements Serializable  {

	public static Layer activeLayer;
	static ArrayList<Layer> LayerArray;

	public static void initialize() {

		Layer l = new Layer();
		l.setLayerID(0);
		l.setLayerName("Layer " + l.getLayerID());
		System.out.println("Layer created: ");
		System.out.println(l.toString());
		LayerArray = new ArrayList<Layer>();
		LayerArray.add(l);
		activeLayer = l;
		l.setActive(true);

	}

	/**
	 * @return the activeLayer
	 */
	public static Layer getActiveLayer() {
		return activeLayer;
	}

	/**
	 * @param activeLayer the activeLayer to set
	 */
	public static void setActiveLayer(Layer activeLayer) {
		LayerContainer.activeLayer = activeLayer;
	}

	public static int getLayerArraySize() {
		return LayerArray.size();

	}

	public static ArrayList<Layer> getLayerContainer() {
		return LayerArray;

	}

	public static void newLayer() {

		int id = LayerArray.size() - 1;

		for (int i = 0; i < LayerArray.size(); i++) {

			if (LayerArray.get(i).getLayerID() == 0) {
				id++;
				i = 0;

			}
		}

		Layer l = new Layer();
		l.setLayerID(id);
		l.setLayerName("Layer " + l.getLayerID());
		System.out.println("Layer created: ");
		System.out.println(l.toString());
		LayerArray.add(l);
		setAllInactive();
		activeLayer = l;
		l.setActive(true);

	}

	private static void setAllInactive() {

		for (Layer i : LayerArray) {
			i.setActive(false);

		}

	}

	public static void setActive(Layer layer) {
		// TODO Auto-generated method stub
		setAllInactive();
		activeLayer = layer;
		layer.setActive(true);
	}

	public static void toggleVisibility(Layer layer) {
		// Toggle visibility of each Paint in the layer

		// first, toggle visibility of layer
		if (layer.isVisible()) {
			layer.setVisible(false);
		} else {
			layer.setVisible(true);

		}

		// now for each Paint, set the visibility
		for (int i = 0; i < CanvasPanel.canvasContainer.size(); i++) {
			Paint p = CanvasPanel.canvasContainer.get(i);

			if (p.getLayer() == layer) {
				// if layer is the same as active layer, toggle visibility
				p.toggleVisibility(layer.isVisible());

			}

		}

	}

	public static void clearLayer(Layer layer) {
		// TODO Auto-generated method stub

		// clear code goes here
		{

			for (int i = 0; i < CanvasPanel.canvasContainer.size(); i++) {
				// loop through container and delete all paint in each later
				Paint p = CanvasPanel.canvasContainer.get(i);
				if (p.getLayer() == layer) {

					p.destroy(false);

				}

			}

		}

	}

	public static void deleteLayer(Layer layer) {
		/* TODO check if the layer to be deleted is active, 
		**if so then set the root later as active in preparation for layer deletion*/
		if(layer.isActive()) {
			LayerArray.get(0).setActive(true);
		}
		
		
		
		for(int i=0;i<LayerArray.size();i++) {
			//loop through the list of layers and delete the one that has the same layer ID 
			if(LayerArray.get(i).getLayerID()==layer.getLayerID()) {
				
				LayerArray.remove(i);
				layer=null;
			}
			
			
		}
		
		
	}

	public static Object getLayerArray() {
		// TODO Auto-generated method stub
		return LayerArray;
	}

	public static void setArrayFromFile(Object object) {
		// TODO Auto-generated method stubs
		LayerArray = (ArrayList<Layer>) object;
	}
}
