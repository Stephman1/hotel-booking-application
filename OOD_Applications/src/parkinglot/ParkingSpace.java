package parkinglot;

public class ParkingSpace {
	
	// Fields
	private String spaceType;
	private boolean isFree = true;
	private Vehicle vehicle;
	private Integer parkingSpaceNum;
	
	// Constructor
	public ParkingSpace(String type, Integer parkingSpaceNum) {
		this.spaceType = type;
		this.parkingSpaceNum = parkingSpaceNum;
	}
	
	// Methods
	public boolean checkIsFree() {
		return isFree;
	}
	
	public void setIsFree(boolean free) {
		isFree = free;
	}
	
	public boolean parkVehicle(Vehicle vehicle, Integer parkingSpaceNum) {
		if (checkIsFree()) {
			this.vehicle = vehicle;
			setIsFree(false);
			return true;
		}
		else {
			return false;
		}
	}
	
	public void removeVehicle() {
		setIsFree(true);
		this.vehicle = null;
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
	
	public Integer getParkingSpaceNum() {
		return parkingSpaceNum;
	}
	
}
