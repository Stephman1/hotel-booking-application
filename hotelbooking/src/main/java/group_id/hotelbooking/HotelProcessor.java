package group_id.hotelbooking;

import java.sql.*;

public class HotelProcessor {
    private static final String URL = "jdbc:postgresql://localhost:5432/hotelrooms";
    private static final String USER = "postgres";
    private static final String PASSWORD = "password";

    public static void printAvailableRoomNumbers() {
        String query = "SELECT * FROM public.hotel_rooms WHERE hotel_rooms.is_occupied = false ORDER BY hotel_rooms.hotel,hotel_rooms.room_number;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                System.out.print("Room: " + rs.getInt("room_number") + "\t");
                System.out.print("Hotel: " + rs.getString("hotel") + "\t");
                System.out.print("Size: " + rs.getString("room_type") + "\t");
                System.out.print("Price: £" + rs.getInt("price") + "\n");
            }


        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }

    }

    public static void setRoomtoOccupied(int roomNumber) {
        String query = "UPDATE hotel_rooms SET is_occupied = TRUE WHERE room_number = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, roomNumber);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Room " + roomNumber + " has been set to occupied.");
            } else {
                System.out.println("Room " + roomNumber + " was not found or is already occupied.");
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }

    }

    public static void setRoomtoUnoccupied(int roomNumber) {
        String query = "UPDATE hotel_rooms SET is_occupied = FALSE WHERE room_number = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, roomNumber);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Room " + roomNumber + " has been set to occupied.");
            } else {
                System.out.println("Room " + roomNumber + " was not found or is already occupied.");
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }

    }
}
