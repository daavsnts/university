package music;
import java.util.ArrayList;
import fakethings.*;

public class Bluetooth {
	private ArrayList<Device> savedDevices = new ArrayList<Device>();
	private Device deviceConnected;

	// Getters:

	public ArrayList<Device> getSavedDevices() {
		if (this.savedDevices.size() != 0) {
			return this.savedDevices;
		} else {
			throw new IllegalArgumentException("[BT] - There are no saved devices!");
		}
	}
	
	public boolean checkDeviceAlreadySaved(Device device) {
		for (Device devices: this.savedDevices) {
			if (devices.getName().equals(device.getName())) {
				return true;
			}
		}
		return false;
	}

	public Device getSavedDeviceByName(String name) {
		if (this.savedDevices != null) {
			for (Device device: savedDevices) {
				if (device.getName().equals(name)) {
					return device;
				}
			}
		} 
		throw new IllegalArgumentException("[BT] - There are no device with this name!");
	}
	
	public Device getDeviceConnected() {
		return this.deviceConnected;
	}

	// Setters:

	public void addSavedDevice(Device device) {
		savedDevices.add(device);
	}

	public void removeSavedDevice(Device device) {
		if (this.deviceConnected == device)
			this.deviceConnected = null;
		savedDevices.remove(device);
	}

	public void setDeviceConnected(Device device) {
		this.deviceConnected = device;
	}
}
