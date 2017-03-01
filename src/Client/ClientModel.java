package Client;

import GameModels.Game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.Hashtable; // HashMap but hopefully throws exceptions if concurrently modified

class ClientModel{

    public enum GameType{
        JOINABLE, WAITING, RESUMABLE
    }

    private String str_authentication_code;
    private User user;
    private Hashtable<Integer, Game> list_joinable, list_waiting, list_resumable;
    private Hashtable<Integer, GameType> hashtable_id_to_list;

    public ClientModel(){
        list_joinable = new Hashtable<Integer, Game>();
        list_waiting = new Hashtable<Integer, Game>();
        list_resumable = new Hashtable<Integer, Game>();
        hashtable_id_to_list = new Hashtable<Integer, GameType>();
    }

    public void setAuthenticationKey(String k){
        str_authentication_code = k;
    }

    public String getAuthenticationKey(){
        return str_authentication_code;
    }

    public void setUser(User u){
        user = u;
    }

    public User getUser(){
        return user;
    }

    public void setJoinableGames(List<Game> list){
        for(Game game : list)
            addJoinableGame(game);
    }

    public Collection<Game> getJoinableGames(){
        return list_joinable.values();
    }

    public void addJoinableGame(Game game){
        hashtable_id_to_list.put(game.get_i_gameId(), GameType.JOINABLE);
        list_joinable.put(game.get_i_gameId(), game);
    }

    public void setWaitingGames(List<Game> list){
        for(Game game : list)
            addWaitingGame(game);
    }

    public Collection<Game> getWaitingGames(){
        return list_waiting.values();
    }

    public void addWaitingGame(Game game){
        hashtable_id_to_list.put(game.get_i_gameId(), GameType.WAITING);
        list_waiting.put(game.get_i_gameId(), game);
    }

    public void setResumableGames(List<Game> list){
        for(Game game : list)
            addResumableGame(game);
    }

    public Collection<Game> getResumableGames(){
        return list_resumable.values();
    }

    public void addResumableGame(Game game){
        hashtable_id_to_list.put(game.get_i_gameId(), GameType.RESUMABLE);
        list_resumable.put(game.get_i_gameId(), game);
    }

    public GameType getGameType(int gameId){
        return hashtable_id_to_list.get(gameId);
    }

    public GameType deleteGame(int gameId){
        GameType type = getGameType(gameId);
        hashtable_id_to_list.remove(gameId);
        if(type == GameType.JOINABLE)
            list_joinable.remove(gameId);
        else if(type == GameType.WAITING)
            list_waiting.remove(gameId);
        else
            list_resumable.remove(gameId);
        return type;
    }

    private void addPlayerToGameObject(String username, Game game){
        List<String> usernames = new ArrayList<>(game.get_M_idToUserInGame().keySet());
        usernames.add(username);
        
        for (String users : usernames) {
        	game.addPlayerToGame(users, game.get_M_idToUserInGame().get(users));
        }
    }

    public void addPlayer(String username, int gameId){
        Game game;
        GameType type = getGameType(gameId);
        if(type == GameType.JOINABLE){
            game = list_joinable.get(gameId).clone(); 
            list_joinable.remove(gameId);
            addPlayerToGameObject(username, game);
            list_joinable.put(gameId, game);
        }
        else if(type == GameType.WAITING){
            game = list_waiting.get(gameId).clone();
            list_waiting.remove(gameId);
            addPlayerToGameObject(username, game);
            list_waiting.put(gameId, game);
        }
        else{
            game = list_resumable.get(gameId).clone();
            list_resumable.remove(gameId);
            addPlayerToGameObject(username, game);
            list_resumable.put(gameId, game);
        }

    }
}