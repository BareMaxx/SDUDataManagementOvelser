package dk.sdu.dm;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App
{
    public static void main( String[] args ) {

        //For at fjerne MongoDB debug beskeder i console.
        Logger.getLogger("").setLevel(Level.WARNING);

        //Manual connection to MongoDB
        String connectionSting = "mongodb://localhost:27017/";
        MongoClient mongoClient = MongoClients.create(connectionSting);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("MyWebsite");
        MongoCollection usersCollection = mongoDatabase.getCollection("Users");

        //Creating JSON object
        Document johnDoe = new Document("_id", 1)
                .append("name", "John Doe")
                .append("cpr", "123456-7890")
                .append("departments", Arrays.asList("Odense", "Aarhus", "Vejle"));

        //Printing the JSON object
        System.out.println(johnDoe.toJson());

        //Inserting document (JSON object)
        usersCollection.insertOne(johnDoe);

        //Query for user
        Document result = (Document) usersCollection.find(Filters.eq("name","John Doe")).first();
        System.out.println("Result: " + result.toJson());

        //Automatic POJO example for at lave klasser om til JSON objekter.
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionSting))
                .codecRegistry(codecRegistry).build();
        MongoClient pojoClient = MongoClients.create(mongoClientSettings);

        MongoDatabase pojoDatabase = pojoClient.getDatabase("MyWebsite");
        MongoCollection<User> pojoUsersCollection = pojoDatabase.getCollection("Users", User.class); //Returns a User class.

        //Insert a single user using POJO
        pojoUsersCollection.insertOne(new User(2, "Jane Doe", "123456-0987"));

        //Insert multiple users
        List<User> userList = new ArrayList<>();
        userList.add(new User(3, "Anne Doe", "654321-7890"));
        userList.add(new User(4, "Pete Doe", "654321-7890"));
        pojoUsersCollection.insertMany(userList);

        //Querying the database with POJO
        ArrayList<User> usersFound;
        pojoUsersCollection.find(Filters.eq("cpr", "654321-7890")).into(usersFound = new ArrayList<>());

        for (User user: usersFound) {
            System.out.println("Found user: \n \tName: " +user.getName() + " \n \tCPR: " + user.getCpr());
        }
    }
}
