package parkinglot;

public class ParkingLot {
	
	// Fields
	private ParkingSpace[] parkingSpaces;
	
	// Constructor
	public ParkingLot(int numSpaces, int carSpaces, int bikeSpaces) {
		this.parkingSpaces = new ParkingSpace[numSpaces];
		for (int i=0; i < carSpaces; i++) {
			if (i == numSpaces) break;
			this.parkingSpaces[i] = new ParkingSpace("car", i);
		}
		if (carSpaces < numSpaces) {
			for (int j=carSpaces; j < (carSpaces + bikeSpaces); j++) {
				if (j == numSpaces) break;
				this.parkingSpaces[j] = new ParkingSpace("bike", j);
			}
		}
	}
	
	// Methods
	public Integer isFreeSpace(String type) {
		for (ParkingSpace space: parkingSpaces) {
			if (space.getSpaceType().equals(type) && space.checkIsFree()) {
				return space.getParkingSpaceNum();
			}
		}
		return null;
	}
	
	public boolean parkVehicle(Vehicle vehicle) {
		Integer parkingSpaceNum = isFreeSpace(vehicle.getVehicleType());
		if (parkingSpaceNum != null) {
			return parkingSpaces[parkingSpaceNum].parkVehicle(vehicle, parkingSpaceNum);
		}
		else {
			return false;
		}
	}
	
	public long removeVehicle(String regNum) {
		for (ParkingSpace space: parkingSpaces) {
			String vehicleNum = space.getVehicleRegNo();
			if (vehicleNum == null) continue;
			else if (vehicleNum.equals(regNum)) {
				return space.removeVehicle();
			}
		}
		return -1;
	}
	
	public String getVehicleType(String regNum) {
		for (ParkingSpace space: parkingSpaces) {
			String vehicleNum = space.getVehicleRegNo();
			if (vehicleNum == null) continue;
			else if (vehicleNum.equals(regNum)) {
				return space.getSpaceType();
			}
		}
		return null;
	}
	
}
