package MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by raulbr on 4/15/17.
 */
public class MongoDB {
    private static MongoDatabase _mongoDB;

    public MongoDB() {
    	initMongoDB();
    }

    public void initMongoDB () {
        try {
            //Connecting to Mongo DB server
        	String host = InetAddress.getLocalHost().toString();
        	String[] split = host.split("/");
        	String database = "TestDev";
            String username = "user@test.COM";
            String pass = "XXXXX";
            char[] password = pass.toCharArray();
            MongoClient mongoClient = new MongoClient("127.0.0.1",27017);
            
//            List<ServerAddress> serverAddresses = new ArrayList<ServerAddress>();
//            ServerAddress address = new ServerAddress("hostname", 8081);
//            serverAddresses.add(address);
//            List<MongoCredential> credentials = new ArrayList<MongoCredential>();
//            MongoCredential credential = MongoCredential.createPlainCredential(username, "$external", password);
//            credentials.add(credential);
//            MongoClient mongoClient1 = new MongoClient(serverAddresses, credentials);

            //Connect to your MongoDB, if doesnt exist it will create one
            _mongoDB = mongoClient.getDatabase("ticketToRideMongoDB");
            System.out.println("Connect to database successfully");

            //Collections in Mongo is the same as tables in a RDBMS
            //createCollections();

        } catch(Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    //Create collections or tables
    private void createCollections() {
        _mongoDB.createCollection("users");
        _mongoDB.createCollection("games");
        _mongoDB.createCollection("usergames");
        _mongoDB.createCollection("players");
    }

    public static MongoDatabase get_mongoDB() {
        return _mongoDB;
    }
}