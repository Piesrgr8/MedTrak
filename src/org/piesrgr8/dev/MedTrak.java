package org.piesrgr8.dev;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.piesrgr8.dev.frames.HomeFrame;
import org.piesrgr8.dev.manager.WindowManager;

public class MedTrak extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// private static JPanel mainPanel = new JPanel();
	public static JFrame mainFrame;
	
	private static String[] columnNames = {"Date", "Time", "Pill"};
	private static Connection con;
	private static PreparedStatement pst;
	
	public MedTrak() {
		super("MedTrak");
		mainFrame = this;
		WindowManager wm = new WindowManager();
		addWindowListener(wm);
		add(new HomeFrame().getPanel(), BorderLayout.NORTH);
		//mainFrame.add(initTable(), BorderLayout.CENTER);
		setSize(800,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		String dbLogin = "jdbc:mysql://108.167.143.90:3306/" + "chrisdbo_MedTrak";
		try {
			con = DriverManager.getConnection(dbLogin, "chrisdbo_jared", "A14e09j18");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		new MedTrak();
		/*
		WindowManager wm = new WindowManager();
		mainFrame.addWindowListener(wm);
		mainFrame.add(new HomeFrame().getPanel(), BorderLayout.NORTH);
		//mainFrame.add(initTable(), BorderLayout.CENTER);
		mainFrame.setSize(800,600);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		*/
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
		scroll.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		scroll.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

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
				model.addRow(new Object[] {date, time, pill});
				i++;
			}

			if (i < 1) {
				JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
			}

			if (i == 1) {
				System.out.println(i + " Record Found");
			} else {
				System.out.println(i + " Records Found");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		panel.add(scroll);
		return panel;
	}
	
	public static Connection getConnection() {
		return con;
	}
}
