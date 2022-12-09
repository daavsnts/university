package utils;
import central.CentralMultimedia;
import music.Music;
import fakethings.Device;
import fakethings.Factory;
import vehicle.Vehicle;
import vehicle.Car;

public abstract class Menu {

	// Settings:

	public static void settingsMenu(CentralMultimedia CM) {
		String option = View.selectFromList(Utils.getVehicleNames(Factory.getFakeVehicles()).toArray(), "Select a vehicle model: ", Utils.getCMName(CM) + " - Settings Menu");
		if (option != null)
			CM.setVehicle(Utils.getVehicleByName(option, Factory.getFakeVehicles()));
	}

	// Main:

	public static void mainMenu(CentralMultimedia CM) {
		Object[] options = {"Vehicle", "Music", "Settings", "Shutdown"};
		boolean exit = false;
		do {
			try {
				int option = View.createMenu(options, "Welcome to " + CM.getModel() + " Multimedia!", Utils.getCMName(CM) + " - Main Menu");
				switch(option) {
				case 0:
					vehicleMenu(CM.getVehicle(), Utils.getCMName(CM));
					break;
				case 1:
					musicMenu(CM.getMusic(), Utils.getCMName(CM));
					break;
				case 2:
					settingsMenu(CM);
					break;
				case 3:
					exit = true;
					break;
				}
			} catch (IllegalArgumentException e) {
				View.showError(e.getMessage());
			}
		} while (!exit);
	}

	// Vehicle:

	public static void vehicleMenu(Vehicle vehicle, String cmName) throws IllegalArgumentException {
		Utils.checkVehicleSelection(vehicle);
		Object[] options = {"Status", "Air", "Back"};
		boolean exit = false;
		do {
			int option = View.createMenu(options, "Select an option: ", cmName + " - Vehicle Menu");
			switch(option) {
			case 0:
				vehicleStatusMenu(vehicle, cmName);
				break;
			case 1:
				airMenu(vehicle, cmName);
				break;
			case 2:
				exit = true;
				break;	
			}
		} while (!exit);
	}

	// Status:

	public static void vehicleStatusMenu(Vehicle vehicle, String cmName) {
		if (vehicle instanceof Car) {
			StringBuilder carName = new StringBuilder();
			View.showMessage(
					"Model: " + carName.append(((Car)vehicle).getModel()).append(" ").append(((Car)vehicle).getRent()).toString() + "\n" +
							"Air: " + Utils.getAirStatus(vehicle.getAir()) + "\n\n" +
							"Velocity: " + ((Car)vehicle).getVelocity() + " KM/H" + "\n" +
							"Fuel type: " + ((Car)vehicle).getFuelType() + "\n" +
							"Fuel level: " + ((Car)vehicle).getFuelLevel() + "%\n\n" +
							"Doors: " + Utils.getDoorsStats(vehicle), cmName + " - Vehicle Status");
		}
	}

	// Air:

	public static void airMenu(Vehicle vehicle, String cmName) {
		String option = View.selectFromList(Utils.getAirOptionsByState(vehicle.getAir().isState()), "Select an option: ", cmName + " - Air Menu");

		if (option != null) {
			if (option.equals("Turn On")) {
				vehicle.getAir().setState(true);
			}
			if (option.equals("Turn Off")) {
				vehicle.getAir().setState(false);
			}
			if (option.equals("Set Level")) {
				Object[] levelOptions = {"20", "40", "60", "80", "100"};
				String levelSelected = View.selectFromList(levelOptions, "Select level:", "Level Menu");
				if (levelSelected != null)
					vehicle.getAir().setLevel(Integer.parseInt(levelSelected));
			}
			if (option.equals("Set Temperature")) {
				Object[] degreeOptions = {"-15", "-10", "0", "10", "15"};
				String degreeSelected =  View.selectFromList(degreeOptions, "Select degree:", "Degree Menu");
				if (degreeSelected != null)
					vehicle.getAir().setTemperature(degreeSelected);
			}
		}
	}

	// Music:

	public static void musicMenu(Music music, String cmName) {
		Object[] options = {"Volume", "Auxiliary", "Bluetooth", "Radio", "Back"};
		boolean exit = false;

		if (music.getListening() == null)
			music.setListening("Nothing");

		do {
			try {
				int option = View.createMenu(options, "Listening: " + music.getListening() + "\nVolume: " + music.getVolume(), cmName + " - Music Menu");
				switch(option) {
				case 0:
					volumeMenu(music, cmName);
					break;
				case 1:
					auxMenu(music, cmName);
					break;
				case 2:
					bluetoothMenu(music, cmName);
					break;
				case 3:
					radioMenu(music, cmName);
					break;
				case 4:
					exit = true;
					break;
				}
			} catch (IllegalArgumentException e) {
				View.showError(e.getMessage());
			}
		} while (!exit);
	}
	
	// Volume:
	
	public static void volumeMenu(Music music, String cmName) {
		Object[] volumeOptions = {"0", "20", "40", "60", "80", "100"};
		String volumeSelected =  View.selectFromList(volumeOptions, "Select volume:", cmName + " - Volume Menu");
		if (volumeSelected != null)
			music.setVolume(volumeSelected);
	}

	// Auxiliary:

	public static void auxMenu(Music music, String cmName) {
		Object[] options = {"Connect", "Disconnect"};
		String option = View.selectFromList(options, "Select an option: ", cmName + " - Auxiliary Menu");
		if (option != null) {
			if (option.equals("Connect")) {
				music.getAuxiliary().setCableConnection(true);
				music.setListening("Auxiliary");
			}
			if (option.equals("Disconnect")) {
				music.getAuxiliary().setCableConnection(false);
				music.setListening("Nothing");
			}
		}
	}

	// Bluetooth:

	public static void bluetoothMenu(Music music, String cmName) {
		Object[] options = {"Search Devices", "Saved Devices", "Back"};
		try {
			int option = View.createMenu(options, "Select an option: ", cmName + " - Bluetooth Menu");
			switch(option) {
			case 0:
				btSearchDevicesMenu(music);
				break;
			case 1:
				btSavedDevicesMenu(music);
				break;
			case 2:
				break;
			}
		} catch (IllegalArgumentException e) {
			View.showError(e.getMessage());
		}
	}

	public static void btSearchDevicesMenu(Music music) {
		Object[] options = Utils.getDeviceNames(Factory.getFakeDevices()).toArray();
		String option = View.selectFromList(options, "Select a device: ", "[Bluetooth] - Search Devices");
		if (option != null)
			btSetDevice(music, Utils.getDeviceByName(option, Factory.getFakeDevices()), "Search");
	}

	public static void btSavedDevicesMenu(Music music) throws IllegalArgumentException {
		Object[] options = Utils.getDeviceNames(music.getBluetooth().getSavedDevices()).toArray();
		String option = View.selectFromList(options, "Select a device: ", "[Bluetooth] - Saved Devices");
		if (option != null)
			btSetDevice(music, music.getBluetooth().getSavedDeviceByName(option), "Saved");
	}

	public static void btSetDevice(Music music, Device device, String type) {	
		if (type.equals("Search")) {	
			Object[] options = {"Connect", "Disconnect"};
			String option = View.selectFromList(options, "Select an option: ", "[Bluetooth] - Device: " + device.getName());
			if (option != null) {
				if (option.equals("Connect")) {
					if (!music.getBluetooth().checkDeviceAlreadySaved(device)) {
						music.getBluetooth().addSavedDevice(device);
					}
					music.getBluetooth().setDeviceConnected(device);
					music.setListening("[Bluetooth] - " + device.getName());
				}
				if (option.equals("Disconnect"))
					music.getBluetooth().setDeviceConnected(null);
			}
		}

		if (type.equals("Saved")) {	
			Object[] options = {"Connect", "Disconnect", "Remove"};
			String option = View.selectFromList(options, "Select an option: ", "[Bluetooth] - Device: " + device.getName());
			if (option != null) {
				if (option.equals("Connect")) {
					if (!music.getBluetooth().checkDeviceAlreadySaved(device)) {
						music.getBluetooth().addSavedDevice(device);
					}
					music.getBluetooth().setDeviceConnected(device);
					music.setListening("[Bluetooth] - " + device.getName());
				}
				if (option.equals("Disconnect")) {
					music.getBluetooth().setDeviceConnected(null);
					music.setListening("Nothing");
				}
				if (option.equals("Remove")) {
					music.getBluetooth().removeSavedDevice(device);
					music.setListening("Nothing");
				}
			}
		}
	}

	// Radio:

	public static void radioMenu(Music music, String cmName) throws IllegalArgumentException {
		String frequency = View.requestString("Input the frequency: ", cmName + " - Radio Menu");
		if (frequency != null) {
			music.getRadio().setFrequency(frequency);
			music.setListening("[Radio] - " + frequency);
		}
	}
}
