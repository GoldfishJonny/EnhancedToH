package com.team4;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;
import org.bson.Document;


public class QuickStart {

    public static void main(String[] args) {
        String connectionString = System.getenv("MONGO_DB_URI");
        if (connectionString == null || connectionString.isEmpty()) {
            System.err.println("No MongoDB connection string provided!");
            return;
        }
        MongoClient client = MongoClients.create(connectionString);

        MongoDatabase db = client.getDatabase("SampleDB");
        MongoCollection<Document> col = db.getCollection("SampleC");

        Document sampleDoc = new Document("_id", "1").append("name", "John Smith");
        Document userDoc = new Document("username", "playerOne")
                .append("highscore1", 5000)
                .append("highscore2", 3200);

        try {
            col.insertOne(sampleDoc);
        } catch (MongoWriteException e) {
            if (e.getError().getCode() == 11000) {
                System.out.println("A document with the same _id already exists.");
            } else {
                throw e;
            }
        }

        readAllDocuments(col);
        client.close();
    }
    private static void readAllDocuments(MongoCollection<Document> collection) {
        FindIterable<Document> documents = collection.find();
        for (Document doc : documents) {
            System.out.println(doc.toJson());
        }
    }
}