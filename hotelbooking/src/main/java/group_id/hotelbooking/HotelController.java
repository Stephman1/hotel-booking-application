package group_id.hotelbooking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotelbooking")
public class HotelController {
	
	private static final String URL = "jdbc:postgresql://localhost:5432/hotelrooms";
    private static final String USER = "postgres";
    private static final String PASSWORD = "password";

    @GetMapping("/availablerooms")
    public String printAvailableRoomNumbers() {
    	StringBuilder availableRooms = new StringBuilder();
    	String query = "SELECT * FROM public.hotel_rooms WHERE hotel_rooms.is_occupied = false ORDER BY hotel_rooms.hotel,hotel_rooms.room_number;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
            	availableRooms.append("Room: " + rs.getInt("room_number") + "\t");
            	availableRooms.append("Hotel: " + rs.getString("hotel") + "\t");
            	availableRooms.append("Size: " + rs.getString("room_type") + "\t");
            	availableRooms.append("Price: £" + rs.getInt("price") + "\n");
            }

        } catch (SQLException e) {
            return "SQL Error: " + e.getMessage();
        }
        return availableRooms.toString();
    }
    
    @PutMapping("/occupied/{hotel}/{roomNumber}")
    public String setRoomtoOccupied(
    		@PathVariable String hotel,
    		@PathVariable int roomNumber) {
    	String query = "UPDATE hotel_rooms SET is_occupied = TRUE WHERE room_number = ? AND hotel = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, roomNumber);
            pstmt.setString(2, hotel);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                return "Room " + roomNumber + " has been set to occupied.";
            } else {
                return "Room " + roomNumber + " was not found or is already occupied.";
            }

        } catch (SQLException e) {
            return "SQL Error: " + e.getMessage();
        }
    }
    
    @PutMapping("/unoccupied/{hotel}/{roomNumber}")
    public String setRoomtoUnoccupied(
    		@PathVariable String hotel,
    		@PathVariable int roomNumber) {
    	String query = "UPDATE hotel_rooms SET is_occupied = FALSE WHERE room_number = ? AND hotel = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, roomNumber);
            pstmt.setString(2, hotel);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                return "Room " + roomNumber + " has been set to unoccupied.";
            } else {
                return "Room " + roomNumber + " was not found or is already unoccupied.";
            }

        } catch (SQLException e) {
            return "SQL Error: " + e.getMessage();
        }
    }
}
