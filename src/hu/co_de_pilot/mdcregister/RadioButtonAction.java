package hu.co_de_pilot.mdcregister;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComponent;

public class RadioButtonAction implements ItemListener {

	private JComponent[] arrayofJC;

	/**
	 * Konstruktor a rádiógomb műveletekhez.
	 */
	public RadioButtonAction(JComponent[] arrayofJC) {
		this.arrayofJC = arrayofJC;
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		for (int i = 0; i < arrayofJC.length; i++) {
			if (e.getStateChange() == e.SELECTED) {
				arrayofJC[i].setEnabled(true);
			} else {
				arrayofJC[i].setEnabled(false);
			}
		}
	}

}
