package parkinglot;

public class ParkingLot {
	
	// Fields
	private ParkingSpace[] parkingSpaces;
	private boolean isFreeSpace;
	
	// Constructor
	public ParkingLot(int numSpaces, int carSpaces, int bikeSpaces) {
		this.parkingSpaces = new ParkingSpace[numSpaces];
		for (int i=0; i < carSpaces; i++) {
			if (i == numSpaces) break;
			this.parkingSpaces[i] = new ParkingSpace("car");
		}
		if (carSpaces < numSpaces) {
			for (int j=carSpaces; j < (carSpaces + bikeSpaces); j++) {
				if (j == numSpaces) break;
				this.parkingSpaces[j] = new ParkingSpace("bike");
			}
		}
	}
	
	// Methods
	public boolean getIsFreeSpace() {
		return isFreeSpace;
	}
	
	public boolean parkVehicle(Vehicle vehicle) {
		return false;
	}
	
	public boolean removeVehicle(String regNo) {
		return false;
	}
	
	public int availableSpaces() {
		int numSpaces = 0;
		if (!isFreeSpace) return numSpaces;
		for (ParkingSpace space: parkingSpaces) {
			if (space.checkIsFree()) {
				numSpaces += 1;
			}
		}
		return numSpaces;
	}
	
}
