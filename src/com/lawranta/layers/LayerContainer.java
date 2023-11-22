/**
 * 
 */
package com.lawranta.layers;

import java.util.ArrayList;

/**
 * This class contains all the layers of the program in an arraylist. It is
 * SINGLETON , there should only be ONE instance of LayerContainer
 */
public class LayerContainer {

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

	public static int getLayerArraySize() {
		return LayerArray.size();

	}

	public static ArrayList<Layer> getLayerContainer() {
		return LayerArray;

	}

	public static void newLayer() {

		int id = LayerArray.size();

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

}
