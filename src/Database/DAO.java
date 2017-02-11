package Database;

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


}
