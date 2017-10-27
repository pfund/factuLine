package com.thomaspfund.checkconsult.convert;

import com.mongodb.DBObject;
import com.thomaspfund.checkconsult.dto.MonthOperationResume;

public class MonthOperationResumeConverter {

	public static MonthOperationResume getMonthOperationResume(DBObject result) {
		MonthOperationResume resume = new MonthOperationResume();
		
		DBObject key = (DBObject) result.get("_id");
		resume.setMonth((int) key.get("month"));
		resume.setYear((int) key.get("year"));
		
		resume.setAmount(getDoubleValue(result.get("amount")));
		resume.setAssistantOneAmount(getDoubleValue(result.get("assistantOneAmount")));
		resume.setAssistantTwoAmount(getDoubleValue(result.get("assistantTwoAmount")));
		resume.setNumberAssistantPaid((Integer) result.get("numberAssistantPaid"));
		resume.setNumberAssistedOperations((Integer) result.get("numberAssistedOperations"));
		resume.setNumberOperations((Integer) result.get("numberOperations"));
		resume.setNumberPaymentsRecieved((Integer) result.get("numberPaymentsRecieved")); 
				
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
