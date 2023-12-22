package hu.co_de_pilot.mdcregister.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import hu.co_de_pilot.mdcregister.InsertAnnualFlightClearance;
import hu.co_de_pilot.mdcregister.InsertSingleFlightClearance;
import hu.co_de_pilot.mdcregister.SearchAnnualFlightClearance;
import hu.co_de_pilot.mdcregister.SearchSingleFlightClearance;
import hu.co_de_pilot.mdcregister.SerializationAFCL;
import hu.co_de_pilot.mdcregister.SerializationSFCL;

public class SerializeButton extends DesignButton implements ActionListener {
	
	private InsertAnnualFlightClearance panelInsertAFCL;
	private SearchAnnualFlightClearance panelSearchAFCL;
	private InsertSingleFlightClearance panelInsertSFCL;
	private SearchSingleFlightClearance panelSearchSFCL;

	public SerializeButton(String text, String name, InsertAnnualFlightClearance panelInsertAFCL,
			SearchAnnualFlightClearance panelSearchAFCL, InsertSingleFlightClearance panelInsertSFCL,
			SearchSingleFlightClearance panelSearchSFCL) {
		super(text, name);
		this.panelInsertAFCL = panelInsertAFCL;
		this.panelSearchAFCL = panelSearchAFCL;
		this.panelInsertSFCL = panelInsertSFCL;
		this.panelSearchSFCL = panelSearchSFCL;
		this.setToolTipText("Az engedélyek listájának fájlba(tartós adatbázisba) mentése.");
		this.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			if (panelSearchAFCL != null) {
				new SerializationAFCL(false, panelInsertAFCL, panelSearchAFCL);
			} else {
				new SerializationSFCL(false, panelInsertSFCL, panelSearchSFCL);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"A lista mentése nem sikerült. Kérje az adminisztrátor segítségét!",
					"Adatok mentése", JOptionPane.OK_OPTION);
		}
		JOptionPane.showMessageDialog(null, "A lista mentése sikeresen végrehajtásra került.", "Adatok mentése",
				JOptionPane.OK_OPTION);
	}

}
