package com.abdullah_al_masum.journalAppByMasum;


import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class JournalApplication {

    public static void main(String[] args) {

//        String connectionString = "mongodb+srv://masum-wsd:masum-wsd-db-password@mongodbclusterofmasum.ba9aw.mongodb.net/?retryWrites=true&w=majority&appName=mongodbClusterOfMasum";
//        ServerApi serverApi = ServerApi.builder()
//                .version(ServerApiVersion.V1)
//                .build();
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .applyConnectionString(new ConnectionString(connectionString))
//                .serverApi(serverApi)
//                .build();
//        // Create a new client and connect to the server
//        try (MongoClient mongoClient = MongoClients.create(settings)) {
//            try {
//                // Send a ping to confirm a successful connection
//                MongoDatabase database = mongoClient.getDatabase("admin");
//                database.runCommand(new Document("ping", 1));
//                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
//            } catch (MongoException e) {
//                e.printStackTrace();
//            }
//        }
        
        SpringApplication app = new SpringApplication(JournalApplication.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8083"));
        app.run(args);
    }


}