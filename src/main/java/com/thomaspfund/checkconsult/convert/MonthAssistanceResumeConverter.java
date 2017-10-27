package com.thomaspfund.checkconsult.convert;

import com.mongodb.DBObject;
import com.thomaspfund.checkconsult.dto.MonthAssistanceResume;

public class MonthAssistanceResumeConverter {

	public static MonthAssistanceResume getMonthAssistanceResume(DBObject result) {
		MonthAssistanceResume resume = new MonthAssistanceResume();
		
		DBObject key = (DBObject) result.get("_id");
		resume.setMonth((int) key.get("month"));
		resume.setYear((int) key.get("year"));
		
		resume.setAmount(getDoubleValue(result.get("amount")));
		resume.setNumberPaidAssistances((Integer) result.get("numberPaidAssistances")); 
		resume.setNumberAssistances((Integer) result.get("numberAssistances"));
				
		return resume;
	}

	private static Double getDoubleValue(Object object) {
		if (object instanceof Integer) {
			return new Double((Integer) object);
		} else {
			return (Double) object;
		}
	}

}
