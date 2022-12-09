package vehicle;

public class Doors {
	private boolean front_left;
	private boolean front_right;
	private boolean back_left;
	private boolean back_right;
	
	// Constructor:
	
	public Doors(boolean front_left, boolean front_right, boolean back_left, boolean back_right) {
		setFront_left(front_left);
		setFront_right(front_right);
		setBack_left(back_left);
		setBack_right(back_right);
	}
	
	// Getters:
	
	public boolean isFront_left() {
		return front_left;
	}

	public boolean isFront_right() {
		return front_right;
	}
	
	public boolean isBack_left() {
		return back_left;
	}
	
	public boolean isBack_right() {
		return back_right;
	}
	
	// Setters:
	
	public void setFront_left(boolean front_left) {
		this.front_left = front_left;
	}

	public void setFront_right(boolean front_right) {
		this.front_right = front_right;
	}

	public void setBack_left(boolean back_left) {
		this.back_left = back_left;
	}

	public void setBack_right(boolean back_right) {
		this.back_right = back_right;
	}
}
