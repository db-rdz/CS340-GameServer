package Database;

import GameModels.Game;
import ServerModel.GameModels.PlayerModel.Player;
import UserModel.User;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raulbr on 4/15/17.
 */
public class MongoGameDAO implements IGameDAO {
//    public static IUserDAO _SINGLETON = new MongoUserDAO();
    private MongoDatabase _mongoDatabase;
    private MongoCollection _gamesCollection;
    private MongoCollection _playersCollection;
    private MongoCollection _usergamesCollection;
    private static int int_gameId = 1; //must initialize to 1 so the server properly counts games

    public MongoGameDAO() {
        _mongoDatabase = MongoDB.get_mongoDB();
        _gamesCollection = _mongoDatabase.getCollection("games");
        _playersCollection = _mongoDatabase.getCollection("players");
        _usergamesCollection = _mongoDatabase.getCollection("usergames");
        System.out.println("MongoGameDAO constructor done");
    }

    /*
        MongoDB stores data records as BSON documents.
        BSON is a binary representation of JSON documents, though it contains more data types than JSON
     */

    @Override
    public void UpdateGamePlayer(int gameId, String username, int numberOfPlayersInGame) throws SQLException {
        if(username == null || gameId == 0 || numberOfPlayersInGame > 5) {
            return;
        }
        String playerNumber = String.valueOf(numberOfPlayersInGame); //PlayerNumber == number of players in the game.
        try {
            BasicDBObject newDocument = new BasicDBObject();
            newDocument.put("player" + playerNumber, username);

            BasicDBObject searchQuery = new BasicDBObject().append("gameid", gameId);
            _gamesCollection.updateOne(searchQuery, newDocument);
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": UPDATEGAMEPLAYER MONGO " + e.getMessage() );
        }
    }

    @Override
    public List<Game> getGamesByUserName(String userName) throws SQLException {
        List<Game> listOfGames = new ArrayList<>();
        try {
            BasicDBObject query = new BasicDBObject();
            query.put("username", userName);
            MongoCursor cursor = _usergamesCollection.find(query).iterator();
            if (!cursor.hasNext()) {
                return listOfGames;
            }
            Document row = (Document)cursor.next();
            BasicDBList list = (BasicDBList)row.get("listofgames");
            for (int i = 0; i < list.size(); i++) {
                Game g = new Game();
                g.set_i_gameId((int)list.get(i));
            }

        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": GETGAMESBYUSERNAME MONGO " + e.getMessage() );
        }
        return listOfGames;
    }

    @Override
    public void updateGameNumberOfPlayers(int gameId, int numberOfPlayersInGame) throws SQLException {
        if (gameId == 0 || numberOfPlayersInGame > 5) {
            return;
        }
        try {
            BasicDBObject newDocument = new BasicDBObject();
            newDocument.put("numberofplayers", numberOfPlayersInGame);

            BasicDBObject searchQuery = new BasicDBObject().append("gameid", gameId);
            _gamesCollection.updateOne(searchQuery, newDocument);
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": UPDATEGAMENUMBEROFPLAYERS MONGO " + e.getMessage() );
        }
    }

    @Override
    public Boolean addGame(Game game) throws SQLException {
        if (game == null) { return false; }
        try {
            Document newUserDoc = new Document().
                    append("gameid", int_gameId++).
                    append("numberofplayers", game.get_numberOfPlayers());

            _gamesCollection.insertOne(newUserDoc);
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": ADDGAME MONGO " + e.getMessage() );
            return false;
        }
        return true;
    }

    @Override
    public Game getGamebyGameId(int gameId) throws SQLException {
        Game game = new Game();
        try {
            BasicDBObject query = new BasicDBObject();
            query.put("gameid", gameId);
            MongoCursor cursor = _gamesCollection.find(query).iterator();
            if(!cursor.hasNext()){return game;}

            Document row = (Document)cursor.next();
            game.set_i_gameId(row.getInteger("gameid"));
            game.set_numberOfPlayers(row.getInteger("numberofplayers"));
            game.setPlayer1(MongoUserDAO._SINGLETON.getUserByUserName( row.getString("player1") ));
            game.setPlayer2(MongoUserDAO._SINGLETON.getUserByUserName( row.getString("player2") ));
            game.setPlayer3(MongoUserDAO._SINGLETON.getUserByUserName( row.getString("player3") ));
            game.setPlayer4(MongoUserDAO._SINGLETON.getUserByUserName( row.getString("player4") ));
            game.setPlayer5(MongoUserDAO._SINGLETON.getUserByUserName( row.getString("player5") ));
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": GETGAMEBYGAMEID MONGO " + e.getMessage() );
        }
        return game;
    }

    @Override
    public boolean createPlayerFromUserName(String userName, int gameId) throws SQLException {
        try {
            Document newGameDoc = new Document().
                    append("username", userName).
                    append("gameid", gameId).
                    append("red", 0).
                    append("white", 0).
                    append("orange", 0).
                    append("green", 0).
                    append("blue", 0).
                    append("purple", 0).
                    append("yellow", 0).
                    append("pink", 0).
                    append("rainbow", 0).
                    append("card1", 0).
                    append("card2", 0).
                    append("card3", 0);

            _playersCollection.insertOne(newGameDoc);
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": CREATEPLAYERFROMUSERNAME MONGO " + e.getMessage() );
            return false;
        }
        return true;
    }

    @Override
    public Player getPlayerByUserName(String userName) throws SQLException {
        Player player = new Player();
        try {
            BasicDBObject query = new BasicDBObject();
            query.put("username", userName);
            MongoCursor cursor = _playersCollection.find(query).iterator();
            if(!cursor.hasNext()){return null;}

            Document row = (Document)cursor.next();
            player.set_userName(row.getString("username"));
            player.set_gameId(row.getInteger("gameid"));
            player.set_red(row.getInteger("red"));
            player.set_white(row.getInteger("white"));
            player.set_orange(row.getInteger("orange"));
            player.set_green(row.getInteger("green"));
            player.set_blue(row.getInteger("blue"));
            player.set_purple(row.getInteger("purple"));
            player.set_yellow(row.getInteger("yellow"));
            player.set_pink(row.getInteger("pink"));
            player.set_rainbow(row.getInteger("rainbow"));
            player.set_card1(row.getInteger("card1"));
            player.set_card2(row.getInteger("card2"));
            player.set_card3(row.getInteger("card3"));
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": GETPLAYERBYUSERNAME MONGO " + e.getMessage() );
        }
        return player;
    }

    @Override
    public boolean updatePlayerTrainCards(String userName, String cardColor, String number) throws SQLException {
        try {
            BasicDBObject newDocument = new BasicDBObject();
            int newNumberOfCards = getCards(userName, cardColor) + Integer.parseInt(number);
            newDocument.put(cardColor, newNumberOfCards);

            BasicDBObject searchQuery = new BasicDBObject().append("username", userName);
            _playersCollection.updateOne(searchQuery, newDocument);
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": UPDATEPLAYERTRAINCARDS MONGO " + e.getMessage() );
            return false;
        }
        return true;
    }

    private int getCards(String username, String cardColor) {
        int numberOfCards = 0;
        try {
            BasicDBObject query = new BasicDBObject();
            query.put("username", username);
            MongoCursor cursor = _playersCollection.find(query).iterator();
            if(!cursor.hasNext()){return 0;}

            Document row = (Document)cursor.next();
            numberOfCards = row.getInteger(cardColor);
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": GETCARDS MONGO " + e.getMessage() );
        }
        return numberOfCards;
    }

    @Override
    public boolean updatePlayerDestCards(String userName, String cardColumn, Integer newCardType) throws SQLException {
        try {
            BasicDBObject newDocument = new BasicDBObject();
            newDocument.put(cardColumn, newCardType);

            BasicDBObject searchQuery = new BasicDBObject().append("username", userName);
            _playersCollection.updateOne(searchQuery, newDocument);
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": UPDATEPLAYERDESTCARDS MONGO " + e.getMessage() );
            return false;
        }
        return true;
    }

    @Override
    public boolean updatePlayerToNewGame(String userName, int newGameId) throws SQLException {
        // APPARENTLY WE ARE NOT USING THIS FUNCTION SO I WONT IMPLEMENTED
        return false;
    }

    @Override
    public List<Game> getAllGames() throws SQLException {
        List<Game> listOfGames = new ArrayList<>();
        try {
            try (MongoCursor<Document> cursor = _gamesCollection.find().iterator()) {
                while (cursor.hasNext()) {
                    Document row = cursor.next();
                    Game game = new Game();
                    game.set_i_gameId(row.getInteger("gameid"));
                    game.set_numberOfPlayers(row.getInteger("numberofplayers"));
                    listOfGames.add(game);
                }
            }
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": GETALLGAMES MONGO " + e.getMessage() );
        }
        return listOfGames;
    }

    @Override
    public void deleteAllGames() throws SQLException {
        try {
            try (MongoCursor<Document> cursor = _gamesCollection.find().iterator()) {
                while (cursor.hasNext()) {
                    Document row = cursor.next();
                    _gamesCollection.deleteOne(row);
                }
            }
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": DELETEALLGAMES MONGO " + e.getMessage() );
        }
    }
}
