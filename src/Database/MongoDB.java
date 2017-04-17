package Database;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;

/**
 * Created by raulbr on 4/15/17.
 */
public class MongoDB {
    private static MongoDatabase _mongoDB;

    public MongoDB() {

    }

    public void initMongoDB () {
        try {
            //Connecting to Mongo DB server
            MongoClient mongoClient = new MongoClient("localhost", 27017);

            //Connect to your MongoDB, if doesnt exist it will create one
            _mongoDB = mongoClient.getDatabase("ticketToRideMongoDB");
            System.out.println("Connect to database successfully");

            //Collections in Mongo is the same as tables in a RDBMS
            createCollections();

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