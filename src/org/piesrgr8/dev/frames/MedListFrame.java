package org.piesrgr8.dev.frames;

import java.awt.BorderLayout;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import org.piesrgr8.dev.MedTrak;
import org.piesrgr8.dev.manager.JSONManager;
import org.piesrgr8.dev.manager.SpringUtilities;
import org.piesrgr8.dev.manager.SystemIcon;

public class MedListFrame extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static JPanel jp;
	private JButton back = new JButton("Back");
	private JButton submit = new JButton("Submit");
	private final String[] labels = {"Name: ", "Grams: ", "Time: "};
	private final JTextField[] fields = new JTextField[labels.length];
	private JSONManager jsm = new JSONManager();
	private static SystemIcon sys = new SystemIcon();

	public MedListFrame() {
		MedListFrame.jp = this;
		JPanel input = new JPanel(new SpringLayout());
		JPanel options = new JPanel();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		for(int i = 0; i < labels.length; i++) {
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			input.add(l);
			
			fields[i] = new JTextField(10);
			l.setLabelFor(fields[i]);
			input.add(fields[i]);
		}
		
		options.setLayout(new BoxLayout(options, BoxLayout.LINE_AXIS));
		
		options.add(submit);
		options.add(back);
		
		 SpringUtilities.makeCompactGrid(input,
                 labels.length, 2, //rows, cols
                 7, 7,        //initX, initY
                 7, 7);       //xPad, yPad
		 
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goBack();
				System.out.println("Back Button Clicked!");
			}
		});
		
		submit.addActionListener(new ActionListener() {
			String[] args = new String[3];
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < labels.length; i++) {
					args[i] = fields[i].getText();
				}
				jsm.createPillData(args[0], args[1], args[2]);
				goBack();
				System.out.println("Submit Button Clicked!");
				sys.send("Success!", "I know what " + args[0] + " is!", MessageType.INFO);
			}
		});
		
		add(input);
		add(options);
		add(createTable());
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
