package music;

public class Radio {
	private String frequency;

	// Getters:
	public String getFrequency() {
		if (!this.frequency.isEmpty()) {
			return this.frequency;
		} else {
			throw new IllegalArgumentException("[Radio] - No frequency selected!");
		}
	}

	// Setters:

	public void setFrequency(String frequency) {
		if (!frequency.isEmpty()) {
			this.frequency = frequency;
		} else {
			throw new IllegalArgumentException("[Radio] - Invalid frequency selected!");
		}
	}
}
