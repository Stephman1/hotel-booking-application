package group_id.hotelbooking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HotelRoomRepository extends JpaRepository<HotelRoom, Integer> {
    List<HotelRoom> findByIsOccupiedFalse();
    List<HotelRoom> findByRoomTypeAndIsOccupiedFalse(String roomType);
    Optional<HotelRoom> findFirstByRoomNumberAndHotel(Integer roomNumber, String hotel);
    List<HotelRoom> findByRoomTypeAndHotel(String roomType, String hotel);
    
}
