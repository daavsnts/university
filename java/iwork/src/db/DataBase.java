package db;
import java.util.ArrayList;

import users.*;

public class DataBase {
	private static ArrayList<User> users = new ArrayList<User>();
	private static ArrayList<String> professions = new ArrayList<String>();

	public static User verifyIfExists(String username) { // Verifica se o nome de usuário já existe.
		for (User users: users) {
			if (users.getUsername().equals(username)) { // Encontrou o nome de usuário.
				return users;
			}
		}
		return null; // Não encontrou o nome de usuário.
	}

	public static void insertUser(User user) { // Insere um usuário na DB.
		DataBase.users.add(user);
	}

	public static User getUser(String username, String password) { // Tenta encontrar um usuário na DB.
		User user = verifyIfExists(username); // Tenta verificar se o nome de usuário existe.
		if (user != null) {
			if (user.getPassword().equals(password)) { // Tenta verificar se a senha está correta.
				return user;
			} else {
				throw new IllegalArgumentException("[DataBase] - Senha incorreta!"); // A senha não bateu.
			}
		} else {
			throw new IllegalArgumentException("[DataBase] - Esse usuário não existe!"); // Não foi encontrado o username na DB.
		}
	}

	public static int getNumberOfUsers() { // Checa a quantidade de usuários na DB.
		return DataBase.users.size(); 
	}

	public static User getUserByIndex(int index) {
		return DataBase.users.get(index);
	}

	public static void addServiceOnList(String profession) {
		boolean has = false;
		for (String professionOfList: professions) {
			if (professionOfList.equals(profession)) {
				has = true;
			} else {
				has = false;
			}

		}
		if (!has)
			professions.add(profession);
	}

	public static ArrayList<String> getServicesList() {
		if (professions.isEmpty()) {
			throw new IllegalArgumentException("[DataBase] - Não há serviços registrados!");
		} else {
			return professions;
		}
	}

	public static ArrayList<String> getWorkersNameList(String service) {
		ArrayList<String> workersNameList = new ArrayList<String>();

		for (User user: users) {
			if (user.getCard() != null)
			if (user.getCard().getProfession().equals(service))
				workersNameList.add(user.getName());
		}

		return workersNameList;

	}
	
	public static User getWorkerByName(String name) {
		for (User user: users)
			if (user.getName().equals(name))
				return user;
		
		return null;
	}

}
