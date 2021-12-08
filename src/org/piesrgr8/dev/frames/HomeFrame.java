package org.piesrgr8.dev.frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.piesrgr8.dev.MedTrak;

public class HomeFrame extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static JPanel jp;
	private JButton track = new JButton("Track");
	private JButton mList = new JButton("Med List");

	public HomeFrame() {
		HomeFrame.jp = this;
		JPanel options = new JPanel(new FlowLayout());
		options.add(track);
		options.add(mList);
		
		track.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MedTrak.mainFrame.getContentPane().removeAll();
				MedTrak.mainFrame.getContentPane().add(new TrackFrame().getPanel(), BorderLayout.CENTER);
				MedTrak.mainFrame.getContentPane().revalidate();
				System.out.println("Track Button Clicked!");
			}
		});
		
		mList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("MedList Button Clicked!");
			}
		});
		
		add(options);
	}

	public JPanel getPanel() {
		return jp;
	}
}
