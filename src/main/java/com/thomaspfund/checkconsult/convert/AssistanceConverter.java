package com.thomaspfund.checkconsult.convert;

import java.util.Date;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.thomaspfund.checkconsult.entity.Assistance;

public class AssistanceConverter implements Converter<Assistance> {

	public static String ASSISTANCE_COLLECTION_NAME = "assistance";
	
	public String getCollectionName() {
		return ASSISTANCE_COLLECTION_NAME;
	}
	
	@Override
	public Assistance getFromMongo(DBObject object) {
		Assistance assistance = new Assistance();
		
		assistance.setId(((ObjectId)object.get("_id")).toStringMongod());
		assistance.setDateAssistance((Date) object.get("dateAssistance"));
		assistance.setOperator((String) object.get("operator"));
		assistance.setFirstName((String) object.get("firstName"));
		assistance.setLastName((String) object.get("lastName"));
		assistance.setOperationType((String) object.get("operationType"));
		assistance.setOperationHospital((String) object.get("operationHospital"));
		assistance.setAmount(getDouble(object.get("amount")));
		assistance.setPaidDate((Date) object.get("paidDate"));
		assistance.setComment((String) object.get("comment"));
		
		return assistance;
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
	
	public DBObject getMongoObject(Assistance assistance) {
		DBObject object = new BasicDBObject();
		
		if (assistance.getId() != null) {
			object.put("_id", new ObjectId(assistance.getId()));
		}
		object.put("dateAssistance", assistance.getDateAssistance());
		object.put("operator",  assistance.getOperator());
		object.put("operationType", assistance.getOperationType());
		object.put("operationHospital", assistance.getOperationHospital());
		object.put("firstName", assistance.getFirstName());
		object.put("lastName", assistance.getLastName());
		object.put("amount", assistance.getAmount());
		object.put("paidDate", assistance.getPaidDate());
		object.put("comment", assistance.getComment());

		return object;
	}

}
