package hotelgui;

import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				LoginGUI loginFrame = new LoginGUI();
				// Register a callback for login event
				loginFrame.addLoginListener(email -> {
				    HotelGUI appFrame = new HotelGUI("http://localhost:8080/hotel_rooms");
				    appFrame.setVisible(true);
				    System.out.println(email);
				});
				loginFrame.setVisible(true);
				
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
		});
	}

}
