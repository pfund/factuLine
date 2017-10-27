package com.thomaspfund.checkconsult.dao;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public abstract class AbstractDAO {
	private static String OPENSHIFT_MONGODB_DB_URL = null;
	private static String OPENSHIFT_APP_NAME = null;
	
	static {
		OPENSHIFT_MONGODB_DB_URL = "mongodb://"
				+ getSystemPropertyOrEnv("MONGODB_USER")
				+ ":"
				+ getSystemPropertyOrEnv("MONGODB_PASS")
				+ "@"
				+ getSystemPropertyOrEnv("MONGODB_PORT_27017_TCP_ADDR")
				+ ":"
				+ getSystemPropertyOrEnv("MONGODB_SERVICE_PORT")
				+ "/"
				+ getSystemPropertyOrEnv("MONGODB_NAME");
		OPENSHIFT_APP_NAME = getSystemPropertyOrEnv("MONGODB_NAME");
	}
	
	/**
	 * Get the system property (or the environment property if system property is not found)
	 * @param propertyName the property name we are looking for
	 * @return the property value (can be null if none is found)
	 */
	private static String getSystemPropertyOrEnv(String propertyName) {
		String propertyValue = System.getProperty(propertyName);
		if (propertyValue == null) {
			propertyValue = System.getenv(propertyName);
		}
		return propertyValue;
	}
	
	/**
	 * Gets a new MongoClient
	 * @return a new MongoClient
	 * @throws UnknownHostException
	 */
    MongoClient getClient() throws UnknownHostException {
    	MongoClientURI uri = new MongoClientURI(OPENSHIFT_MONGODB_DB_URL);
		return new MongoClient(uri);
	}

    /**
     * Gets the DBCollection for the 'consult' collection using the given MongoClient.
     * @param mongoClient
     * @return the DBCollection for the 'consult' collection
     */
    DBCollection getCollection(MongoClient mongoClient, String collectionName) {

		DB db = mongoClient.getDB(OPENSHIFT_APP_NAME);
		return db.getCollection(collectionName);

	}

}
