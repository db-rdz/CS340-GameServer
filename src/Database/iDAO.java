package Database;

import java.sql.SQLException;
import java.util.List;

import ServerModel.GameModels.Game;
import ServerModel.UserModel.User;

/**
 * Created by benjamin on 10/02/17.
 */
public interface iDAO {

    public User getUserFromId(int userId);

    public int getUserId(String userName);

    public Game getGameFromId(int gameId);

    public Boolean login(String userName, String password) throws SQLException;

    public Boolean authenticateUserWithToken(String token) throws SQLException;

    public User getUserByUserName(String username) throws SQLException;

    public User getUserByAccessToken(String token) throws SQLException;

    public Boolean registerUser(String userName, String password) throws SQLException;

    public void initializeDB();

    public List<Game> getGamesByUserName(String userName) throws SQLException;

    public List<Game> getAllGames()  throws SQLException;
<<<<<<< HEAD
=======

    public List<User> getAllUsers() throws SQLException;
>>>>>>> d1407a606be8cafa8691643a6e2a0e9215c38226

}
