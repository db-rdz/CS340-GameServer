package Database;

import ServerModel.GameModels.Game;
import ServerModel.UserModel.User;

/**
 * Created by benjamin on 11/02/17.
 */
public class DAO implements iDAO {

    public static iDAO _SINGLETON = new DAO();

    @Override
    public User getUserFromId(int userId) {
        return null;
    }

    @Override
    public Game getGameFromId(int gameId){
        return null;
    }


}
