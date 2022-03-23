package org.piesrgr8.dev.frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.piesrgr8.dev.MedTrak;
import org.piesrgr8.dev.manager.JSONManager;

public class TrackFrame extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JSONManager jsm = new JSONManager();
	
	private static JPanel jp;
	private String[] pills = jsm.listPillData();
	private JComboBox<String> pillList = new JComboBox<String>(pills);
	private JButton submit = new JButton("Submit");
	private JButton back = new JButton("Back");
	
	public TrackFrame() {
		TrackFrame.jp = this;
		JPanel tracking = new JPanel(new FlowLayout());
		tracking.add(submit);
		tracking.add(back);
		tracking.add(pillList);
		
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addTracking(pillList.getSelectedItem().toString());
				goBack();
				System.out.println("Submit Button Clicked! (" + pillList.getSelectedItem().toString() + ")");
			}
		});
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goBack();
				System.out.println("Back Button Clicked!");
			}
		});
		
		add(tracking);
		add(createTable());
	}
	
	private void addTracking(String pillName) {
		jsm.createTrackData(pillName);
	}
	
	private JPanel createTable() {
		JPanel panel = new JPanel(new BorderLayout());
		
		ArrayList<String[]> elements = jsm.readAllPillData();
		Object[][] content = new Object[elements.size()][3];
		
		for (int i = 0; i < elements.size(); i++) {
			String[] arr = elements.get(i);
			for (int j = 0; j < 3; j++) {
				content[i][j] = arr[j];
			}
		}
		
		String[] columnNames = {"Name", "Grams", "Time"};
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
		table.setModel(new DefaultTableModel(content, columnNames));
		
		JScrollPane scroll = new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		panel.add(scroll);
		
		return panel;
	}
	
	private void goBack() {
		MedTrak.mainFrame.getContentPane().removeAll();
		MedTrak.mainFrame.getContentPane().add(new HomeFrame().getPanel(), BorderLayout.NORTH);
		MedTrak.mainFrame.getContentPane().add(MedTrak.initTable(), BorderLayout.CENTER);
		MedTrak.mainFrame.getContentPane().revalidate();
	}

	public JPanel getPanel() {
		return jp;
	}
}
