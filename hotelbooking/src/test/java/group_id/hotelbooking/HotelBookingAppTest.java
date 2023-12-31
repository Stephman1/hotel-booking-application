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
		HotelRoom room1 = new HotelRoom(101,4,180,"medium","Marriott");
		HotelRoom room2 = new HotelRoom(103,2,120,"small","Hilton");
		HotelRoom room3 = new HotelRoom(202,5,530,"large","Savoy");
		
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
		HotelRoom room1 = new HotelRoom(101,4,180,"medium","Marriott");
		HotelRoom room2 = new HotelRoom(103,2,120,"small","Hilton");
		HotelRoom room3 = new HotelRoom(202,5,530,"large","Savoy");
		
		List<HotelRoom> rooms = new ArrayList<>();
		rooms.add(room1);
		rooms.add(room2);
		rooms.add(room3);
		
		List<HotelRoom> smallRooms = new ArrayList<>();
		smallRooms.add(room2);
		
        // Create a mock object for the DatabaseService interface
        HotelRoomRepository mockDatabaseService = mock(HotelRoomRepository.class);

        // Define the behaviour of the mock object
        when(mockDatabaseService.findByRoomTypeAndIsOccupiedFalse("small")).thenReturn(smallRooms);

        // Perform the test using the mock object
        HotelController hotelController = new HotelController(mockDatabaseService, true);
        List<HotelRoom> result = hotelController.getAvailableRoomsByRoomType("small");

        // Verify that the mock object's methods were called as expected
        verify(mockDatabaseService, times(1)).findByRoomTypeAndIsOccupiedFalse("small");

        // Assert the result of the test
        assertEquals(smallRooms, result);
    }
	
	@Test
    public void testUpdateRoomOccupiedStatus() {
		Optional<HotelRoom> room = Optional.ofNullable(new HotelRoom(105,2,130,"small","Savoy"));
		assertFalse(room.get().getIsOccupied());
		
        // Create a mock object for the DatabaseService interface
        HotelRoomRepository mockDatabaseService = mock(HotelRoomRepository.class);

        // Define the behaviour of the mock object
        when(mockDatabaseService.findFirstByRoomNumberAndHotel(105,"Savoy")).thenReturn(room);

        // Perform the test using the mock object
        HotelController hotelController = new HotelController(mockDatabaseService, true);
        Map<String,Boolean> occupiedStatus = new HashMap<>();
        occupiedStatus.put("occupied", true);
        ResponseEntity<String> result = hotelController.updateRoomOccupiedStatus("Savoy",105,occupiedStatus);
        // Verify that the mock object's methods were called as expected
        verify(mockDatabaseService, times(1)).findFirstByRoomNumberAndHotel(105,"Savoy");
       
        // Assert the result of the test
        assertEquals(ResponseEntity.ok("Room 105 at the Savoy has had its occupied status updated to true"), result);
        assertTrue(room.get().getIsOccupied());
    }
	
	@Test
	public void testUpdatePriceForRoomType() {
		HotelRoom room1 = new HotelRoom(101,2,110,"small","Marriott");
		HotelRoom room2 = new HotelRoom(202,2,110,"small","Marriott");
		
		List<HotelRoom> marriottRooms = new ArrayList<>();
		marriottRooms.add(room1);
		marriottRooms.add(room2);
		
		// Create a mock object for the DatabaseService interface
        HotelRoomRepository mockDatabaseService = mock(HotelRoomRepository.class);
        // Define the behaviour of the mock object
        when(mockDatabaseService.findByRoomTypeAndHotel("small","Marriott")).thenReturn(marriottRooms);
        
        // Perform the test using the mock object
        HotelController hotelController = new HotelController(mockDatabaseService, true);
        Map<String,Integer> price = new HashMap<>();
        Integer newPrice = 130;
        price.put("price", newPrice);
        ResponseEntity<String> result = hotelController.updatePriceForRoomType("Marriott","small",price);
        // Verify that the mock object's methods were called as expected
        verify(mockDatabaseService, times(1)).findByRoomTypeAndHotel("small","Marriott");
        
        // Assert the result of the test
        assertEquals(ResponseEntity.ok("Room pricing updated for small rooms at the Marriott to £" + newPrice + " per night"), result);
        assertEquals(room1.getPrice(),newPrice);
        assertEquals(room2.getPrice(),newPrice);
	}
	
}
