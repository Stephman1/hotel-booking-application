package group_id.hotelbooking;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "hotel_rooms")
@Data
public class HotelRoom {
    @Id
    private int id;

    private int room_number;
    private int number_of_People;
    private int price;
    private String room_type;
    private boolean is_occupied;
    private String hotel;
}