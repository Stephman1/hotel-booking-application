package group_id.hotelbooking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/available/{room_type}")
    public List<HotelRoom> getAvailableRoomNumbers(@PathVariable String room_type) {
        List<HotelRoom> rooms = hotelRoomRepository.findByRoomTypeAndIsOccupiedFalse(room_type);
        return new ArrayList<>(rooms);
    }

}
