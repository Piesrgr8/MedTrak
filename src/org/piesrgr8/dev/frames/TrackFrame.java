package org.piesrgr8.dev.frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.piesrgr8.dev.MedTrak;

public class TrackFrame extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static JPanel jp;
	private String[] pills = {"Hydroxyzine", "Lexapro"};
	private JComboBox<String> pillList = new JComboBox<String>(pills);
	private JButton submit = new JButton("Submit");
	
	public TrackFrame() {
		TrackFrame.jp = this;
		JPanel tracking = new JPanel(new FlowLayout());
		tracking.add(submit);
		tracking.add(pillList);
		
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MedTrak.mainFrame.getContentPane().removeAll();
				MedTrak.mainFrame.getContentPane().add(new HomeFrame().getPanel(), BorderLayout.NORTH);
				MedTrak.mainFrame.getContentPane().add(MedTrak.initTable(), BorderLayout.CENTER);
				MedTrak.mainFrame.getContentPane().revalidate();
				System.out.println("Submit Button Clicked!");
			}
		});
		
		add(tracking);
	}

	public JPanel getPanel() {
		return jp;
	}
}
