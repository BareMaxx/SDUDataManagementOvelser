package dk.sdu.mmmi.dm.healthos.persistance;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import dk.sdu.mmmi.dm.healthos.domain.*;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * @author Oliver Nordestgaard | olnor18
 */

public class PersistanceHandler implements IPersistanceHandler {
    private static final String URL = "localhost";
    private static final int PORT = 27017;
    private static final String DATABASE_NAME = "database";
    private static PersistanceHandler instance;
    private MongoDatabase database;

    private PersistanceHandler() {
        initializeMongoDatabase();
    }

    public static PersistanceHandler getInstance() {
        if (instance == null) {
            instance = new PersistanceHandler();
        }
        return instance;
    }

    private void initializeMongoDatabase() {
        try {
            CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                    fromProviders(PojoCodecProvider.builder().automatic(true).build()));
            MongoClientSettings settings = MongoClientSettings.builder()
                    .codecRegistry(pojoCodecRegistry)
                    .applyConnectionString(new ConnectionString("mongodb://" + URL + ":" + PORT))
                    .build();
            MongoClient mongoClient = MongoClients.create(settings);
            database = mongoClient.getDatabase(DATABASE_NAME);
        } finally {
            if (database == null) System.exit(-1);
        }
    }

    @Override
    public List<Employee> getEmployees() {
        MongoCollection<Employee> mongoCollection = database.getCollection("employees", Employee.class);
        return mongoCollection.find().into(new ArrayList<>());
    }

    @Override
    public Employee getEmployee(ObjectId id) {
        MongoCollection<Employee> mongoCollection = database.getCollection("employees", Employee.class);
        return mongoCollection.find(Filters.eq("_id", new ObjectId(String.valueOf(id)))).first();
    }

    @Override
    public boolean createEmployee(Employee employee) {
        MongoCollection<Employee> mongoCollection = database.getCollection("employees", Employee.class);
        mongoCollection.insertOne(employee);
        return true;
    }

    /*
    Implement all of the following. Beware that the model classes are as of yet not properly implemented
    */


    @Override
    public List<Patient> getPatients() {
        throw new UnsupportedOperationException("This operation is not supported...");
    }

    @Override
    public Patient getPatient(ObjectId id) {
        throw new UnsupportedOperationException("This operation is not supported...");
    }

    @Override
    public boolean createPatient(Patient patient) {
        //make HealthOS support this action in the presentation layer too.
        throw new UnsupportedOperationException("This operation is not supported...");
    }

    @Override
    public List<Bed> getBeds() {
        throw new UnsupportedOperationException("This operation is not supported...");
    }

    @Override
    public Bed getBed(ObjectId id) {
        throw new UnsupportedOperationException("This operation is not supported...");
    }

    @Override
    public boolean createBed(Bed bed) {
        //make HealthOS support this action in the presentation layer too.
        throw new UnsupportedOperationException("This operation is not supported...");
    }

    @Override
    public List<Admission> getAdmissions() {
        throw new UnsupportedOperationException("This operation is not supported...");
    }

    @Override
    public Admission getAdmission(ObjectId id) {
        throw new UnsupportedOperationException("This operation is not supported...");
    }

    @Override
    public boolean createAdmission(Admission admission) {
        //make HealthOS support this action in the presentation layer too.
        throw new UnsupportedOperationException("This operation is not supported...");
    }

    @Override
    public boolean deleteAdmission(ObjectId id) {
        throw new UnsupportedOperationException("This operation is not supported...");
    }

}
