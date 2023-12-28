package group_id.hotelbooking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRoomRepository extends JpaRepository<HotelRoom, String> {
	List<HotelRoom> findAll();  
    List<HotelRoom> findByIsOccupiedFalse();
    List<HotelRoom> findByRoomTypeAndIsOccupiedFalse(String roomType);
    
}
