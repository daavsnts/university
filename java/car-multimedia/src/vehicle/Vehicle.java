package vehicle;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Vehicle {
	private String type;
	private String rent;
	private String model;
	private int velocity;
	private Fuel fuel;
	private Doors doors;
	private Air air = new Air();
	
	// Constructor:
	
	Vehicle(String type, String rent, String model, Fuel fuel, Doors doors) {
		setType(type);
		setRent(rent);
		setModel(model);
		setFuel(fuel);
		setDoors(doors);
	}
	
	// Getters:
	
	public String getType() {
		return type;
	}

	public String getRent() {
		return rent;
	}
	
	public String getModel() {
		return model;
	}
	
	public int getVelocity() {
		return this.velocity;
	}
	
	public String getFuelType() {
		return this.fuel.getType();
	}
	
	public int getFuelLevel() {
		return this.fuel.getLevel() - ThreadLocalRandom.current().nextInt(0, 4);
	}
	
	public Doors getDoors() {
		return this.doors;
	}
	
	public Air getAir() {
		return this.air;
	}
	
	// Setters:
	
	public void setRent(String rent) {
		this.rent = rent;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
	
	public void setFuel(Fuel fuel) {
		this.fuel = fuel;
	}
	
	public void setDoors(Doors doors) {
		this.doors = doors;
	}
	
	public void setAir(Air air) {
		this.air = air;
	}
	
}
