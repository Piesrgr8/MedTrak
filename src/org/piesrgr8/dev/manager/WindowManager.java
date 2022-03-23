package org.piesrgr8.dev.manager;

import java.awt.TrayIcon.MessageType;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowManager extends WindowAdapter {
	
	

	public void windowClosing(WindowEvent e) {
		System.out.println("Closing Window...");
	}

	public void windowActivated(WindowEvent event) {
		System.out.println("Window Activated!");
	}

	public void windowClosed(WindowEvent event) {
		System.out.println("Window Closed!");
	}

	public void windowDeactivated(WindowEvent event) {
		System.out.println("Window Deactivated!");
	}

	public void windowDeiconified(WindowEvent event) {
		System.out.println("Window Restored!");
	}

	public void windowIconified(WindowEvent event) {
		System.out.println("Window Minimized!");
		new SystemIcon().send("MedTrak is still running.", "We'll notify you.", MessageType.INFO);
	}

	public void windowOpened(WindowEvent event) {
		System.out.println("Window Opened!");
	}

}
