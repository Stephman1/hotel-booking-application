package parkinglot;

public class ParkingSpace {
	
	// Fields
	private String spaceType;
	private boolean isFree = true;
	private Vehicle vehicle;
	private Integer parkingSpaceNum;
	private long timeOccupied;
	
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
			setTimeOccupied();
			setIsFree(false);
			return true;
		}
		else {
			return false;
		}
	}
	
	public long removeVehicle() {
		setIsFree(true);
		this.vehicle = null;
		return (System.currentTimeMillis() - timeOccupied);
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
	
	public long getTimeOccupied() {
		return timeOccupied;
	}
	
	public void setTimeOccupied() {
		timeOccupied = System.currentTimeMillis();
	}
	
}
