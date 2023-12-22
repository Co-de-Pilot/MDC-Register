package hu.co_de_pilot.mdcregister;

import java.time.LocalDate;
import java.util.function.Predicate;

public class PeriodFilter implements Predicate<FlightClearance> {
	
	private LocalDate dateFrom;
	private LocalDate dateUntil;
	
	public PeriodFilter(LocalDate dateFrom, LocalDate dateUntil) {
		this.dateFrom = dateFrom.minusDays(1L);
		this.dateUntil = dateUntil.plusDays(1L);
	}
	
	public PeriodFilter(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
		this.dateUntil = null;
	}
	
	@Override
	public boolean test(FlightClearance clearance) {
		boolean isWithinPeriod = false;
		if (dateUntil == null) {
			if (dateFrom.isAfter(clearance.getDateValidFrom().minusDays(1L)) &&
					dateFrom.isBefore(clearance.getDateValidUntil().plusDays(1L))) {
				isWithinPeriod = true;
			}
		} else {
			if (clearance.getDateValidFrom().isAfter(dateFrom) && clearance.getDateValidUntil().isBefore(dateUntil)) {
				isWithinPeriod = true;
			}
		}
		return isWithinPeriod;
	}

}
