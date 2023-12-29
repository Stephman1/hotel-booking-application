package group_id.hotelbooking;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "hotel_rooms")
public class HotelRoom {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "room_number")
    private Integer roomNumber;

    @Column(name = "number_of_people")
    private Integer numberOfPeople;
    
    @Column(name = "price")
    private Integer price;
    
    @Column(name = "room_type")
    private String roomType;

    @Column(name = "is_occupied")
    private Boolean isOccupied;
    
    @Column(name = "hotel")
    private String hotel;
    
    public HotelRoom() {
    	// Default constructor
    }
    
    public HotelRoom(Integer roomNumber, Integer numOfPeople, Integer price, String roomType, String hotel) {
    	this.roomNumber = roomNumber;
    	this.numberOfPeople = numOfPeople;
    	this.price = price;
    	this.roomType = roomType;
    	this.isOccupied = false;
    	this.hotel = hotel;
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
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HotelRoom otherRoom = (HotelRoom) obj;
        return Objects.equals(roomNumber, otherRoom.roomNumber) &&
               Objects.equals(numberOfPeople, otherRoom.numberOfPeople) &&
               Objects.equals(price, otherRoom.price) &&
               Objects.equals(roomType, otherRoom.roomType) &&
               Objects.equals(hotel, otherRoom.hotel);
    }

	/**
	 * @return the price
	 */
	public Integer getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}

	/**
	 * @return the isOccupied
	 */
	public Boolean getIsOccupied() {
		return isOccupied;
	}

	/**
	 * @param isOccupied the isOccupied to set
	 */
	public void setIsOccupied(Boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	/**
	 * @return the roomNumber
	 */
	public Integer getRoomNumber() {
		return roomNumber;
	}

	/**
	 * @return the numberOfPeople
	 */
	public Integer getNumberOfPeople() {
		return numberOfPeople;
	}

	/**
	 * @return the roomType
	 */
	public String getRoomType() {
		return roomType;
	}

	/**
	 * @return the hotel
	 */
	public String getHotel() {
		return hotel;
	}

}
