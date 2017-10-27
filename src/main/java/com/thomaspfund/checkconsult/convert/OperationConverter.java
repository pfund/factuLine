package com.thomaspfund.checkconsult.convert;

import java.util.Date;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.thomaspfund.checkconsult.entity.Operation;

public class OperationConverter implements Converter<Operation> {

	public static String OPERATION_COLLECTION_NAME = "operation";
	
	public String getCollectionName() {
		return OPERATION_COLLECTION_NAME;
	}
	
	@Override
	public Operation getFromMongo(DBObject object) {
		Operation operation = new Operation();
		
		operation.setId(((ObjectId)object.get("_id")).toStringMongod());
		operation.setDateOperation((Date) object.get("dateOperation"));
		operation.setFirstName((String) object.get("firstName"));
		operation.setLastName((String) object.get("lastName"));
		operation.setOperationType((String) object.get("operationType"));
		operation.setOperationHospital((String) object.get("operationHospital"));
		operation.setAmount(getDouble(object.get("amount")));
		operation.setAssistantOne((String) object.get("assistantOne"));
		operation.setAssistantOneAmount(getDouble(object.get("assistantOneAmount")));
		operation.setAssistantTwo((String) object.get("assistantTwo"));
		operation.setAssistantTwoAmount(getDouble(object.get("assistantTwoAmount")));
		operation.setPaymentRecievedDate((Date) object.get("paymentRecievedDate"));
		operation.setAssistantsPaidDate((Date) object.get("assistantsPaidDate"));
		operation.setComment((String) object.get("comment"));
		
		return operation;
	}
	
	/**
	 * Converts a mongodb object into a double (if possible). Sometimes we get Integers, so we convert them into doubles.
	 * @param o the mongodb object that we want to convert into a double
	 * @return the double value of the given o object
	 */
	private static Double getDouble(Object o) {
		if (o instanceof Integer) {
			return ((Integer) o).doubleValue();
		} else {
			return (Double) o;
		}
	}
	
	public DBObject getMongoObject(Operation operation) {
		DBObject object = new BasicDBObject();
		
		if (operation.getId() != null) {
			object.put("_id", new ObjectId(operation.getId()));
		}
		object.put("dateOperation", operation.getDateOperation());
		object.put("firstName", operation.getFirstName());
		object.put("lastName", operation.getLastName());
		object.put("operationType", operation.getOperationType());
		object.put("operationHospital", operation.getOperationHospital());
		object.put("amount", operation.getAmount());
		object.put("assistantOne", operation.getAssistantOne());
		object.put("assistantOneAmount", operation.getAssistantOneAmount());
		object.put("assistantTwo", operation.getAssistantTwo());
		object.put("assistantTwoAmount", operation.getAssistantTwoAmount());
		object.put("paymentRecievedDate", operation.getPaymentRecievedDate());
		object.put("assistantsPaidDate", operation.getAssistantsPaidDate());
		object.put("comment", operation.getComment());

		return object;
	}

}
