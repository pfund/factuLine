package com.thomaspfund.checkconsult.convert;

import java.util.Date;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.thomaspfund.checkconsult.entity.Consult;

public class ConsultConverter implements Converter<Consult> {

	public static String CONSULT_COLLECTION_NAME = "consult";
	
	public String getCollectionName() {
		return CONSULT_COLLECTION_NAME;
	}
	
	@Override
	public Consult getFromMongo(DBObject object) {
		Consult consult = new Consult();
		
		consult.setId(((ObjectId)object.get("_id")).toStringMongod());
		consult.setOrder((Integer) object.get("order"));
		consult.setDateConsult((Date) object.get("dateConsult"));
		consult.setFirstName((String) object.get("firstName"));
		consult.setLastName((String) object.get("lastName"));
		consult.setBirthDate((Date) object.get("birthDate"));
		consult.setConsultationPrice(getDouble(object.get("consultationPrice")));
		consult.setMaterialPrice(getDouble(object.get("materialPrice")));
		consult.setMedicamentPrice(getDouble(object.get("medicamentPrice")));
		consult.setRebate(getDouble(object.get("rebate")));
		consult.setComment((String) object.get("comment"));
		
		return consult;
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
	
	public DBObject getMongoObject(Consult consult) {
		DBObject object = new BasicDBObject();
		
		if (consult.getId() != null) {
			object.put("_id", new ObjectId(consult.getId()));
		}
		object.put("order",  consult.getOrder());
		object.put("dateConsult", consult.getDateConsult());
		object.put("firstName", consult.getFirstName());
		object.put("lastName", consult.getLastName());
		object.put("birthDate", consult.getBirthDate());
		object.put("consultationPrice", consult.getConsultationPrice());
		object.put("materialPrice", consult.getMaterialPrice());
		object.put("medicamentPrice", consult.getMedicamentPrice());
		object.put("rebate", consult.getRebate());
		object.put("comment", consult.getComment());

		return object;
	}

}
