package hu.co_de_pilot.mdcregister;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class PopupMessages {
	private static String[] buttonLabelsYNC = new String[] { "Igen", "Nem", "Mégsem" };
	private static String[] buttonLabelsDeleteDeactivate = new String[] { "Törlés", "Deaktiválás", "Mégsem" };
	private static String[] buttonLabelsYN = new String[] { "Igen", "Nem" };
	private static String defaultOption = buttonLabelsYNC[0];
	private static Icon icon = null;
	
	public static int showExitConfirmMessage(JButton button, String textOfMessage, String titleOfMessage) {

		return JOptionPane.showOptionDialog(button, textOfMessage, titleOfMessage, JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.WARNING_MESSAGE, icon, buttonLabelsYNC, defaultOption);
	}

	public static int showConfirmMessage(JButton button, String textOfMessage, String titleOfMessage) {

		return JOptionPane.showOptionDialog(button, textOfMessage, titleOfMessage, JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE, icon, buttonLabelsYN, defaultOption);
	}
	
	public static int showDeleteConfirmMessage(JButton button, String textOfMessage, String titleOfMessage) {
		
		return JOptionPane.showOptionDialog(button, textOfMessage, titleOfMessage, JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.WARNING_MESSAGE, icon, buttonLabelsDeleteDeactivate, defaultOption);
	}
}
