package group_id.hotelbooking;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Integer id;

    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "num_rooms")
    private Integer numRooms;

    @Column(name = "location_city")
    private String locationCity;

    @Column(name = "location_state")
    private String locationState;

    @Column(name = "location_country")
    private String locationCountry;

    @Column(name = "hotel_owner")
    private String hotelOwner;
    
    public Hotel() {
    	// Default constructor
    }

	public Hotel(String hotelName, Integer numRooms, String locationCity, String locationState, String locationCountry,
			String hotelOwner) {
		super();
		this.hotelName = hotelName;
		this.numRooms = numRooms;
		this.locationCity = locationCity;
		this.locationState = locationState;
		this.locationCountry = locationCountry;
		this.hotelOwner = hotelOwner;
	}

	/**
	 * @return the hotelName
	 */
	public String getHotelName() {
		return hotelName;
	}

	/**
	 * @param hotelName the hotelName to set
	 */
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	/**
	 * @return the numRooms
	 */
	public Integer getNumRooms() {
		return numRooms;
	}

	/**
	 * @param numRooms the numRooms to set
	 */
	public void setNumRooms(Integer numRooms) {
		this.numRooms = numRooms;
	}

	/**
	 * @return the locationCity
	 */
	public String getLocationCity() {
		return locationCity;
	}

	/**
	 * @param locationCity the locationCity to set
	 */
	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}

	/**
	 * @return the locationState
	 */
	public String getLocationState() {
		return locationState;
	}

	/**
	 * @param locationState the locationState to set
	 */
	public void setLocationState(String locationState) {
		this.locationState = locationState;
	}

	/**
	 * @return the locationCountry
	 */
	public String getLocationCountry() {
		return locationCountry;
	}

	/**
	 * @param locationCountry the locationCountry to set
	 */
	public void setLocationCountry(String locationCountry) {
		this.locationCountry = locationCountry;
	}

	/**
	 * @return the hotelOwner
	 */
	public String getHotelOwner() {
		return hotelOwner;
	}

	/**
	 * @param hotelOwner the hotelOwner to set
	 */
	public void setHotelOwner(String hotelOwner) {
		this.hotelOwner = hotelOwner;
	}
    
}
