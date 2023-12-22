package hu.co_de_pilot.mdcregister;

import java.util.ArrayList;
import java.util.List;

public class CollectAircraftDatasFromJComp {
	
	private List<Aircraft> listOfAircrafts = new ArrayList<>();
	
	public CollectAircraftDatasFromJComp(List<List<DesignTextField>> listOfAircraftDTF) {
		
		String[] aircraftDatas = new String[3];
		for (int i = 0; i < listOfAircraftDTF.size(); i++) {
			List<DesignTextField> actualListOfDTF = listOfAircraftDTF.get(i);
			if (actualListOfDTF.get(1).isEnabled()) {
				for (int j = 0; j < actualListOfDTF.size(); j++) {
					DesignTextField actualDTF = actualListOfDTF.get(j);
					aircraftDatas[j] = actualDTF.getText();
					actualDTF.setText("");
					actualDTF.setEnabled(false);
				}
				listOfAircrafts.add(new Aircraft(aircraftDatas[0], aircraftDatas[1], aircraftDatas[2]));				
			}
		}
	}

	public List<Aircraft> getListOfAircrafts() {
		return listOfAircrafts;
	}

}
