package org.piesrgr8.dev.frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.piesrgr8.dev.MedTrak;

public class MedListFrame extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static JPanel jp;
	private JButton track = new JButton("Back");

	public MedListFrame() {
		MedListFrame.jp = this;
		JPanel options = new JPanel(new FlowLayout());
		JLabel text = new JLabel("Hey wassup!");
		options.add(text);
		options.add(track);
		
		track.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MedTrak.mainFrame.getContentPane().removeAll();
				MedTrak.mainFrame.getContentPane().add(new HomeFrame().getPanel(), BorderLayout.NORTH);
				MedTrak.mainFrame.getContentPane().add(MedTrak.initTable(), BorderLayout.CENTER);
				MedTrak.mainFrame.getContentPane().revalidate();
				System.out.println("Back Button Clicked!");
			}
		});
		
		add(options);
	}

	public JPanel getPanel() {
		return jp;
	}

}
