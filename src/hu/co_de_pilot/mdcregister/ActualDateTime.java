package hu.co_de_pilot.mdcregister;

import java.time.LocalDateTime;

public class ActualDateTime {
	
	LocalDateTime actualDateTime = LocalDateTime.now();
	
	public String printDate() {
		StringBuilder dateBuilder = new StringBuilder();
		dateBuilder.append(actualDateTime.getYear());
		dateBuilder.append(".");
		if (actualDateTime.getMonthValue() < 10) {
			dateBuilder.append("0");
		}
		dateBuilder.append(actualDateTime.getMonthValue());
		dateBuilder.append(".");
		if (actualDateTime.getDayOfMonth() < 10) {
			dateBuilder.append("0");
		}
		dateBuilder.append(actualDateTime.getDayOfMonth());
		dateBuilder.append("-n ");
		return dateBuilder.toString();
	}
	
	public String printTime() {
		StringBuilder timeBuilder = new StringBuilder();
		if (actualDateTime.getHour() < 10) {
			timeBuilder.append("0");
		}
		timeBuilder.append(actualDateTime.getHour());
		if (actualDateTime.getMinute() < 10) {
			timeBuilder.append("0");
		}
		timeBuilder.append(actualDateTime.getMinute());
		timeBuilder.append("LT-kor.");
		return timeBuilder.toString();
	}
	
	public String printFileNameDateTime() {
		StringBuilder fileNameBuilder = new StringBuilder();
		fileNameBuilder.append(actualDateTime.getYear());
		fileNameBuilder.append("_");
		if (actualDateTime.getMonthValue() < 10) {
			fileNameBuilder.append("0");
		}
		fileNameBuilder.append(actualDateTime.getMonthValue());
		fileNameBuilder.append("_");
		if (actualDateTime.getDayOfMonth() < 10) {
			fileNameBuilder.append("0");
		}
		fileNameBuilder.append(actualDateTime.getDayOfMonth());
		fileNameBuilder.append("_");
		if (actualDateTime.getHour() < 10) {
			fileNameBuilder.append("0");
		}
		fileNameBuilder.append(actualDateTime.getHour());
		if (actualDateTime.getMinute() < 10) {
			fileNameBuilder.append("0");
		}
		fileNameBuilder.append(actualDateTime.getMinute());
		fileNameBuilder.append("LT");
		return fileNameBuilder.toString();
	}
	
	
}
