package fakethings;

public class Phone implements Device {
private String name;
	
	// Constructor:
	
	public Phone(String name) {
		setName(name);
	}
	
	// Getters:
	public String getName() {
		return this.name;
	}
	// Setters:
	public void setName(String name) {
		StringBuilder phoneName = new StringBuilder();
		phoneName.append(name).append(" Phone");
		this.name = phoneName.toString();
	}
}
