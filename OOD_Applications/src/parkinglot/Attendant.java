package parkinglot;

public class Attendant {
	
	// Methods
	public boolean giveParkingSpace(Vehicle vehicle, ParkingLot parkingLot) {
		return parkingLot.parkVehicle(vehicle);
	}
	
	public boolean retrieveVehicle(String regNo, ParkingLot parkingLot) {
		return parkingLot.removeVehicle(regNo);
	}

}
