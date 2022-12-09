package vehicle;

import java.util.concurrent.ThreadLocalRandom;

public class Car extends Vehicle {
	private int maxVelocity;
	
	// Constructor:
	
	public Car(String type, String rent, String model, int maxVelocity, Fuel fuel, Doors doors) {
		super(type, rent, model, fuel, doors);
		setMaxVelocity(maxVelocity);
	}
	
	// Getters:
	
	public int getMaxVelocity() {
		return this.maxVelocity;
	}
	
	@Override
	public int getVelocity() {
		return ThreadLocalRandom.current().nextInt(0, getMaxVelocity() + 1);
	}
	
	// Setters:
	
	public void setMaxVelocity(int maxVelocity) {
		this.maxVelocity = maxVelocity;
	}
}
