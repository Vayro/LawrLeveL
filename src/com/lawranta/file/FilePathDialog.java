package com.lawranta.file;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import com.lawranta.globals.GLOBAL;

public class FilePathDialog extends JFileChooser {

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
	public FilePathDialog() {
		setBounds(100, 100, 450, 300);
		setFileSelectionMode(JFileChooser.FILES_ONLY);

		setFileFilter(new FileFilter() {

			public java.lang.String getDescription() {
				return "Triple Authenticated Numeral File, (*.tan)";
			}

			@Override
			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				} else {
					return f.getName().toLowerCase().endsWith(GLOBAL.EXTENSION);
				}

			}

		});
	}
	
	
	
	
	
	
}
