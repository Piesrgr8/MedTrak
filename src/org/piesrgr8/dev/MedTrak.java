package org.piesrgr8.dev;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.TrayIcon.MessageType;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.piesrgr8.dev.frames.HomeFrame;
import org.piesrgr8.dev.manager.SystemIcon;
import org.piesrgr8.dev.manager.WindowManager;

public class MedTrak extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static JFrame mainFrame;

	private static String[] columnNames = { "Date", "Time", "Pill" };
	private static Connection con;
	private static PreparedStatement pst;
	private Image img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("medtrak.png"));
	private static SystemIcon sys = new SystemIcon();

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
		
		String dbLogin = "jdbc:mysql://108.167.143.90:3306/" + "chrisdbo_MedTrak";
		try {
			con = DriverManager.getConnection(dbLogin, "chrisdbo_jared", "iwantthistowork");
		} catch (SQLException e) {
			sys.send("Communications Link Failure", "SQL Server did not respond!", MessageType.ERROR);
			e.printStackTrace();
		}
		
		try {
			new MedTrak();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static JPanel initTable() {
		JPanel panel = new JPanel(new BorderLayout());

		// TableModel tm = new TableModel();

		DefaultTableModel model = new DefaultTableModel();

		model.setColumnIdentifiers(columnNames);

		// DefaultTableModel model = new DefaultTableModel(tm.getData1(),
		// tm.getColumnNames());

		// table = new JTable(model);

		JTable table = new JTable();

		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);

		JScrollPane scroll = new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		// String textvalue = textbox.getText();

		String date = "";
		String time = "";
		String pill = "";

		try {
			pst = con.prepareStatement("SELECT * FROM pillLog");
			ResultSet rs = pst.executeQuery();

			int i = 0;
			if (rs.next()) {
				date = rs.getString("date");
				time = rs.getString("time");
				pill = rs.getString("pill");
				model.addRow(new Object[] { date, time, pill });
				i++;
			}

			if (i < 1) {
				JLabel l = new JLabel("No Records Found!", SwingConstants.CENTER);
				panel.add(l, BorderLayout.NORTH);
			}

			if (i == 1) {
				System.out.println(i + " Record Found");
			} else {
				System.out.println(i + " Records Found");
			}
		} catch (Exception ex) {
			JLabel l = new JLabel("Connection Null", SwingConstants.CENTER);
			panel.add(l, BorderLayout.NORTH);
		}
		panel.add(scroll);
		return panel;
	}
	
	public static URL getIconUrl() {
		return MedTrak.class.getResource("medtrak.png");
	}

	public static Connection getConnection() {
		return con;
	}
}
