package vehicle;

public class Air {
	private int temperature;
	private boolean state;
	private int level;
	
	// Getters:
	
	public int getTemperature() {
		return temperature;
	}
	
	public boolean isState() {
		return state;
	}
	
	public int getLevel() {
		return level;
	}
	
	// Setters:
	
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	
	public void setTemperature(String temperature) {
		this.temperature = Integer.parseInt(temperature);
	}
	
	public void setState(boolean state) {
		this.state = state;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void setLevel(String level) {
		this.level = Integer.parseInt(level);
	}
}
