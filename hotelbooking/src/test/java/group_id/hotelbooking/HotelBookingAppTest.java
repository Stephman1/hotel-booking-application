package group_id.hotelbooking;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

/**
 * Unit tests for hotel booking application.
 */
public class HotelBookingAppTest 
{
	
	// Mock database
	@Test
    public void testGetAllRooms() {
		Hotel hotel1 = new Hotel();
		Hotel hotel2 = new Hotel();
		Hotel hotel3 = new Hotel();
		HotelRoom room1 = new HotelRoom(101,4,380.0,"suite",false,hotel1);
		HotelRoom room2 = new HotelRoom(103,2,120.0,"standard",false,hotel2);
		HotelRoom room3 = new HotelRoom(202,5,530.0,"penthouse",false,hotel3);
		
		List<HotelRoom> rooms = new ArrayList<>();
		rooms.add(room1);
		rooms.add(room2);
		rooms.add(room3);
		
        // Create a mock object for the DatabaseService interface
        HotelRoomRepository mockDatabaseService = mock(HotelRoomRepository.class);

        // Define the behaviour of the mock object
        when(mockDatabaseService.findAll()).thenReturn(rooms);

        // Perform the test using the mock object
        HotelController hotelController = new HotelController(mockDatabaseService, true);
        List<HotelRoom> result = hotelController.getAllRooms();

        // Verify that the mock object's methods were called as expected
        verify(mockDatabaseService, times(1)).findAll();

        // Assert the result of the test
        assertEquals(rooms, result);
    }
	
	@Test
    public void testGetAvailableRoomsByRoomType() {
		Hotel hotel1 = new Hotel();
		Hotel hotel2 = new Hotel();
		Hotel hotel3 = new Hotel();
		HotelRoom room1 = new HotelRoom(101,4,380.0,"suite",false,hotel1);
		HotelRoom room2 = new HotelRoom(103,2,120.0,"standard",false,hotel2);
		HotelRoom room3 = new HotelRoom(202,5,530.0,"penthouse",false,hotel3);
		
		List<HotelRoom> rooms = new ArrayList<>();
		rooms.add(room1);
		rooms.add(room2);
		rooms.add(room3);
		
		List<HotelRoom> smallRooms = new ArrayList<>();
		smallRooms.add(room2);
		
        // Create a mock object for the DatabaseService interface
        HotelRoomRepository mockDatabaseService = mock(HotelRoomRepository.class);

        // Define the behaviour of the mock object
        when(mockDatabaseService.findByRoomTypeAndIsOccupiedFalse("standard")).thenReturn(smallRooms);

        // Perform the test using the mock object
        HotelController hotelController = new HotelController(mockDatabaseService, true);
        List<HotelRoom> result = hotelController.getAvailableRoomsByRoomType("standard");

        // Verify that the mock object's methods were called as expected
        verify(mockDatabaseService, times(1)).findByRoomTypeAndIsOccupiedFalse("standard");

        // Assert the result of the test
        assertEquals(smallRooms, result);
    }
	
	@Test
    public void testUpdateRoomOccupiedStatus() {
		Optional<HotelRoom> room = Optional.ofNullable(new HotelRoom(105,2,130.0,"standard",false,new Hotel()));
		assertFalse(room.get().getIsOccupied());
		
        // Create a mock object for the DatabaseService interface
        HotelRoomRepository mockDatabaseService = mock(HotelRoomRepository.class);

        // Define the behaviour of the mock object
        when(mockDatabaseService.findByHotelHotelNameAndRoomNumber("Luxury Palace",105)).thenReturn(room);

        // Perform the test using the mock object
        HotelController hotelController = new HotelController(mockDatabaseService, true);
        Map<String,Object> occupiedStatus = new HashMap<>();
        occupiedStatus.put("occupied", true);
        occupiedStatus.put("hotel", "Luxury Palace");
        ResponseEntity<String> result = hotelController.updateRoomOccupiedStatus(105,occupiedStatus);
        // Verify that the mock object's methods were called as expected
        verify(mockDatabaseService, times(1)).findByHotelHotelNameAndRoomNumber("Luxury Palace",105);
       
        // Assert the result of the test
        assertEquals(ResponseEntity.ok("Room 105 at the Luxury Palace has had its occupied status updated to true"), result);
        assertTrue(room.get().getIsOccupied());
    }
	
	@Test
	public void testUpdatePriceForRoomType() {
		HotelRoom room1 = new HotelRoom(101,2,110.0,"standard",false,new Hotel());
		HotelRoom room2 = new HotelRoom(202,2,110.0,"standard",false,new Hotel());
		
		List<HotelRoom> marriottRooms = new ArrayList<>();
		marriottRooms.add(room1);
		marriottRooms.add(room2);
		
		// Create a mock object for the DatabaseService interface
        HotelRoomRepository mockDatabaseService = mock(HotelRoomRepository.class);
        // Define the behaviour of the mock object
        when(mockDatabaseService.findByHotelHotelNameAndRoomType("Seaside Resort","standard")).thenReturn(marriottRooms);
        
        // Perform the test using the mock object
        HotelController hotelController = new HotelController(mockDatabaseService, true);
        Map<String,Object> rate = new HashMap<>();
        Double newRate = 130.0;
        rate.put("rate", newRate);
        rate.put("hotel", "Seaside Resort");
        ResponseEntity<String> result = hotelController.updateRateForRoomType("standard",rate);
        // Verify that the mock object's methods were called as expected
        verify(mockDatabaseService, times(1)).findByHotelHotelNameAndRoomType("Seaside Resort","standard");
        
        // Assert the result of the test
        assertEquals(ResponseEntity.ok("Room pricing updated for standard rooms at the Seaside Resort to £" + newRate + " per night"), result);
        assertEquals(room1.getRoomRate(),newRate);
        assertEquals(room2.getRoomRate(),newRate);
	}
	
}
