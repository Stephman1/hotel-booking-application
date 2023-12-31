package group_id.hotelbooking;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/hotel_rooms")
public class HotelController {

	private final HotelRoomRepository hotelRoomRepository;

    // Constructor for normal usage
    @Autowired
    public HotelController(HotelRoomRepository hotelRoomRepository) {
        this.hotelRoomRepository = hotelRoomRepository;
    }

    // Constructor for testing with a mock object
    public HotelController(HotelRoomRepository hotelRoomRepository, boolean isTest) {
        this.hotelRoomRepository = hotelRoomRepository;
    }

    /**
    Example curl:
    curl -X GET "http://localhost:8080/hotel_rooms"
    */
    @GetMapping
    public List<HotelRoom> getAllRooms() {
    	// Use the Spring Data JPA repository to fetch available rooms
        List<HotelRoom> rooms = hotelRoomRepository.findAll();
        return new ArrayList<>(rooms);
    }
    
    /**
    Example curl:
    curl -X GET "http://localhost:8080/hotel_rooms/available"
    */
    @GetMapping("/available")
    public List<HotelRoom> getAllAvailableRooms() {
        List<HotelRoom> rooms = hotelRoomRepository.findByIsOccupiedFalse();
        return new ArrayList<>(rooms);
    }
    
    /**
     Example curl:
     curl -X GET "http://localhost:8080/hotel_rooms/available/standard"
     */
    @GetMapping("/available/{roomType}")
    public List<HotelRoom> getAvailableRoomsByRoomType(@PathVariable String roomType) {
        List<HotelRoom> rooms = hotelRoomRepository.findByRoomTypeAndIsOccupiedFalse(roomType);
        return new ArrayList<>(rooms);
    }

    /**
     Example curl:
     curl -X PUT -H "Content-Type: application/json" -d '{"hotel": "InterContinental", "occupied": true}' "http://localhost:8080/hotel_rooms/occupied/203" 
     */
    @PutMapping("/occupied/{roomNumber}")
    public ResponseEntity<String> updateRoomOccupiedStatus(
    		@PathVariable Integer roomNumber, 
    		@RequestBody Map<String, Object> occupied) {
    	String hotel = (String) occupied.get("hotel");
    	Boolean isOccupied = (Boolean) occupied.get("occupied");
        Optional<HotelRoom> potentialRoom = hotelRoomRepository.findByHotelHotelNameAndRoomNumber(hotel,roomNumber);
        if (potentialRoom.isPresent()) {
            HotelRoom room = potentialRoom.get();
            room.setIsOccupied(isOccupied);
            hotelRoomRepository.save(room); // Save the updated room to the database
            return ResponseEntity.ok("Room " + roomNumber + " at the " + hotel + " has had its occupied status updated to " + isOccupied);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found for hotel " + hotel + " and room number " + roomNumber);
        }
    }

    /**
    Example curl:
    curl -X PUT -H "Content-Type: application/json" -d '{"hotel": "Waldorf Astoria", "rate": 120.00}'  "http://localhost:8080/hotel_rooms/rate/standard"
    */
    @PutMapping("/rate/{roomType}")
    public ResponseEntity<String> updateRateForRoomType(
    		@PathVariable String roomType,
    		@RequestBody Map<String, Object> roomRate) {
    	String hotel = (String) roomRate.get("hotel");
    	Double newRate = (Double) roomRate.get("rate");
        List<HotelRoom> roomsToUpdate = hotelRoomRepository.findByHotelHotelNameAndRoomType(hotel,roomType);
        for (HotelRoom room : roomsToUpdate) {
            room.setRoomRate(newRate);
            hotelRoomRepository.save(room);
        }
        return ResponseEntity.ok("Room pricing updated for " + roomType + " rooms at the " + hotel + " to £" + newRate + " per night");
    }

}
