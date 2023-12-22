package hu.co_de_pilot.mdcregister;

import java.io.Serializable;

public class Aircraft implements Serializable {
	
	private final String registrationOfAircraft;
	private final String typeOfAircraft;
	private final String radioCallSign;

	public Aircraft(String registrationOfAircraft, String typeOfAircraft, String radioCallSign) {
		this.registrationOfAircraft = registrationOfAircraft;
		this.typeOfAircraft = typeOfAircraft;
		this.radioCallSign = radioCallSign;
	}

	public String getRegistrationOfAircraft() {
		return registrationOfAircraft;
	}

	public String getTypeOfAircraft() {
		return typeOfAircraft;
	}

	public String getRadioCallSign() {
		return radioCallSign;
	}
	
}
