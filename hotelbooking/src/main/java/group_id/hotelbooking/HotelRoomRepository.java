package group_id.hotelbooking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HotelRoomRepository extends JpaRepository<HotelRoom, Integer> {
    List<HotelRoom> findByIsOccupiedFalse();
    List<HotelRoom> findByRoomTypeAndIsOccupiedFalse(String roomType);
    Optional<HotelRoom> findByHotelHotelNameAndRoomNumber(String hotel, Integer roomNumber);
    List<HotelRoom> findByHotelHotelNameAndRoomType(String hotel, String roomType);
    
}
