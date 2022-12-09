package utils;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import db.DataBase;
import users.*;

public class View {

	public static void showMessage(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	public static String requestString(String msg, String title) {
		return JOptionPane.showInputDialog(null, msg, title, JOptionPane.QUESTION_MESSAGE);
	}

	public static int requestInt(String msg, String title) {
		return Integer.parseInt(JOptionPane.showInputDialog(null, msg, title, JOptionPane.QUESTION_MESSAGE)); 
	}

	public static void showError(String error) {
		JOptionPane.showMessageDialog(null, error, "[iWork] - Erro!", JOptionPane.ERROR_MESSAGE);
	}

	public static String selectFromList(Object[] choices, String msg, String title) {
		return (String) JOptionPane.showInputDialog(null, msg, title, JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
	}

	public static void homeScreen() {
		Object[] options = {"Login", "Registrar", "Sair"};
		boolean exit = false;
		do {
			int option = JOptionPane.showOptionDialog(null, "Bem vindo ao Freelance iWork!", "[iWork] - Início", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			switch (option) {
			case 0:
				userLogin();
				break;
			case 1:
				userRegister();
				break;
			case 2:
				exit = true;
				break;
			}
		} while (!exit);

	}

	public static void userRegister() {
		// GUI:
		final JTextField userNameField = new JTextField(10);
		final JTextField nameField = new JTextField(10);
		final JPasswordField passwordField = new JPasswordField(10);
		JPanel pane = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0);
		pane.add(new JLabel("Usuário:"), gbc);
		gbc.gridy = 1;
		pane.add(new JLabel("Senha:"), gbc);
		gbc.gridy = 2;
		pane.add(new JLabel("Nome:"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		pane.add(userNameField, gbc);
		gbc.gridy = 1;
		pane.add(passwordField, gbc);
		gbc.gridy = 2;
		pane.add(nameField, gbc);

		boolean sucessfull = false;
		do {
			int reply = JOptionPane.showConfirmDialog(null, pane, "[iWork] - Registrar usuário:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (reply == JOptionPane.OK_OPTION) {

				try {
					String username = userNameField.getText();
					String password = new String(passwordField.getPassword());
					String name = nameField.getText();

					Utils.createUser(username, password, name);
					showMessage("Cadastro efetuado com sucesso, faça o login para continuar!", "[iWork] - Registro");
					sucessfull = true;
				} catch (IllegalArgumentException e) {
					showError(e.getMessage());
					continue;
				}

			}
			if (reply == JOptionPane.CANCEL_OPTION)
				sucessfull = true;
		} while(!sucessfull);
	}

	public static void userLogin() {
		// GUI:
		final JTextField userNameField = new JTextField(10);
		final JPasswordField passwordField = new JPasswordField(10);
		JPanel pane = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0);
		pane.add(new JLabel("Usuário:"), gbc);
		gbc.gridy = 1;
		pane.add(new JLabel("Senha:"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		pane.add(userNameField, gbc);
		gbc.gridy = 1;
		pane.add(passwordField, gbc);

		boolean sucessfull = false;
		do {
			int reply = JOptionPane.showConfirmDialog(null, pane, "[iWork] - Fazer login:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (reply == JOptionPane.OK_OPTION) {
				String username = userNameField.getText();
				String password = new String(passwordField.getPassword());

				try {
					User user = Utils.userLogin(username, password);
					menu(user);
				} catch (IllegalArgumentException e) {
					showError(e.getMessage());
				}

			}
			if (reply == JOptionPane.CANCEL_OPTION)
				sucessfull = true;
		} while(!sucessfull);
	}

	public static void menu(User user) {
		Object[] options = {"Procurar Card", "Registrar Card", "Sair"};
		boolean exit = false;
		do {
			int option = JOptionPane.showOptionDialog(null, "Bem vindo ao Freelance iWork!", "[iWork] - Início", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			switch (option) {
			case 0:
				try {
					chooseService(user);
				} catch (IllegalArgumentException e) {
					showError(e.getMessage());
				}
				break;
			case 1:
				registerCard(user);
				break;
			case 2:
				exit = true;
				break;
			}
		} while (!exit);
	}

	// Register Card Menu:

	public static void registerCard(User user) {
		// GUI:
		final JTextField cardProfession = new JTextField(10);
		final JTextField cardDescription = new JTextField(40);
		final JTextField cardPhone = new JTextField(15);
		JPanel pane = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0);
		pane.add(new JLabel("Profissão:"), gbc);
		gbc.gridy = 1;
		pane.add(new JLabel("Telefone:"), gbc);
		gbc.gridy = 2;
		pane.add(new JLabel("Descrição do Card:"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		pane.add(cardProfession, gbc);
		gbc.gridy = 1;
		pane.add(cardPhone, gbc);
		gbc.gridy = 2;
		pane.add(cardDescription, gbc);

		boolean sucessfull = false;
		do {
			int reply = JOptionPane.showConfirmDialog(null, pane, "[iWork] - Registrar card:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (reply == JOptionPane.OK_OPTION) {
				String cardDesc = cardDescription.getText();
				String cardProf = cardProfession.getText();
				String cardPh = cardPhone.getText();

				try {
					user.createCard(cardDesc, cardProf, cardPh);
					showMessage("Card criado com sucesso, o mesmo já foi adicionado na lista!", "[iWork] - Registro");
					sucessfull = true;
				} catch (IllegalArgumentException e) {
					showError(e.getMessage());
				}

			}
			if (reply == JOptionPane.CANCEL_OPTION)
				sucessfull = true;
		} while(!sucessfull);
	}

	// Choose Service Menu:
	public static void chooseService(User user) {
		Object[] services = DataBase.getServicesList().toArray();
		String serviceSelected = View.selectFromList(services, "Selecione a opção desejada!", "[iWork] - Menu");

		chooseWorker(serviceSelected, user);
	}

	public static void chooseWorker(String service, User user) {
		Object[] workersNames = DataBase.getWorkersNameList(service).toArray();
		String workerSelected = View.selectFromList(workersNames, "Selecione o trabalhador desejado!", "[iWork] - Menu");

		seeWorkerCard(workerSelected, user, service);
	}

	public static void seeWorkerCard(String workerName, User user, String service) {
		User worker = DataBase.getWorkerByName(workerName);
		if (worker == user) {
			View.showMessage("Nome do trabalhador: " + worker.getName() + "\n" + "Profissão: " + worker.getCard().getProfession() + "\n" + "Descrição: " + worker.getCard().getDescription() + "\n", "[iWork] - Card do " + worker.getName());
		} else {
				Object[] hours = {"09:00", "12:00", "15:00"};
				String hourSelected = View.selectFromList(hours, "Nome do trabalhador: " + worker.getName() + "\n" + "Profissão: " + worker.getCard().getProfession() + "\n" + "Descrição: " + worker.getCard().getDescription() + "\n" + "Selecione o horário para agendar!", "[iWork] - Menu");
				if (Utils.checkAvaliableSchedulings(worker.getCard().getSchedulings(), hourSelected)) {
					worker.getCard().addScheduling(hourSelected, service, user);
					showMessage("Agendamento feito com sucesso!", "[iWork] - Agendamento!");
				} else {
					showError("Horário não disponível!");
				}
		}
		
	}

}
