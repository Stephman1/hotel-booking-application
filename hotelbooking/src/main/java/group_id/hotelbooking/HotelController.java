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
import java.util.Optional;

@RestController
@RequestMapping("/hotel_rooms")
public class HotelController {

    @Autowired
    private HotelRoomRepository hotelRoomRepository;


    @GetMapping
    public List<HotelRoom> getAllRooms() {
    	// Use the Spring Data JPA repository to fetch available rooms
        List<HotelRoom> rooms = hotelRoomRepository.findAll();
        return new ArrayList<>(rooms);
    }

    @GetMapping("/available")
    public List<HotelRoom> getAllAvailableRooms() {
        List<HotelRoom> rooms = hotelRoomRepository.findByIsOccupiedFalse();
        return new ArrayList<>(rooms);
    }

    @GetMapping("/available/{roomType}")
    public List<HotelRoom> getAvailableRoomNumbers(@PathVariable String roomType) {
        List<HotelRoom> rooms = hotelRoomRepository.findByRoomTypeAndIsOccupiedFalse(roomType);
        return new ArrayList<>(rooms);
    }

    /**
     Example curl:
     curl -X PUT "http://localhost:8080/hotel_rooms/occupied/Hilton/104/true" 
     **/
    @PutMapping("occupied/{hotel}/{roomNumber}/{occupied}")
    public ResponseEntity<String> updateRoomOccupiedStatus(
    		@PathVariable String hotel,
    		@PathVariable Integer roomNumber, 
    		@PathVariable Boolean occupied) {
        Optional<HotelRoom> potentialRoom = hotelRoomRepository.findFirstByRoomNumberAndHotel(roomNumber, hotel);
        if (potentialRoom.isPresent()) {
            HotelRoom room = potentialRoom.get();
            room.setIsOccupied(occupied);
            hotelRoomRepository.save(room); // Save the updated room to the database
            return ResponseEntity.ok("Room occupied status updated");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found for hotel " + hotel + " and room number " + roomNumber);
        }
    }

    /**
    Example curl:
    curl -X PUT "http://localhost:8080/hotel_rooms/price/Hilton/small/120"
    **/
    @PutMapping("price/{hotel}/{roomType}/{price}")
    public ResponseEntity<String> updatePriceForRoomType(
    		@PathVariable String hotel,
    		@PathVariable String roomType,
    		@PathVariable Integer price) {

        List<HotelRoom> roomsToUpdate = hotelRoomRepository.findByRoomTypeAndHotel(roomType, hotel);

        for (HotelRoom room : roomsToUpdate) {
            room.setPrice(price);
            hotelRoomRepository.save(room);
        }
        return ResponseEntity.ok("Room pricing updated");
    }

}
