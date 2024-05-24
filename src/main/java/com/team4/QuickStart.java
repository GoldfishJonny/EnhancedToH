package com.team4;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class QuickStart {
    public static void main(String[] args) {
        // Connection string from MongoDB Atlas or your MongoDB server
        String connectionString = "mongodb+srv://dutranpgm:(yS+yG3zy9*QmE$@enhancedhanoi.qdk7tyj.mongodb.net/?retryWrites=true&w=majority&appName=EnhancedHanoi";

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("LeaderboardDB"); // replace "test" with your database name
            System.out.println("Connected to MongoDB!");
            // You can now perform operations on the database

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}