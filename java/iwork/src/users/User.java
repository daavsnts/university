package users;

import db.DataBase;

public class User {
	private String username;
	private String password;
	private String name;
	private Card card;

	public User(String username, String password, String name) {
		setUsername(username);
		setPassword(password);
		setName(name);
	}

	// Getters:
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
	public String getName() {
		return this.name;
	}
	public Card getCard() {
		return this.card;
	}

	// Setters:
	public void setUsername(String username) {
		if (username.isEmpty()) {
			throw new IllegalArgumentException("[User] - Nome de usuário inválido!");
		}
		if (DataBase.verifyIfExists(username) != null) {
			throw new IllegalArgumentException("[DataBase] - Esse nome de usuário já está sendo usado!");
		}
			this.username = username;
	}
	public void setPassword(String password) {
		if (password.isEmpty())
			throw new IllegalArgumentException("[User] - Senha inválida!");
		
		this.password = password;
	}
	public void setName(String name) {
		if (name.isEmpty())
			throw new IllegalArgumentException("[User] - Nome inválido!");
		this.name = name;
	}
	public Card createCard(String description, String profession, String phone) {
		this.card = new Card(description, profession, phone);
		DataBase.addServiceOnList(profession);
		return this.card;
	}

}
