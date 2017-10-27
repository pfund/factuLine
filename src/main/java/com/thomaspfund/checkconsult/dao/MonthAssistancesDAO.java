package com.thomaspfund.checkconsult.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.thomaspfund.checkconsult.convert.AssistanceConverter;
import com.thomaspfund.checkconsult.convert.MonthAssistanceResumeConverter;
import com.thomaspfund.checkconsult.dto.MonthAssistanceResume;

public class MonthAssistancesDAO extends AbstractDAO {

	public List<MonthAssistanceResume> getAllMonthAssistances() throws UnknownHostException {
		
		List<MonthAssistanceResume> resultList = new ArrayList<>();
		
		MongoClient client = null;
		try {
			client = getClient();
			DBCollection collection = super.getCollection(client, AssistanceConverter.ASSISTANCE_COLLECTION_NAME);

			DBObject project0Fields = new BasicDBObject();
			BasicDBList add2HoursList = new BasicDBList();
			add2HoursList.add("$dateAssistance");
			add2HoursList.add(2 * 60 * 60 * 1_000);
			DBObject add2Hours = new BasicDBObject("$add", add2HoursList);
//			DBObject newDateAssistance = new BasicDBObject("dateAssistance", add2Hours);
			project0Fields.put("dateAssistance", add2Hours);
			project0Fields.put("amount", "$amount");
			project0Fields.put("paidDate", "$paidDate");
			
			DBObject projectFields = new BasicDBObject("_id", 0);
			DBObject keyObject = new BasicDBObject();
			keyObject.put("month", new BasicDBObject("$month", "$dateAssistance"));
			keyObject.put("year", new BasicDBObject("$year", "$dateAssistance"));
			projectFields.put("key", keyObject);
			projectFields.put("amount", "$amount");
			
			BasicDBList conditionPaidDateList = new BasicDBList();
			conditionPaidDateList.add("$paidDate");
			conditionPaidDateList.add(1);
			conditionPaidDateList.add(0);
			projectFields.put("numberPaidAssistances", new BasicDBObject("$cond", conditionPaidDateList));
			
			DBObject project0 = new BasicDBObject();
			project0.put("$project", project0Fields);
			
			DBObject project = new BasicDBObject();
			project.put("$project", projectFields);	
			
			DBObject groupFields = new BasicDBObject();
			groupFields.put("_id", "$key");
			groupFields.put("amount", new BasicDBObject("$sum", "$amount"));
			groupFields.put("numberPaidAssistances",  new BasicDBObject("$sum", "$numberPaidAssistances"));
			groupFields.put("numberAssistances", new BasicDBObject("$sum", 1));

			DBObject group = new BasicDBObject();
			group.put("$group", groupFields);

			DBObject sortFields = new BasicDBObject();
			sortFields.put("_id.year", Integer.valueOf(1));
			sortFields.put("_id.month", Integer.valueOf(1));
			DBObject sort = new BasicDBObject();
			sort.put("$sort", sortFields);

			List<DBObject> pipeline = Arrays.asList(project0, project, group, sort); 
			AggregationOutput aggOutput = collection.aggregate(pipeline);
			
			for (DBObject result : aggOutput.results()) {
				resultList.add(MonthAssistanceResumeConverter.getMonthAssistanceResume(result));
			}
		} finally {
			if (client != null) {
				client.close();
			}
		}

		return resultList;
	}

}
