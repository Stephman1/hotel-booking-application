package parkinglot;

public class ParkingSpace {
	
	// Fields
	private String spaceType;
	private boolean isFree = true;
	private Vehicle vehicle = null;
	
	// Constructor
	public ParkingSpace(String type) {
		this.spaceType = type;
	}
	
	// Methods
	public boolean checkIsFree() {
		return isFree;
	}
	
	public boolean parkCar(Vehicle vehicle) {
		if (checkIsFree()) {
			this.vehicle = vehicle;
			isFree = false;
			return true;
		}
		else {
			return false;
		}
	}
	
	public String getSpaceType() {
		return spaceType;
	}
	
	public String getVehicleRegNo() {
		if (checkIsFree()) {
			return null;
		}
		else {
			return vehicle.getRegNo();
		}
	}
	
}
