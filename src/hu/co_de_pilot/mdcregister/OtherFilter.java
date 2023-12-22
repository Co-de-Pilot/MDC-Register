package hu.co_de_pilot.mdcregister;

import java.util.function.Predicate;

public class OtherFilter implements Predicate<FlightClearance> {
	
	private String filtertype;
	private boolean modified;
	private boolean active;
	private boolean approved;
	private boolean flownAway;
	
	public OtherFilter(String filtertype, boolean approved, boolean active, boolean modified, boolean flownAway) {
		this.filtertype = filtertype;
		this.modified = modified;
		this.active = active;
		this.approved = approved;
		this.flownAway = flownAway;
	}
	
	public OtherFilter(String filtertype, boolean approved, boolean active, boolean flownAway) {
		this.filtertype = filtertype;
		if (filtertype.equals("print")) {
			this.active = active;
			this.approved = approved;
			this.flownAway = flownAway;
		} else {
			this.active = active;
			this.approved = approved;
			this.modified = flownAway;			
		}
	}
	
	@Override
	public boolean test(FlightClearance clearance) {
		boolean isFiltered = false;
		if (filtertype.equals("print")) {
			if (clearance.isActive() == active && clearance.isApproved() == approved && clearance.isFlownAway() == flownAway) {
				isFiltered = true;
			}
		} else if (filtertype.equals("annual")) {
			if (clearance.isActive() == active && clearance.isApproved() == approved && clearance.isModified() == modified) {
				isFiltered = true;
			}
		} else {
			if (clearance.isModified() == modified && clearance.isActive() == active && clearance.isApproved() == approved
					&& clearance.isFlownAway() == flownAway) {
				isFiltered = true;
			}
		}
		return isFiltered;
	}

}
