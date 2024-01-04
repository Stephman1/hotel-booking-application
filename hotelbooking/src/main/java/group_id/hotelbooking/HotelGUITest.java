package group_id.hotelbooking;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HotelGUITest implements ActionListener {
	
	private int count = 0;
	private JFrame frame;
	private JPanel panel;
	private JLabel viewAllLabel;
	
	public HotelGUITest() {
		frame = new JFrame();
		
		JButton viewAllButton = new JButton("Click to view all hotel rooms");
		viewAllLabel = new JLabel("Number of clicks: 0");
		
		viewAllButton.addActionListener(this);
		
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		panel.setLayout(new GridLayout(0, 1));
		panel.add(viewAllButton);
		panel.add(viewAllLabel);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Hotel Booking Application");
		frame.pack();
		frame.setVisible(true);
		
	}

	public static void main(String[] args) {
		new HotelGUITest();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		count += 1;
		viewAllLabel.setText("Number of clicks: " + count);
	}

}
