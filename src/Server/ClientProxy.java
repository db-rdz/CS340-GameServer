package Server;

import Command.ICommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

/**
 * Created by RyanBlaser on 2/13/17.
 */

public class ClientProxy
{
    public static ClientProxy SINGLETON = new ClientProxy();

    public ClientProxy()
    {
        _m_usersCommands = new HashMap<>();
    }

    private Map<String, List<ICommand>> _m_usersCommands;

    public Map<String, List<ICommand>> get_m_usersCommands() {
        return _m_usersCommands;
    }

    public void set_m_usersCommands(Map<String, List<ICommand>> _m_usersCommands) {
        this._m_usersCommands = _m_usersCommands;
    }

    public List<ICommand> getUserCommands(String username, int lastCommandRecievedIndex)
    {
    	List<ICommand> debugOnly = _m_usersCommands.get(username);
    	List<ICommand> userCommands = new ArrayList<>();
    	for (int i = lastCommandRecievedIndex; i < debugOnly.size(); i++)
    	{
    		userCommands.add(debugOnly.get(i));
    	}
    		
        return userCommands;
    }
}