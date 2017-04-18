package MongoDatabase;

import Client.IClient;
import DatabaseInterfaces.IUserDAO;
import Server.IServer;
import UserModel.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.IterableCodec;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raulbr on 4/15/17.
 */
public class MongoUserDAO implements IUserDAO {
    public static IUserDAO _SINGLETON = new MongoUserDAO();
    private MongoDatabase _mongoDatabase;
    private MongoCollection _usersCollection;
    private int AUTH_TOKEN_LENGTH = 15;

    public MongoUserDAO() {
        //Grabs the mongo database
        _mongoDatabase = MongoDB.get_mongoDB();

        //Grabs the collection or table where all the users data is located
        _usersCollection = _mongoDatabase.getCollection("users");
        System.out.println("MongoUserDAO constructor done");
    }

    /*
        MongoDB stores data records as BSON documents.
        BSON is a binary representation of JSON documents, though it contains more data types than JSON
     */

    @Override
    public Boolean login(String userName, String password) throws SQLException, IClient.InvalidUsername, IClient.InvalidPassword, IServer.UserAlreadyLoggedIn {
        //User user = new User();
        try {
            BasicDBObject query = new BasicDBObject();
            query.put("username", userName);
            MongoCursor cursor = _usersCollection.find(query).iterator();
            if (!cursor.hasNext()) {
                return false;
            }
            Document row = (Document)cursor.next();
            if (row == null) {
                return false;
            }
            //user.set_S_username(row.getString("username"));
            //user.set_S_password(row.getString("password"));
            //user.set_S_token(row.getString("token"));

            String userPassword = row.getString("password");
            if (password.equals(userPassword)) {
                return true;
            }

        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": LOGIN MONGO " + e.getMessage() );
        }
        return false;
    }

    @Override
    public Boolean registerUser(String userName, String password) throws SQLException {
        // Creating and adding a document which is comparable to a row in a RDBMS
        try {
            Document newUserDoc = new Document().
                    append("username", userName).
                    append("password", password).
                    append("token", makeToken());

            _usersCollection.insertOne(newUserDoc);
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": REGISTERUSER MONGO " + e.getMessage() );
            return false;
        }
        return true;
    }

    @Override
    public void updateUserToken(String username, String authenticationCode) {
        try {
            BasicDBObject newDocument = new BasicDBObject();
            newDocument.put("token", authenticationCode);

            BasicDBObject searchQuery = new BasicDBObject().append("username", username);
            _usersCollection.updateOne(searchQuery, newDocument);
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": UPDATEUSERTOKEN MONGO " + e.getMessage() );
        }
    }

    @Override
    public User getUserByUserName(String username) throws SQLException, IServer.UserAlreadyLoggedIn {
        User user = new User();
        try {
            BasicDBObject query = new BasicDBObject();
            query.put("username", username);
            MongoCursor cursor = _usersCollection.find(query).iterator();
            if(!cursor.hasNext()){return user;}

            Document row = (Document)cursor.next();
            user.set_S_username(row.getString("username"));
            user.set_S_password(row.getString("password"));
            user.set_S_token(row.getString("token"));

        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": GETUSERBYUSERNAME MONGO " + e.getMessage() );
        }
        return user;
    }

    @Override
    public User getUserByAccessToken(String token) throws SQLException {
        User user = new User();
        try {
            BasicDBObject query = new BasicDBObject();
            query.put("token", token);
            MongoCursor cursor = _usersCollection.find(query).iterator();

            Document row = (Document)cursor.next();
            user.set_S_username(row.getString("username"));
            user.set_S_password(row.getString("password"));
            user.set_S_token(row.getString("token"));

        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> listOfUsers = new ArrayList<>();
        try {
             try (MongoCursor<Document> cursor = _usersCollection.find().iterator()) {
                 while (cursor.hasNext()) {
                     Document row = (Document)cursor.next();
                     User user = new User();
                     user.set_S_username(row.getString("username"));
                     user.set_S_password(row.getString("password"));
                     user.set_S_token(row.getString("token"));
                     listOfUsers.add(user);
                 }
             }
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": GETALLUSERS MONGO " + e.getMessage() );
        }
        return listOfUsers;
    }

    private String makeToken()
    {
        return RandomGenerator.getInstance().randomUUID().substring(0, AUTH_TOKEN_LENGTH);
    }

}
