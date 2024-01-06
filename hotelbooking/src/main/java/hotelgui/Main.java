package hotelgui;

import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				HotelGUI frame = new HotelGUI("http://localhost:8080/hotel_rooms");
	            frame.setVisible(true);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
		});
	}

}
