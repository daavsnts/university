package music;

public class Music {
	private int volume;
	private Auxiliary auxiliary = new Auxiliary();
	private Bluetooth bluetooth = new Bluetooth();
	private Radio radio = new Radio();
	private String listening;
	
	// Getters:
	
	public int getVolume() {
		return volume;
	}
	
	public Auxiliary getAuxiliary() {
		return auxiliary;
	}
	
	public Bluetooth getBluetooth() {
		return bluetooth;
	}
	
	public Radio getRadio() {
		return radio;
	}
	
	public String getListening() {
		return listening;
	}
	
	// Setters:
	
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	public void setVolume(String volume) {
		this.volume = Integer.parseInt(volume);
	}
	
	public void setAuxiliary(Auxiliary auxiliary) {
		this.auxiliary = auxiliary;
	}
	
	public void setBluetooth(Bluetooth bluetooth) {
		this.bluetooth = bluetooth;
	}
	
	public void setRadio(Radio radio) {
		this.radio = radio;
	}
	
	public void setListening(String listening) {
		this.listening = listening;
	}
}
