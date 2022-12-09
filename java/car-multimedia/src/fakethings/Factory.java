package fakethings;
import java.util.ArrayList;
import vehicle.Vehicle;
import vehicle.Car;
import vehicle.Fuel;
import vehicle.Doors;

public abstract class Factory {

	public static ArrayList<Vehicle> getFakeVehicles() {
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

		String[] fakeCarType = {"Hatch","Hatch","Sedan"};
		String[] fakeRentNames = {"Hyundai","Chevrolet","Honda"};
		String[] fakeCarNames = {"HB20","Onix","Civic"};
		int[] fakeCarMaxVelocity = {192,171,202};
		int[] fakeFuelLevel = {50,76,98};
		String[] fakeFuelType = {"Etanol","Gasoline","Etanol"};

		for (int i = 0; i < 3; i++) {
			Fuel fuel = new Fuel(fakeFuelLevel[i], fakeFuelType[i]);
			Doors doors = new Doors(false, false, false, true);
			Car car = new Car(fakeCarType[i], fakeCarNames[i], fakeRentNames[i], fakeCarMaxVelocity[i], fuel, doors);
			vehicles.add(car);
		}

		return vehicles;
	}

	public static ArrayList<Device> getFakeDevices() {
		ArrayList<Device> devices = new ArrayList<Device>();
		String[] fakePhoneDeviceNames = {"Mi Play","Davi","Moto G 4575"};
		String[] fakeIPodDeviceNames = {"Laura","Renato","Fernanda"};

		for (int i = 0; i < 3; i++) {
			Phone phone = new Phone(fakePhoneDeviceNames[i]);
			IPod ipod = new IPod(fakeIPodDeviceNames[i]);
			devices.add(phone);
			devices.add(ipod);
		}

		return devices;
	}
}
