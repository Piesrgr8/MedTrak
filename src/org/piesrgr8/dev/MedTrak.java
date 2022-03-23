package org.piesrgr8.dev;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.piesrgr8.dev.frames.HomeFrame;
import org.piesrgr8.dev.manager.FileManager;
import org.piesrgr8.dev.manager.JSONManager;
import org.piesrgr8.dev.manager.SystemIcon;
import org.piesrgr8.dev.manager.WindowManager;

public class MedTrak extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static JFrame mainFrame;

	private static String[] columnNames = { "Date", "Time", "Pill" };
	private Image img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("medtrak.png"));
	private static SystemIcon sys = new SystemIcon();
	private static FileManager fm = new FileManager();
	private static JSONManager jsm = new JSONManager();

	public MedTrak() throws AWTException {
		super("MedTrak");
		MedTrak.mainFrame = this;
		WindowManager wm = new WindowManager();
		addWindowListener(wm);
		add(new HomeFrame().getPanel(), BorderLayout.NORTH);
		add(initTable(), BorderLayout.CENTER);
		setIconImage(img);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.ICONIFIED);
		setVisible(true);
	}

	public static void main(String[] args) {
		try {
			sys.systemTrayInit();
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
		
		fm.createDir();
		
		try {
			new MedTrak();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static JPanel initTable() {
		JPanel panel = new JPanel(new BorderLayout());
		
		ArrayList<String[]> elements = jsm.listTrackData();
		System.out.println(elements.size());
		Object[][] content = new Object[elements.size()][3];
		
		for (int i = 0; i < elements.size(); i++) {
			String[] arr = elements.get(i);
			for (int j = 0; j < 3; j++) {
				content[i][j] = arr[j];
			}
		}
		
		JTable table = new JTable(content, columnNames) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};

		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);

		JScrollPane scroll = new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		table.setModel(new DefaultTableModel(content, columnNames));
		
		panel.add(scroll);
		return panel;
	}
	
	public static URL getIconUrl() {
		return MedTrak.class.getResource("medtrak.png");
	}
}
