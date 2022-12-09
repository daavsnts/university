package utils;

import java.util.ArrayList;
import fakethings.Device;
import vehicle.Vehicle;
import central.CentralMultimedia;
import vehicle.Air;

public abstract class Utils {
	
	public static ArrayList<String> getDeviceNames(ArrayList<Device> devices) {
		if (devices != null) {
			ArrayList<String> deviceNames = new ArrayList<String>();
			for (Device device: devices) {
				deviceNames.add(device.getName());
			}
			return deviceNames;
		} else {
			throw new IllegalArgumentException("[UT] - There are no devices around!");
		}
	}
	
	public static ArrayList<String> getVehicleNames(ArrayList<Vehicle> vehicles) {
		if (vehicles != null) {
			ArrayList<String> vehicleNames = new ArrayList<String>();
			for (Vehicle vehicle: vehicles) {
				StringBuilder vehicleName = new StringBuilder();
				vehicleNames.add(vehicleName.append(vehicle.getRent()).append(" ").append(vehicle.getModel()).toString());
			}
			return vehicleNames;
		} else {
			throw new IllegalArgumentException("[UT] - There are no devices around!");
		}
	}
	
	public static Device getDeviceByName(String name, ArrayList<Device> devices) {
		if (devices != null) {
			for (Device device: devices) {
				if (device.getName().equals(name)) {
					return device;
				}
			}
		} 
		throw new IllegalArgumentException("[UT] - There are no device with this name!");
	}
	
	public static Vehicle getVehicleByName(String name, ArrayList<Vehicle> vehicles) {
		if (vehicles != null) {
			for (Vehicle vehicle: vehicles) {
				StringBuilder vehicleName = new StringBuilder();
				vehicleName.append(vehicle.getRent()).append(" ").append(vehicle.getModel());
				if (vehicleName.toString().equals(name)) {
					return vehicle;
				}
			}
		} 
		throw new IllegalArgumentException("[UT] - There are no vehicles with this name!");
	}
	
	public static String getCMName(CentralMultimedia CM) {
		StringBuilder cmName = new StringBuilder();
		cmName.append("[").append(CM.getRent()).append(" ").append(CM.getModel()).append("]");
		return cmName.toString();
	}
	
	public static String getDoorsStats(Vehicle vehicle) {
		StringBuilder doorsStats = new StringBuilder();
		if (vehicle.getDoors().isFront_left()) {
			doorsStats.append("\n[Front-left: Open]* | ");
		} else {
			doorsStats.append("\n[Front-left: Closed] | ");
		}
		if (vehicle.getDoors().isFront_right()) {
			doorsStats.append("[Front-right: Open]*\n");
		} else {
			doorsStats.append("[Front-right: Closed]\n");
		}
		if (vehicle.getDoors().isBack_left()) {
			doorsStats.append("[Back-left: Open]* | ");
		} else {
			doorsStats.append("[Back-left: Closed] | ");
		}
		if (vehicle.getDoors().isBack_right()) {
			doorsStats.append("[Back-right: Open]*");
		} else {
			doorsStats.append("[Back-right: Closed]");
		}
		
		return doorsStats.toString();
	}
	
	public static String getAirStatus(Air airCond) {
		StringBuilder airStatus = new StringBuilder();
		if (airCond.isState()) {
			return airStatus.append("On \nLevel: ").append(airCond.getLevel()).append("% | Temperature: ").append(airCond.getTemperature()).append("°C").toString();
		} else {
			return "Off";
		}
	}
	
	public static Object[] getAirOptionsByState(boolean state) {
		ArrayList<String> options = new ArrayList<String>();
		if (state == true) {
			options.add("Turn Off");
			options.add("Set Level");
			options.add("Set Temperature");
		} else {
			options.add("Turn On");
			options.add("Set Level");
			options.add("Set Temperature");
		}
		return options.toArray();
	}
	
	public static void checkVehicleSelection(Vehicle vehicle) {
		if (vehicle == null)
			throw new IllegalArgumentException("[Vehicle] - The vehicle was not selected!\nGo to Settings!");
	}
}
