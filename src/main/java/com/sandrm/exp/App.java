package com.sandrm.exp;

import java.util.logging.Logger;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

/**
 * Experience with MongoDB!
 *
 */
public class App {
	private static final int PORT = 27017;
	private static final String PROJECTDB = "projectdb";
	
	private static Logger log = Logger.getLogger(App.class.getName());
	
	public static void main(String[] args) {
		System.out.println("Test MongoDB!");

		MongoClient mongoClient = new MongoClient("localhost", PORT);
	    MongoIterable<String> strings = mongoClient.listDatabaseNames();
	    MongoCursor<String> iterator = strings.iterator();
	    while (iterator.hasNext()) {
	    	log.info("Database: {}" + iterator.next()); 
	    }

	    MongoDatabase db = mongoClient.getDatabase(PROJECTDB);
	    String name = db.getName();
	    log.info("Got: {}" +  name);

	    mongoClient.close();
	}		
}
