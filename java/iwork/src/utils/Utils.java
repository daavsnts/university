package utils;
import users.*;

import java.util.ArrayList;

import db.*;

public class Utils {

	public static User createUser(String username, String password, String name) throws IllegalArgumentException {
		// Validar antes de instanciar
		User user = new User(username, password, name);
		DataBase.insertUser(user);
		return user;
	}

	public static User userLogin(String username, String password) {
		return DataBase.getUser(username, password);
	}

	public static boolean checkAvaliableSchedulings(ArrayList<Scheduling> schedulings, String hourSelected) {
		boolean avaliable = true;
		for (Scheduling scheduling: schedulings) {
			if (scheduling.getTime().equals(hourSelected))
				avaliable = false;
			if (scheduling.getTime().equals(hourSelected))
				avaliable = false;
			if (scheduling.getTime().equals(hourSelected))
				avaliable = false;
		}

		return avaliable;
	}

}
