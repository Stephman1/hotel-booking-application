package group_id.hotelbooking;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rooms")
public class HotelRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer id;

    @Column(name = "room_number")
    private Integer roomNumber;

    @Column(name = "number_of_people")
    private Integer numberOfPeople;

    @Column(name = "room_rate")
    private Double roomRate;  // Assuming the room rate can be a decimal value

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "room_is_occupied")
    private Boolean isOccupied;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    
    public HotelRoom() {
    	// Default constructor
    }

	public HotelRoom(Integer roomNumber, Integer numberOfPeople, Double roomRate, String roomType, Boolean isOccupied,
			Hotel hotel) {
		super();
		this.roomNumber = roomNumber;
		this.numberOfPeople = numberOfPeople;
		this.roomRate = roomRate;
		this.roomType = roomType;
		this.isOccupied = isOccupied;
		this.hotel = hotel;
	}

	/**
	 * @return the roomNumber
	 */
	public Integer getRoomNumber() {
		return roomNumber;
	}

	/**
	 * @param roomNumber the roomNumber to set
	 */
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	/**
	 * @return the numberOfPeople
	 */
	public Integer getNumberOfPeople() {
		return numberOfPeople;
	}

	/**
	 * @param numberOfPeople the numberOfPeople to set
	 */
	public void setNumberOfPeople(Integer numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	/**
	 * @return the roomRate
	 */
	public Double getRoomRate() {
		return roomRate;
	}

	/**
	 * @param roomRate the roomRate to set
	 */
	public void setRoomRate(Double roomRate) {
		this.roomRate = roomRate;
	}

	/**
	 * @return the roomType
	 */
	public String getRoomType() {
		return roomType;
	}

	/**
	 * @param roomType the roomType to set
	 */
	public void setRoomType(String roomType) {
		this.roomType = roomType;
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
	 * @return the hotel
	 */
	public Hotel getHotel() {
		return hotel;
	}

	/**
	 * @param hotel the hotel to set
	 */
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	

}
