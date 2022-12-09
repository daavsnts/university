import central.CentralMultimedia;
import utils.Menu;

public class Main {
	
	public static void main(String[] args) {
		CentralMultimedia corkex = new CentralMultimedia("Corkex", "UCB");
		Menu.settingsMenu(corkex);
		Menu.mainMenu(corkex);
	}

}
