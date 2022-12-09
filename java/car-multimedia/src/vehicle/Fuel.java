package vehicle;

public class Fuel {
	private int level;
	private String type;
	
	// Constructor:
	
	public Fuel(int level, String type) {
		setLevel(level);
		setType(type);
	}
	
	// Getters:

	public int getLevel() {
		return level;
	}

	public String getType() {
		return type;
	}
	
	// Setters:
	
	public void setLevel(int nivel) {
		this.level = nivel;
	}

	public void setType(String type) {
		this.type = type;
	}
}
