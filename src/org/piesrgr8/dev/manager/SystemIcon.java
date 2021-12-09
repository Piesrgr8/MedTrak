package org.piesrgr8.dev.manager;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import org.piesrgr8.dev.MedTrak;

public class SystemIcon {
	private SystemTray tray = getSystemTray();
	private TrayIcon trayIcon = new TrayIcon(getIcon(), "Tray Demo");
	
	public void systemTrayInit() throws AWTException {
		PopupMenu pm = new PopupMenu();
		MenuItem exit = new MenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		pm.add(exit);
		trayIcon.setImageAutoSize(true);
		trayIcon.setPopupMenu(pm);
		trayIcon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					try {
						new MedTrak();
					} catch (AWTException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		trayIcon.setToolTip("MedTrak");
		tray.add(trayIcon);
	}
	
	public void send(String title, String message, MessageType mt) {
		if (SystemTray.isSupported()) {
			getTrayIcon().displayMessage(title, message, mt);
		}
	}
	
	public TrayIcon getTrayIcon() {
		return trayIcon;
	}
	
	public SystemTray getSystemTray() {
		return SystemTray.getSystemTray();
	}
	
	public Image getIcon() {
		ImageIcon temp = new ImageIcon(MedTrak.getIconUrl());
		return temp.getImage();
	}
}
