package utils;

import javax.swing.JOptionPane;

abstract public class View {
	
	// Messages:
	public static void showMessage(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	public static void showError(String error) {
		JOptionPane.showMessageDialog(null, error, "[Central Multimedia] - Error!", JOptionPane.ERROR_MESSAGE);
	}
	
	public static String requestString(String msg, String title) {
		return JOptionPane.showInputDialog(null, msg, title, JOptionPane.QUESTION_MESSAGE);
	}

	// Menus:
	
	public static int createMenu(Object[] options, String message, String menuName) {
            int option = JOptionPane.showOptionDialog(null, message, menuName, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            return option;
    }
	
	public static String selectFromList(Object[] choices, String msg, String title) {
		return (String) JOptionPane.showInputDialog(null, msg, title, JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
	}
	
}