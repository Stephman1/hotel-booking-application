package parkinglot;

public class Attendant {
	
	// Methods
	public boolean giveParkingSpace(Vehicle vehicle, ParkingLot parkingLot) {
		return parkingLot.parkVehicle(vehicle);
	}
	
	public long retrieveVehicle(String regNo, ParkingLot parkingLot) {
		long parkingTime = parkingLot.removeVehicle(regNo);
		if (parkingTime > 0) {
			String type = parkingLot.getVehicleType(regNo);
			return calculatePayment(parkingTime, type);
		}
		return -1;
	}
	
	private long calculatePayment(long time, String vehicleType) {
		// Every minute costs 50p to park if a car and 30p if a bike with a £1 entry charge.
		long payment = (time / 1000) / 60;
		if (vehicleType.equals("car")) {
			payment *= 0.5;
		}
		else if (vehicleType.equals("bike")) {
			payment *= 0.3;
		}
		payment += 1;
		return payment;
	}

}
