package com.sandrm.exp;

import java.util.logging.Logger;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;


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
	    
	    
	    //Read
	    MongoCollection<Document> docs = db.getCollection("developers2");
	    Document filterDoc = new Document();
	    filterDoc.append("specialty", "Java developer");
	    
	    Document document = docs.find(filterDoc).first();
	    log.info("Document: " + document.toString());
	    
	    //Update
	    Bson filter = eq("title", "Opanas Kovbasyuk");
		Bson query = combine(set("specialty", "DBA specialist"));
		
		UpdateResult result = docs.updateOne(filter, query);
		
		log.info("UpdateOne Status : " + result.wasAcknowledged());
		log.info("No of Record Modified : " + result.getModifiedCount());

	    Document documentAfter = docs.find(filter).first();
	    log.info("Document: " + documentAfter.toString());

		
	    //myDoc = docs.find(eq("_id", "test")).first();
	    //System.out.println(myDoc.toJson());

		//BasicDBObject searchQuery = new BasicDBObject();
		//searchQuery.put("title", "Opanas Kovbasyuk");
		
		//DBCursor cursor = table.find(searchQuery);

	    mongoClient.close();
	}		
}
