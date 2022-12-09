package fakethings;

public class IPod implements Device {
	private String name;
	
	// Constructor:
	
	public IPod(String name) {
		setName(name);
	}
	
	// Getters:
	public String getName() {
		return this.name;
	}
	// Setters:
	public void setName(String name) {
		StringBuilder iPodName = new StringBuilder();
		iPodName.append("Ipod ").append(name);
		this.name = iPodName.toString();
	}
}
