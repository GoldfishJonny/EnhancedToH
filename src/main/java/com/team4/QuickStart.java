package com.team4;

import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.FileReader;
import java.io.Reader;

/**
 * Class that handles MongoDB stuff
 * It connects to the MongoDB database that i set up
 * It imports and exports userdata such as names and highscores
 * @author Du Tran
 */
public class QuickStart {
    private MongoClient client;
    private MongoDatabase db;
    private MongoCollection<Document> col;

    /*
    public static void main(String[] args) {
        String connectionString = System.getenv("MONGO_DB_URI");
        if (connectionString == null || connectionString.isEmpty()) {
            System.err.println("No MongoDB connection string provided!");
            return;
        }
         */
    public QuickStart() {
            this.client = MongoClients.create("mongodb+srv://dutranpgm:tAZowHn5EzpmXY3c@sample.68tcera.mongodb.net/?retryWrites=true&w=majority&appName=Sample");
            this.db = client.getDatabase("SampleDB");
            this.col = db.getCollection("SampleC");
        }

        /*
        Document sampleDoc = new Document("_id", "1").append("name", "John Smith");
        Document userDoc = new Document("username", "playerOne")
                .append("highscore1", 5000)
                .append("highscore2", 3200);
         */


    public void loadDataFromFile (String filePath){
        try (Reader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            JsonObject json = gson.fromJson(reader, JsonObject.class);
            insertDataFromJson(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertDataFromJson (JsonObject json){
        Document doc = Document.parse(json.toString());
        try {
            col.insertOne(doc);
        } catch (MongoWriteException e) {
            if (e.getError().getCode() == 11000) {
                System.out.println("A document with the same _id already exists.");
            } else {
                throw e;
            }
        }
    }

    private static void readAllDocuments(MongoCollection<Document> collection) {
        FindIterable<Document> documents = collection.find();
        for (Document doc : documents) {
            System.out.println(doc.toJson());
        }
    }
        /*
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

         */
        public void close() {
            client.close();
        }
    }

    /*
    I guess the idea now is that when something is added to the data.json file it's also pushed onto the DB
    I think maybe how this run everything the game ends and it just scans through the entire json file and adds
    non-duplicates to the DB?

    things needed:
    1. Make sure it can read data from MongoDB
    2. Create a way to keep the top 5 scores in the data.json file (it only does the first 5 scores in no order)
    3. Make a way to have different users
    4. Figure out how to pull based on username and avoid duplicate username pulls i guess?
     */
