package com.lawranta.initializer;

import javax.swing.UIManager;

import com.lawranta.edit.Debug;
import com.lawranta.file.*;
import com.lawranta.frames.MainFrame;
import com.lawranta.globals.GLOBAL;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainClass {
	private static boolean run = true;

	public MainClass() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		UIManager.put("Menu.foreground", GLOBAL.lightColor);
		UIManager.put("MenuItem.background", GLOBAL.darkColor);
		UIManager.put("MenuItem.foreground", GLOBAL.lightColor);
		UIManager.put("MenuItem.opaque", true);

		System.out.println("initializing..");

		GLOBAL.fileInfo = new FileInfo();

		GLOBAL.MAINFRAME = new MainFrame();
		GLOBAL.DEBUGFRAME = new Debug();
		GLOBAL.DEBUGFRAME.setVisible(false);

		GLOBAL.filePathDialog = new FilePathDialog();
		GLOBAL.exportPathDialog = new ExportPathDialog();
		GLOBAL.MAINFRAME.setVisible(true);
		

		
	}
	
	
}
