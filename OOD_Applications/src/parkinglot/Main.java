package parkinglot;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		ParkingLot parkingLot = new ParkingLot(3,2,1);
		Attendant parkingAttendant = new Attendant();
		Scanner scanner = new Scanner(System.in);
		
		Main parkingLotApp = new Main();
		parkingLotApp.start(parkingLot, parkingAttendant, scanner);
	}
	
	public void start(ParkingLot parkingLot, Attendant parkingAttendant, Scanner scanner) {
		while (true) {
			System.out.println("Would you like to park or remove your vehicle");
			System.out.print("> ");
			System.out.flush();
			String parkRemove = scanner.nextLine();
			if (parkRemove.equalsIgnoreCase("end program")) {
				scanner.close();
				return;
			}
			System.out.println("Please enter your vehicle registration number");
			System.out.print("> ");
			System.out.flush();
			String regNum = scanner.nextLine();
			
			if (parkRemove.equalsIgnoreCase("park")) {
				System.out.println("Please enter your vehicle type");
				System.out.print("> ");
				System.out.flush();
				String type = scanner.nextLine();
				if (parkingAttendant.giveParkingSpace(new Vehicle(regNum, type), parkingLot)) {
					System.out.println("Thank you for parking your vehicle.");
				}
				else {
					System.out.println("Sorry, we do not have any more spaces for your vehicle type.");
				}
			}
			else if (parkRemove.equalsIgnoreCase("remove")) {
				double payment = parkingAttendant.retrieveVehicle(regNum, parkingLot);
				if (payment != -1) {
					System.out.printf("Please pay the attendant £%.2f\n",payment);
					System.out.println("Thank you for using our parking lot.");
				}
				else {
					System.out.println("This registration number does not match a vehicle in the parking lot.");
				}
			}
		}
	}
	
}
