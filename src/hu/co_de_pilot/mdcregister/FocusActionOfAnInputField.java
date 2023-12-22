package hu.co_de_pilot.mdcregister;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JScrollPane;

public class FocusActionOfAnInputField implements FocusListener {

	private final String regexOfregNumberOfMFA = "HU-[\\d\\/]+";
	private final String regexOfregistrationOfAircraft = "[[A-Z][-_]]{5,6}";
	private final String regexOftypeOfAircraft = "[[A-Z][0-9]]{3,4}";
	private final String regexOfradioCallSign = "[[A-Z][0-9]]{5,7}";
	private final String regexOfdate = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
	private final String regexOfAerodrome = "[[A-Z][0-9][-_]]{3,7}";

	private JScrollPane scrollpane;
	private DesignTextField textfield;
	private DesignComboBox combobox = null;
	private String typeOfData;
	private hu.co_de_pilot.datechooser.DateChooser dateChooser;
	private boolean isCorrectInputData = false;
	MouseAction mouseAction;

	public FocusActionOfAnInputField(DesignTextField textfield, String typeOfData) {
		this.textfield = textfield;
		this.typeOfData = typeOfData;
	}

	public FocusActionOfAnInputField(DesignTextField textfield, String typeOfData, JScrollPane scrollpane,
			MouseAction mouseAction) {
		this.textfield = textfield;
		this.typeOfData = typeOfData;
		this.scrollpane = scrollpane;
		this.mouseAction = mouseAction;
	}

	public FocusActionOfAnInputField(DesignComboBox combobox) {
		this.combobox = combobox;
	}

	public FocusActionOfAnInputField(DesignTextField textfield, String typeOfData, hu.co_de_pilot.datechooser.DateChooser dateChooser) {
		this.textfield = textfield;
		this.typeOfData = typeOfData;
		this.dateChooser = dateChooser;
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		if (combobox == null) {
			textfield.setShadowColor(Color.decode("#c31432"));
			if (typeOfData.equals("departureAerodrome") || typeOfData.equals("destinationAerodrome")
					|| typeOfData.equals("typeOfAircraft")) {
				scrollpane.setVisible(true);
				mouseAction.setTextfield(textfield);
			} else if (typeOfData.equals("dateOfInflight")) {
				dateChooser.showPopup(); // Fókuszra megjelenik a dátumválasztó, de nem tűnik el a kiválasztás után!!!
			}
		} else {
			combobox.setShadowColor(Color.decode("#c31432"));
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (combobox != null) {
			isCorrectInputData = true;
			combobox.setShadowColor(Color.decode("#38ef7d"));
//			combobox.setForeground(Color.decode("#38ef7d"));
		} else if (typeOfData.equals("regNumberOfMFA")) {
			Pattern pattern = Pattern.compile(regexOfregNumberOfMFA);
			Matcher matcher = pattern.matcher(textfield.getText());
			if (matcher.matches() || textfield.getText()
					.equals("")) {
				isCorrectInputData = true;
				textfield.setShadowColor(Color.decode("#38ef7d"));
			}
		} else if (typeOfData.equals("registrationOfAircraft")) {
			Pattern pattern = Pattern.compile(regexOfregistrationOfAircraft);
			Matcher matcher = pattern.matcher(textfield.getText());
			if (matcher.matches()) {
				isCorrectInputData = true;
				textfield.setShadowColor(Color.decode("#38ef7d"));
			}
		} else if (typeOfData.equals("typeOfAircraft")) {
			scrollpane.setVisible(false);
			Pattern pattern = Pattern.compile(regexOftypeOfAircraft);
			Matcher matcher = pattern.matcher(textfield.getText());
			if (matcher.matches()) {
				isCorrectInputData = true;
				textfield.setShadowColor(Color.decode("#38ef7d"));
			}
		} else if (typeOfData.equals("radioCallSign")) {
			Pattern pattern = Pattern.compile(regexOfradioCallSign);
			Matcher matcher = pattern.matcher(textfield.getText());
			if (matcher.matches() || textfield.getText()
					.equals("")) {
				isCorrectInputData = true;
				textfield.setShadowColor(Color.decode("#38ef7d"));
			}
		} else if (typeOfData.equals("dateOfInflight")) {
			Pattern pattern = Pattern.compile(regexOfdate);
			Matcher matcher = pattern.matcher(textfield.getText());
			if (matcher.matches() || textfield.getText()
					.equals("")) {
				isCorrectInputData = true;
				textfield.setShadowColor(Color.decode("#38ef7d"));
			}
		} else if (typeOfData.equals("departureAerodrome")) {
			scrollpane.setVisible(false);
			Pattern pattern = Pattern.compile(regexOfAerodrome);
			Matcher matcher = pattern.matcher(textfield.getText());
			if (matcher.matches() || textfield.getText()
					.equals("")) {
				isCorrectInputData = true;
				textfield.setShadowColor(Color.decode("#38ef7d"));
			}
		} else if (typeOfData.equals("destinationAerodrome")) {
			scrollpane.setVisible(false);
			Pattern pattern = Pattern.compile(regexOfAerodrome);
			Matcher matcher = pattern.matcher(textfield.getText());
			if (matcher.matches() || textfield.getText()
					.equals("")) {
				isCorrectInputData = true;
				textfield.setShadowColor(Color.decode("#38ef7d"));
			}
		}
	}
}
