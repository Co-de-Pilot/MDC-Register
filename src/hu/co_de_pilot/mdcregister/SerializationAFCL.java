package hu.co_de_pilot.mdcregister;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class SerializationAFCL {

	private InsertAnnualFlightClearance panelInsertAFCL;
	private SearchAnnualFlightClearance panelSearchAFCL;

	public SerializationAFCL(boolean isDeserialization,
			InsertAnnualFlightClearance panelInsertAFCL, SearchAnnualFlightClearance panelSearchAFCL) {

		this.panelInsertAFCL = panelInsertAFCL;
		this.panelSearchAFCL = panelSearchAFCL;

		if (!isDeserialization) {
			serializeAFCL();
		} else {
			deserializeAFCL();
		}
	}

	private void serializeAFCL() {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("listOfAFCL.obj"))) {
			out.writeObject(panelSearchAFCL.getListOfAFCL());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deserializeAFCL() {
		List<AnnualFlightClearance> readListOfAFCL = null;
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("listOfAFCL.obj"))) {
			readListOfAFCL = (List<AnnualFlightClearance>) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		panelSearchAFCL.getListOfAFCL().clear();
		for (AnnualFlightClearance readAFCL : readListOfAFCL) {
			AnnualFlightClearance actualAFCL = new AnnualFlightClearance(readAFCL.getRegNumberOfMFA(),
					readAFCL.getStateOfRegistry(), panelInsertAFCL, panelSearchAFCL,
					readAFCL.getListOfAircrafts(), readAFCL.getDateValidFrom(), readAFCL.getDateValidUntil(),
					readAFCL.isApproved(), readAFCL.isActive(), readAFCL.isModified(),
					readAFCL.getRemarks(), readAFCL.getOriginRegNumberOfMFA(),
					readAFCL.isModifier(), readAFCL.getClearancePDFFilePath());
			panelSearchAFCL.getListOfAFCL().add(actualAFCL);
		}
	}

}
