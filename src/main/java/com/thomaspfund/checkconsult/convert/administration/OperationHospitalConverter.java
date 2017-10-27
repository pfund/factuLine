package com.thomaspfund.checkconsult.convert.administration;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.thomaspfund.checkconsult.convert.Converter;
import com.thomaspfund.checkconsult.entity.administration.OperationHospital;

public class OperationHospitalConverter implements Converter<OperationHospital> {

	@Override
	public String getCollectionName() {
		return "administration_operationHospital";
	}

	@Override
	public OperationHospital getFromMongo(DBObject object) {
		OperationHospital operationHospital = new OperationHospital();
		
		operationHospital.setId(((ObjectId)object.get("_id")).toStringMongod());
		operationHospital.setName((String) object.get("name"));
		return operationHospital;
	}

	@Override
	public DBObject getMongoObject(OperationHospital operationHospital) {
		DBObject object = new BasicDBObject();
		
		if (operationHospital.getId() != null) {
			object.put("_id", new ObjectId(operationHospital.getId()));
		}
		object.put("name", operationHospital.getName());
		return object;
	}

}
