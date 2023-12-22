package hu.co_de_pilot.mdcregister;

import java.util.List;

import javax.swing.table.DefaultTableModel;

public class RefillTableFromList {

	public RefillTableFromList(DefaultTableModel tablemodel, int columnNumber, List<SingleFlightClearance> listOfSFCL,
			List<AnnualFlightClearance> listOfAFCL) {

		Object[] arrayOfNewRowDatas = new Object[columnNumber];
		tablemodel.setRowCount(0);
		if (listOfSFCL != null) {
			for (SingleFlightClearance sfcl : listOfSFCL) {
				for (int i = 0; i < arrayOfNewRowDatas.length; i++) {
					arrayOfNewRowDatas[i] = sfcl.getDatasInArray()[i];
				}
				tablemodel.addRow(arrayOfNewRowDatas);
			}
		} else {			
			for (AnnualFlightClearance afcl : listOfAFCL) {
				for (int j = 0; j < afcl.getListOfAircrafts().size(); j++) {
					Object[] arrayOfNewAFCLDatas = (Object[]) afcl.getDatasInArray()[j];
					for (int i = 0; i < arrayOfNewRowDatas.length; i++) {
						arrayOfNewRowDatas[i] = arrayOfNewAFCLDatas[i];
					}
					tablemodel.addRow(arrayOfNewRowDatas);
				}
			}
		}
	}

	public RefillTableFromList(DefaultTableModel tablemodel, int columnNumber, SingleFlightClearance clearance) {

		Object[] arrayOfNewRowDatas = new Object[columnNumber];
		tablemodel.setRowCount(0);
		for (int i = 0; i < arrayOfNewRowDatas.length; i++) {
			arrayOfNewRowDatas[i] = clearance.getAdditonalDatasInArray()[i];
		}
		tablemodel.addRow(arrayOfNewRowDatas);
	}

}
