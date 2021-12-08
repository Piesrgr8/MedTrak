package org.piesrgr8.dev.manager;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import org.piesrgr8.dev.MedTrak;

public class WindowManager extends WindowAdapter {
	
	

	public void windowClosing(WindowEvent e) {
		System.out.println("Closing Window...");
		if (MedTrak.getConnection() != null) {
			try {
				MedTrak.getConnection().close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
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
	}

	public void windowOpened(WindowEvent event) {
		System.out.println("Window Opened!");
	}

}
