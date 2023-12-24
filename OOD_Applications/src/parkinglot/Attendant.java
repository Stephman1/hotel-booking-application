package parkinglot;


public class Attendant {
	
	// Methods
	public boolean giveParkingSpace(Vehicle vehicle, ParkingLot parkingLot) {
		return parkingLot.parkVehicle(vehicle);
	}
	
	public double retrieveVehicle(String regNum, ParkingLot parkingLot) {
		String type = parkingLot.getVehicleType(regNum);
		long parkingTime = parkingLot.removeVehicle(regNum);
		if (parkingTime > 0) {
			return calculatePayment(parkingTime, type);
		}
		return -1;
	}
	
	private double calculatePayment(long time, String vehicleType) {
		// Every minute costs 50p to park if a car and 30p if a bike with a £1 entry charge.
		int minutes = (int) (time / (1000 * 60));
		System.out.println("Your vehicle has been parked for " + minutes + " minutes.");
		double payment = 1.0;
		double minutesCharged;
		if (vehicleType.equals("car")) {
			minutesCharged = minutes * 0.50;
			payment += minutesCharged;
		}
		else if (vehicleType.equals("bike")) {
			minutesCharged = minutes * 0.30;
			payment += minutesCharged;
		}
		return payment;
	}

}
