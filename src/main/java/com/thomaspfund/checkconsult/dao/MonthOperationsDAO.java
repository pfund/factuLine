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
import com.thomaspfund.checkconsult.convert.MonthOperationResumeConverter;
import com.thomaspfund.checkconsult.convert.OperationConverter;
import com.thomaspfund.checkconsult.dto.MonthOperationResume;

public class MonthOperationsDAO extends AbstractDAO {

	public List<MonthOperationResume> getAllMonthOperations() throws UnknownHostException {
		
		List<MonthOperationResume> resultList = new ArrayList<>();
		
		MongoClient client = null;
		try {
			client = getClient();
			DBCollection collection = super.getCollection(client, OperationConverter.OPERATION_COLLECTION_NAME);

			DBObject project0Fields = new BasicDBObject();
			BasicDBList add2HoursList = new BasicDBList();
			add2HoursList.add("$dateOperation");
			add2HoursList.add(2 * 60 * 60 * 1_000);
			DBObject add2Hours = new BasicDBObject("$add", add2HoursList);
//			DBObject newDateOperation = new BasicDBObject("dateOperation", add2Hours);
			project0Fields.put("dateOperation", add2Hours);
			project0Fields.put("amount", "$amount");
			project0Fields.put("assistantOneAmount", "$assistantOneAmount");
			project0Fields.put("assistantTwoAmount", "$assistantTwoAmount");
			project0Fields.put("paymentRecievedDate", "$paymentRecievedDate");
			project0Fields.put("assistantsPaidDate", "$assistantsPaidDate");

			DBObject projectFields = new BasicDBObject("_id", 0);
			DBObject keyObject = new BasicDBObject();
			keyObject.put("month", new BasicDBObject("$month", "$dateOperation"));
			keyObject.put("year", new BasicDBObject("$year", "$dateOperation"));
			projectFields.put("key", keyObject);
			projectFields.put("amount", "$amount");
			projectFields.put("assistantOneAmount", "$assistantOneAmount");
			projectFields.put("assistantTwoAmount", "$assistantTwoAmount");

			BasicDBList conditionAssistantsPaidList = new BasicDBList();
			conditionAssistantsPaidList.add("$assistantsPaidDate");
			conditionAssistantsPaidList.add(1);
			conditionAssistantsPaidList.add(0);
			projectFields.put("numberAssistantPaid", new BasicDBObject("$cond", conditionAssistantsPaidList));
			
			BasicDBList assistantOneGtList = new BasicDBList();
			assistantOneGtList.add("$assistantOneAmount");
			assistantOneGtList.add(0);
			
			BasicDBList assistantTwoGtList = new BasicDBList();
			assistantTwoGtList.add("$assistantTwoAmount");
			assistantTwoGtList.add(0);

			BasicDBList orList = new BasicDBList();
			orList.add(new BasicDBObject("$gt", assistantOneGtList));
			orList.add(new BasicDBObject("$gt", assistantTwoGtList));
			
			BasicDBObject orClause = new BasicDBObject();
			orClause.put("$or", orList);

			BasicDBList conditionNumberAssistedList = new BasicDBList();
			conditionNumberAssistedList.add(orClause);
			conditionNumberAssistedList.add(1);
			conditionNumberAssistedList.add(0);
			projectFields.put("numberAssistedOperations", new BasicDBObject("$cond", conditionNumberAssistedList));
			
			BasicDBList conditionPaymentsRecievedList = new BasicDBList();
			conditionPaymentsRecievedList.add("$paymentRecievedDate");
			conditionPaymentsRecievedList.add(1);
			conditionPaymentsRecievedList.add(0);
			projectFields.put("numberPaymentsRecieved", new BasicDBObject("$cond", conditionPaymentsRecievedList));

			DBObject project0 = new BasicDBObject();
			project0.put("$project", project0Fields);
			
			DBObject project = new BasicDBObject();
			project.put("$project", projectFields);	
			
			DBObject groupFields = new BasicDBObject();
			groupFields.put("_id", "$key");
			groupFields.put("amount", new BasicDBObject("$sum", "$amount"));
			groupFields.put("assistantOneAmount", new BasicDBObject("$sum", "$assistantOneAmount"));
			groupFields.put("assistantTwoAmount", new BasicDBObject("$sum", "$assistantTwoAmount"));
			groupFields.put("numberAssistantPaid", new BasicDBObject("$sum", "$numberAssistantPaid"));
			groupFields.put("numberAssistedOperations", new BasicDBObject("$sum", "$numberAssistedOperations"));
			groupFields.put("numberOperations", new BasicDBObject("$sum", 1));
			groupFields.put("numberPaymentsRecieved",  new BasicDBObject("$sum", "$numberPaymentsRecieved"));

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
				resultList.add(MonthOperationResumeConverter.getMonthOperationResume(result));
			}
		} finally {
			if (client != null) {
				client.close();
			}
		}

		return resultList;
	}

}
