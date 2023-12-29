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

    @Autowired
    private HotelRoomRepository hotelRoomRepository;

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
     curl -X GET "http://localhost:8080/hotel_rooms/available/small"
     */
    @GetMapping("/available/{roomType}")
    public List<HotelRoom> getAvailableRoomNumbers(@PathVariable String roomType) {
        List<HotelRoom> rooms = hotelRoomRepository.findByRoomTypeAndIsOccupiedFalse(roomType);
        return new ArrayList<>(rooms);
    }

    /**
     Example curl:
     curl -X PUT -H "Content-Type: application/json" -d '{"occupied": true}' "http://localhost:8080/hotel_rooms/occupied/Hilton/104" 
     */
    @PutMapping("occupied/{hotel}/{roomNumber}")
    public ResponseEntity<String> updateRoomOccupiedStatus(
    		@PathVariable String hotel,
    		@PathVariable Integer roomNumber, 
    		@RequestBody Map<String, Boolean> occupied) {
        Optional<HotelRoom> potentialRoom = hotelRoomRepository.findFirstByRoomNumberAndHotel(roomNumber, hotel);
        if (potentialRoom.isPresent()) {
            HotelRoom room = potentialRoom.get();
            Boolean isOccupied = occupied.get("occupied");
            room.setIsOccupied(isOccupied);
            hotelRoomRepository.save(room); // Save the updated room to the database
            return ResponseEntity.ok("Room " + roomNumber + " at the " + hotel + " has had its occupied status updated to " + isOccupied);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found for hotel " + hotel + " and room number " + roomNumber);
        }
    }

    /**
    Example curl:
    curl -X PUT -H "Content-Type: application/json" -d '{"price": 120}'  "http://localhost:8080/hotel_rooms/price/Hilton/small"
    */
    @PutMapping("price/{hotel}/{roomType}")
    public ResponseEntity<String> updatePriceForRoomType(
    		@PathVariable String hotel,
    		@PathVariable String roomType,
    		@RequestBody Map<String, Integer> price) {

        List<HotelRoom> roomsToUpdate = hotelRoomRepository.findByRoomTypeAndHotel(roomType, hotel);
        Integer newPrice = price.get("price");
        
        for (HotelRoom room : roomsToUpdate) {
            room.setPrice(newPrice);
            hotelRoomRepository.save(room);
        }
        return ResponseEntity.ok("Room pricing updated for " + roomType + " rooms at the " + hotel + " to £" + newPrice + " per night");
    }

}
