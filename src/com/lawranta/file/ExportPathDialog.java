package com.lawranta.file;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class ExportPathDialog extends JFileChooser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	private final JFileChooser contentPanel = new JFileChooser();
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public ExportPathDialog() {
		setBounds(100, 100, 450, 300);
		setFileSelectionMode(JFileChooser.FILES_ONLY);

		setFileFilter(new FileFilter() {

			public java.lang.String getDescription() {
				return "Portable Network Graphic, (*.png)";
			}

			@Override
			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				} else {
					return f.getName().toLowerCase().endsWith(".png");
				}

			}

		});
	}
	
	
	
	
	
	
}
