package com.thomaspfund.checkconsult.convert;

import com.mongodb.DBObject;
import com.thomaspfund.checkconsult.dto.MonthResume;

public class MonthResumeConverter {

	public static MonthResume getMonthResume(DBObject result) {
		MonthResume resume = new MonthResume();
		
		DBObject key = (DBObject) result.get("_id");
		resume.setMonth((int) key.get("month"));
		resume.setYear((int) key.get("year"));
		
		resume.setConsultationPrice(getDoubleValue(result.get("consultationPrice")));
		resume.setMaterialPrice(getDoubleValue(result.get("materialPrice")));
		resume.setMedicamentPrice(getDoubleValue(result.get("medicamentPrice")));
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
