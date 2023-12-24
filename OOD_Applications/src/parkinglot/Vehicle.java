package parkinglot;

public class Vehicle {
	
	// Fields
	private String regNo;
	private boolean isParked;
	private String type;
	
	// Constructor
	public Vehicle(String regNo, String vehicleType) {
		this.regNo = regNo;
		this.type = vehicleType;
	}
	
	// Methods
	public String getRegNo() {
		return regNo;
	}
	
	public boolean getIsParked() {
		return isParked;
	}
	
	public void setIsParked(boolean parked) {
		isParked = parked;
	}
	
	public String getVehicleType() {
		return type;
	}
	
}
