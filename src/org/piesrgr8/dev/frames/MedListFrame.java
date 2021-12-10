package org.piesrgr8.dev.frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.piesrgr8.dev.MedTrak;
import org.piesrgr8.dev.manager.JSONManager;
import org.piesrgr8.dev.manager.SpringUtilities;

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

	public MedListFrame() {
		MedListFrame.jp = this;
		JPanel input = new JPanel(new SpringLayout());
		JPanel options = new JPanel();
		JPanel results = new JPanel();
		
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
		 
		 results.setLayout(new BorderLayout());
		 jsm.readAllPillData();
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MedTrak.mainFrame.getContentPane().removeAll();
				MedTrak.mainFrame.getContentPane().add(new HomeFrame().getPanel(), BorderLayout.NORTH);
				MedTrak.mainFrame.getContentPane().add(MedTrak.initTable(), BorderLayout.CENTER);
				MedTrak.mainFrame.getContentPane().revalidate();
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
			}
		});
		
		add(input);
		add(options);
		add(results);
	}

	public JPanel getPanel() {
		return jp;
	}

}
