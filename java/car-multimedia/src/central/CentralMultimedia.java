package central;

import music.Music;
import vehicle.Vehicle;

public class CentralMultimedia {
	private String rent;
	private String model;
	private Music music = new Music();
	private Vehicle vehicle;

	// Constructor:

	public CentralMultimedia(String model, String rent) {
		setModel(model);
		setRent(rent);
	}

	// Getters:

	public String getRent() {
		return rent;
	}

	public String getModel() {
		return model;
	}

	public Music getMusic() {
		return music;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	// Setters:

	public void setRent(String rent) {
		this.rent = rent;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
}
