package hu.co_de_pilot.mdcregister;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class SerializationSFCL {

	private InsertSingleFlightClearance panelInsertSFCL;
	private SearchSingleFlightClearance panelSearchSFCL;

	public SerializationSFCL(boolean isDeserialization,
			InsertSingleFlightClearance panelInsertSFCL, SearchSingleFlightClearance panelSearchSFCL) {

		this.panelInsertSFCL = panelInsertSFCL;
		this.panelSearchSFCL = panelSearchSFCL;

		if (!isDeserialization) {
			serializeSFCL();
		} else {
			deserializeSFCL();
		}
	}

	private void serializeSFCL() {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("listOfSFCL.obj"))) {
			out.writeObject(panelSearchSFCL.getListOfSFCL());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deserializeSFCL() {
		List<SingleFlightClearance> readListOfSFCL = null;
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("listOfSFCL.obj"))) {
			readListOfSFCL = (List<SingleFlightClearance>) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		panelSearchSFCL.getListOfSFCL().clear();
		for (SingleFlightClearance readSFCL : readListOfSFCL) {
			SingleFlightClearance actualSFCL = new SingleFlightClearance(readSFCL.getRegNumberOfMFA(),
					readSFCL.getStateOfRegistry(), panelInsertSFCL, panelSearchSFCL,
					readSFCL.getRegistrationOfAircraft(), readSFCL.getTypeOfAircraft(), readSFCL.getRadioCallSign(),
					readSFCL.getDateValidFrom(), readSFCL.getDepartureAerodrome(), readSFCL.getDestinationAerodrome(),
					readSFCL.getPurposeOfFlight(), readSFCL.isApproved(), readSFCL.isActive(), readSFCL.isModified(),
					readSFCL.isFlownAway(), readSFCL.getRemarks(), readSFCL.getOriginRegNumberOfMFA(),
					readSFCL.isModifier(), readSFCL.getDateOfRealflight(), readSFCL.getClearancePDFFilePath());
			panelSearchSFCL.getListOfSFCL().add(actualSFCL);
		}
	}

}
