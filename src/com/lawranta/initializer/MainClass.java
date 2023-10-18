package com.lawranta.initializer;

import javax.swing.UIManager;

import com.lawranta.edit.Debug;
import com.lawranta.file.*;
import com.lawranta.frames.MainFrame;
import com.lawranta.globals.GLOBAL;

import java.util.Map.Entry;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

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
		
	
		
		
		// Might throw a UnsatisfiedLinkError if the native library fails to load or a RuntimeException if hooking fails 
				GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(false); // Use false here to switch to hook instead of raw input

				System.out.println("Global keyboard hook successfully started, press [escape] key to shutdown. Connected keyboards:");
				
				for (Entry<Long, String> keyboard : GlobalKeyboardHook.listKeyboards().entrySet()) {
					System.out.format("%d: %s\n", keyboard.getKey(), keyboard.getValue());
				}
				
				keyboardHook.addKeyListener(new GlobalKeyAdapter() {
				
					@Override 
					public void keyPressed(GlobalKeyEvent event) {
						System.out.println(event);
						if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_ESCAPE) {
							run = false;
						}
						if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_SHIFT) {
							System.out.println("Penis");
						}
						
						
					}
					
					@Override 
					public void keyReleased(GlobalKeyEvent event) {
						System.out.println(event); 
					}
				});
				
				try {
					while(run) { 
						Thread.sleep(128); 
					}
				} catch(InterruptedException e) { 
					//Do nothing
				} finally {
					keyboardHook.shutdownHook(); 
				}

	}

}
