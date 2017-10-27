package com.thomaspfund.checkconsult.convert;

import com.mongodb.DBObject;

public interface Converter<T> {

	String getCollectionName();
	
	T getFromMongo(DBObject object);
	
	DBObject getMongoObject(T consult);
}
