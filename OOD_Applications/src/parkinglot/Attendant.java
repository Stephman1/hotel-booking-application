package parkinglot;

public class Attendant {
	
	// Methods
	public boolean giveParkingSpace(Vehicle vehicle, ParkingLot parkingLot) {
		if (parkingLot.getIsFreeSpace()) {
			parkingLot.parkVehicle(vehicle);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean vehicleExit(String regNo, ParkingLot parkingLot) {
		return parkingLot.removeVehicle(regNo);
	}

}
