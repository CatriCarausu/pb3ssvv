

import java.io.BufferedWriter;
import repository.DataManager;
import controller.ClientController;
import ui.ElectricaUI;

public class App {
	public static void main(String[] args) {
		DataManager repo = new DataManager();
		
		ClientController ctrl = new ClientController();
		
		ElectricaUI console = new ElectricaUI(ctrl);
		console.Run();
	}
}
