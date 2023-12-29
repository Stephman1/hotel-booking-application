package group_id.hotelbooking;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "hotel_rooms")
@Data
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
public class HotelRoom {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@Column(name = "room_number")
    private Integer roomNumber;

    //@Column(name = "number_of_people")
    private Integer numberOfPeople;
    
    //@Column(name = "price")
    private Integer price;
    
    //@Column(name = "room_type")
    private String roomType;

    //@Column(name = "is_occupied")
    private Boolean isOccupied;
    
    //@Column(name = "hotel")
    private String hotel;
    
    /**
    public HotelRoom() {
    	// Default constructor
    }
    
    
    // Methods
    
    @Override
    public String toString() {
        return "HotelRoom{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", numberOfPeople=" + numberOfPeople +
                ", price=" + price +
                ", roomType='" + roomType + '\'' +
                ", isOccupied=" + isOccupied +
                ", hotel='" + hotel + '\'' +
                '}';
    }
	
	public Integer getPrice() {
		return price;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public String getHotel() {
		return hotel;
	}
	
	public String getRoomType() {
		return roomType;
	}
	
	public void setIsOccupied(Boolean occupied) {
		isOccupied = occupied;
	}
	
	public void setPrice(Integer newPrice) {
		price = newPrice;
	}
	*/

}