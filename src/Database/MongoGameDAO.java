package Database;

import GameModels.Game;
import ServerModel.GameModels.PlayerModel.Player;
import com.mongodb.client.MongoDatabase;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by raulbr on 4/15/17.
 */
public class MongoGameDAO implements IGameDAO {
    public static IUserDAO _SINGLETON = new MongoUserDAO();
    private MongoDatabase _mongoDatabase;

    public MongoGameDAO() {
        _mongoDatabase = MongoDB.get_mongoDB();

        System.out.println("MongoGameDAO constructor done");
    }

    /*
        MongoDB stores data records as BSON documents.
        BSON is a binary representation of JSON documents, though it contains more data types than JSON
     */

    @Override
    public void UpdateGamePlayer(int gameId, String username, int numberOfPlayersInGame) throws SQLException {

    }

    @Override
    public List<Game> getGamesByUserName(String userName) throws SQLException {
        return null;
    }

    @Override
    public void updateGameNumberOfPlayers(int gameId, int numberOfPlayersInGame) throws SQLException {

    }

    @Override
    public Boolean addGame(Game game) throws SQLException {
        return null;
    }

    @Override
    public Game getGamebyGameId(int gameId) throws SQLException {
        return null;
    }

    @Override
    public boolean createPlayerFromUserName(String userName, int gameId) throws SQLException {
        return false;
    }

    @Override
    public Player getPlayerByUserName(String userName) throws SQLException {
        return null;
    }

    @Override
    public boolean updatePlayerTrainCards(String userName, String cardColor, String number) throws SQLException {
        return false;
    }

    @Override
    public boolean updatePlayerDestCards(String userName, String cardColumn, Integer newCardType) throws SQLException {
        return false;
    }

    @Override
    public boolean updatePlayerToNewGame(String userName, int newGameId) throws SQLException {
        return false;
    }

    @Override
    public List<Game> getAllGames() throws SQLException {
        return null;
    }

    @Override
    public void deleteAllGames() throws SQLException {

    }
}
