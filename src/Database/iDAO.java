package Database;

import ServerModel.UserModel.User;

/**
 * Created by benjamin on 10/02/17.
 */
public interface iDAO {

    public User getUserFromId(int userId);

}
