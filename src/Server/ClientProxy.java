package Server;

import Command.CommandContainer;
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
    
    private Map<String, CommandContainer> _m_usersCommands;
    
    
    public Map<String, CommandContainer> get_m_usersCommands() {
        return _m_usersCommands;
    }
    
    public void set_m_usersCommands(Map<String, CommandContainer> _m_usersCommands) {
        this._m_usersCommands = _m_usersCommands;
    }
    
    public CommandContainer getUserCommands(String username)
    {
        return _m_usersCommands.get(username);
    }
}
