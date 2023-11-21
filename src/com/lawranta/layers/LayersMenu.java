package com.lawranta.layers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.lawranta.frames.internal.Menu;

public class LayersMenu extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6650752107365927678L;

	/**
	 * Create the panel.
	 */
	public LayersMenu() {
		
		
		setText("Layers");
		for(int i=0; i<LayerContainer.getLayerArraySize();i++) {
			
		Layer layer = LayerContainer.getLayerContainer().get(i);
		JMenuItem mntmNewMenuItem = null;
		
		if(layer.isActive()) {
		 mntmNewMenuItem = new JMenuItem(layer.getLayerName() + " (active)");
		
		}else {
			 mntmNewMenuItem = new JMenuItem(layer.getLayerName());
			
		}
		
		
		
		add(mntmNewMenuItem);
		
		}
		
		addSeparator();
		
		JMenuItem newMenuItem = new JMenuItem("New Layer");
		add(newMenuItem);
		
		newMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LayerContainer.newLayer();
				Menu.refreshLayerMenu();
				
			}
			
			
			
			
		});
		
		
		
		
		
		
		
		JMenuItem deleteMenuItem = new JMenuItem("Delete Layer");
		add(deleteMenuItem);
		
		JMenuItem clearMenuItem = new JMenuItem("Clear Layer");
		add(clearMenuItem);
		
		
	}

}
