package ServerModel.UserModel;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benjamin on 10/02/17.
 */
public class User implements iUser {

    //-----------------------------------------CLASS VARIALBES-------------------------------------------------//
    private int _i_ID = -1;
    private Boolean _B_isInGame = false;

    //-----------------------------------------STATIC VARIABLES------------------------------------------------//
    /**Maps a string () to a user*/
    /** Note: The function of finding a user with a determined id I think should be done here to keep us organized */
    private static List<User> _L_listOfAllUsers = new ArrayList<>();
    private static Map<Integer, User> _M_idToUser = new HashMap();

    //-----------------------------------------STATIC FUNCTIONS------------------------------------------------//
    public static User getUserWithID(int id){
        return _M_idToUser.get(id);
    }


    //-----------------------------------------SETTERS AND GETTERS---------------------------------------------//

    public int get_i_ID() { return _i_ID; }
    public void set_i_ID(int _i_ID) { this._i_ID = _i_ID; }

    public Boolean isUserInGame() { return _B_isInGame; }
    public void set_UserGameStatus(Boolean _B_isInGame) { this._B_isInGame = _B_isInGame; }

        //_____________________________________________________________________________________________________//



}
