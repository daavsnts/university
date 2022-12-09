package users;
import java.util.ArrayList;

public class Card {
	private String description;
	private String profession;
	private String phone;
	private ArrayList<Scheduling> schedulings = new ArrayList<Scheduling>();
	private float rating;
	
	public Card(String description, String profession, String phone) {
		setDescription(description);
		setProfession(profession);
		setPhone(phone);
	}
	
	// Getters:
	public String getDescription() {
		return this.description;
	}
	public String getProfession() {
		return this.profession;
	}
	public String getPhone() {
		return this.phone;
	}
	public ArrayList<Scheduling> getSchedulings() {
		return this.schedulings;
	}
	public float getRating() {
		return this.rating;
	}
	
	// Setters:
	public void setDescription(String description) {
		if (description.isEmpty())
			throw new IllegalArgumentException("[Card] - Descrição inválida!");
		this.description = description;
	}
	public void setProfession(String profession) {
		if (profession.isEmpty())
			throw new IllegalArgumentException("[Card] - Profissão inválida!");
		this.profession = profession;
	}
	public void setPhone(String phone) {
		if (phone.isEmpty())
			throw new IllegalArgumentException("[Card] - Número de telefone inválido!");
		this.phone = phone;
	}
	public Scheduling addScheduling(String time, String profession, User client) {
		if (profession.isEmpty())
			throw new IllegalArgumentException("[Card] - Profissão inválida!");
		if (client == null)
			throw new IllegalArgumentException("[Card] - Usuário inválido!");
		if (time == null)
			throw new IllegalArgumentException("[Card] - Agendamento inválido!");
		Scheduling scheduling = new Scheduling(time, profession, client);
		schedulings.add(scheduling);
		return scheduling;
	}
	public void setRating(float rating) {
		this.rating += (rating / 0.5);
	}
}
