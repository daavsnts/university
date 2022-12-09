package users;

public class Scheduling {
	private String time;
	private String profession;
	private User client;
	
	public Scheduling(String time, String profession, User client) {
		setTime(time);
		setProfession(profession);
		setClient(client);
	}
	
	// Getters:
	public String getTime() {
		return this.time;
	}
	public String getProfession() {
		return this.profession;
	}
	public User getClient() {
		return this.client;
	}
	
	// Setters:
	public void setTime(String time) {
		if (time.isEmpty())
			throw new IllegalArgumentException("[Agendamento] - Horário inválido!");
		this.time = time;
	}
	public void setProfession(String profession) {
		if (profession.isEmpty())
			throw new IllegalArgumentException("[Agendamento] - Profissão inválida!");
		this.profession = profession;
	}
	public void setClient(User client) {
		if (client == null)
			throw new IllegalArgumentException("[Agendamento] - Cliente inválido!");
		this.client = client;
	}
}
