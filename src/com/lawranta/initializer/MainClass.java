package com.lawranta.initializer;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.lawranta.file.FilePathDialog;
import com.lawranta.frames.MainFrame;
import com.lawranta.globals.GLOBAL;

public class MainClass {
	public static final JFrame MAINFRAME = new MainFrame();

	public MainClass() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		// TODO Auto-generated method stub
		System.out.println("initializing..");
		UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

		GLOBAL.path = new FilePathDialog();
		MAINFRAME.setVisible(true)

		;

	}

}
