package group_id.hotelbooking;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for hotel booking application.
 */
public class HotelBookingAppTest 
{
	
	// Mock database
	@Test
    public void testDatabaseInteractionAllAvailable() {
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
    public void testDatabaseInteractionAllAvailableSize() {
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
}
