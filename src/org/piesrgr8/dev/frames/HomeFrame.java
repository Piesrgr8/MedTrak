package org.piesrgr8.dev.frames;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HomeFrame extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static JPanel jp = new JPanel(new FlowLayout());

	public HomeFrame() {
		JPanel options = new JPanel(new FlowLayout());
		JButton track = new JButton("Track");
		JButton mList = new JButton("Med List");
		options.add(track);
		options.add(mList);
		jp.add(options);
	}

	public JPanel getPanel() {
		return jp;
	}
}
