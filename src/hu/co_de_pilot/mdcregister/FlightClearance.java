package hu.co_de_pilot.mdcregister;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class FlightClearance implements Serializable {
	
	private final String regNumberOfMFA;
	private final String stateOfRegistry;
	private final LocalDate dateValidFrom;
	private final LocalDate dateValidUntil;
	private final boolean approved;
	private boolean active;
	private boolean modified;
	private boolean flownAway;


	public FlightClearance(String regNumberOfMFA, String stateOfRegistry,
			LocalDate dateValidFrom, LocalDate dateValidUntil, boolean approved,
			boolean active, boolean modified, boolean flownAway) {
		this.regNumberOfMFA = regNumberOfMFA;
		this.stateOfRegistry = stateOfRegistry;
		this.dateValidFrom = dateValidFrom;
		this.dateValidUntil = dateValidUntil;
		this.approved = approved;
		this.active = active;
		this.modified = modified;
		this.flownAway = flownAway;
	}

	public String getRegNumberOfMFA() {
		return regNumberOfMFA;
	}

	public String getStateOfRegistry() {
		return stateOfRegistry;
	}

	public LocalDate getDateValidFrom() {
		return dateValidFrom;
	}

	public LocalDate getDateValidUntil() {
		return dateValidUntil;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public boolean isApproved() {
		return approved;
	}

	public boolean isFlownAway() {
		return flownAway;
	}

	public void setFlownAway(boolean flownAway) {
		this.flownAway = flownAway;
	}
	
	
	
}
