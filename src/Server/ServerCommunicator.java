package Server;

import Command.AddGameToServerCommand;
import Command.AddJoinableToClientCommand;
import Command.AddPlayerToServerCommand;
import Command.CommandContainer;
import Command.DeleteGameCommand;
import Command.GetCommandsCommand;
import Command.ICommand;
import Command.LoginCommand;
import Command.LogoutCommand;
import Command.RegisterCommand;
import Command.StartGameCommand;
import Command.StreamIO;
import Database.DAO;
import GameModels.Game;
import Server.IServer.GameIsFullException;
import Server.IServer.UserAlreadyLoggedIn;
import jdk.nashorn.internal.ir.CatchNode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.sun.corba.se.pept.transport.EventHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.org.apache.xpath.internal.compiler.OpCodes;

import Client.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Enumeration;


/**
 * Created by RyanBlaser on 2/6/17.
 */

public class ServerCommunicator {
    public static ServerCommunicator SINGLETON = new ServerCommunicator();

    private static final int MAX_WAITING_CONNECTION = 10;
    private HttpServer server;
    private static int SERVER_PORT_NUMBER;
    
    private Gson gson = new Gson();
    private ObjectMapper objectMapper = new ObjectMapper();
    private SimpleModule module_login = new SimpleModule();
    private SimpleModule module_add_joinable = new SimpleModule();
    private SimpleAbstractTypeResolver resolver = new SimpleAbstractTypeResolver();

    private static class InvalidAuthenticationCodeException extends Exception {

    }

    public static void main(String[] args)
    {
    	SERVER_PORT_NUMBER = Integer.parseInt(args[0]);
        SINGLETON.run();
    }
    
    private void setModules() {
    	module_login.addAbstractTypeMapping(ICommand.class, LoginCommand.class);
    	module_add_joinable.addAbstractTypeMapping(ICommand.class, AddJoinableToClientCommand.class);
    	objectMapper.registerModule(module_login);
    	objectMapper.registerModule(module_add_joinable);
    	
    	resolver.addMapping(ICommand.class, LoginCommand.class);
    }

    private void run()
    {
//    	setModules();
        try {
            server = HttpServer.create(new InetSocketAddress(SERVER_PORT_NUMBER), MAX_WAITING_CONNECTION);
            
			String probableAddress = "Could not find a likely IP choice";
            Enumeration e = NetworkInterface.getNetworkInterfaces();
			System.out.println("This machine is attached to the following IP addresses:");
			while(e.hasMoreElements())
			{
			    NetworkInterface n = (NetworkInterface) e.nextElement();
			    Enumeration ee = n.getInetAddresses();
			    while (ee.hasMoreElements())
			    {
			        InetAddress i = (InetAddress) ee.nextElement();
			        String prefix = i.getHostAddress().substring(0, 3);
			        if(prefix.contains("128") || prefix.contains("192") || prefix.contains("10"))
			        	probableAddress = i.getHostAddress();

			        System.out.println("\t" + i.getHostAddress());
			    }
			}
			if(probableAddress.equals("Could not find a likely IP choice"))
			{
				System.out.println(probableAddress);
				//System.out.println("Server not started");
				//return;
			}
			else
			{
				System.out.println("Host ip address: " + probableAddress);
				System.out.println("Host server port: " + SERVER_PORT_NUMBER + "\n");
			}
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.setExecutor(null);
        server.createContext("/command", commandHandler);
        server.createContext("/update", pollerHandler);
        server.createContext("/", defaultHandler);
        server.start();
        
    }

    //2-16-17 1:40am
    //IT WORKS NOW!!
    private HttpHandler commandHandler = new HttpHandler() {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
    
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody());
            CommandContainer input = null;
            try {
                input = objectMapper.readValue(exchange.getRequestBody(), CommandContainer.class);
                System.out.println("jackson CommandContainer: " + input.icommand.get(0));
            } catch (Exception e) {
                e.printStackTrace();
            }
    
            System.out.println("\nExecuting " + input.str_type.get(0));
    
        ICommand command = null;
        // switch all command types
        switch (input.str_type.get(0)) {
            case "AddJoinableCommand":
                command = new AddGameToServerCommand(new Game(), input.icommand.get(0).getAuthenticationCode())
                break;
            case "AddPlayerToServerCommand":
            //                	String authenticationCode = (String)input.icommand.get(0);
            //                	int gameId = (int)input.icommand.get(1);
            //                    command = new AddPlayerToServerCommand(gameId, authenticationCode);
            //command = (AddPlayerToServerCommand) input.icommand.get(0);
                break;
            //                case "DeleteGameCommand":
            //                    command = (DeleteGameCommand) input.icommand.get(0);
            //                    break;
            //                case "GetCommandsCommand":
            //                    command = (GetCommandsCommand) input.icommand.get(0);
            //                    break;
            case "LoginCommand":
                command = new LoginCommand(input.icommand.get(0).getUser());
                break;
            case "LogoutCommand":
                command = input.icommand.get(0);
                break;
            case "RegisterCommand":
                command = new RegisterCommand((User) input.icommand.get(0).getUser());
                break;
            case "StartGameCommand":
                command = input.icommand.get(0);
                break;
            default:
                throw new UnknownHostException();
        }
    
    CommandContainer response = null;
    try{
        response = command.execute();
    } catch(GameIsFullException | UserAlreadyLoggedIn e){}
    String theResponse = objectMapper.writeValueAsString(response);
    //            System.out.println("this -> client: " + theResponse);
    exchange.sendResponseHeaders(200, theResponse.length());
    StreamIO.write(exchange.getResponseBody(), theResponse);
    System.out.println("Sending back to client!");
}
};

    private HttpHandler pollerHandler = new HttpHandler() {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            System.out.println("Hello from the pollerHandler");
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody());
            CommandContainer input = null;
            try {
                input = objectMapper.readValue(exchange.getRequestBody(), CommandContainer.class);
                System.out.println("jackson CommandContainer: " + input.icommand.get(0));
                CommandContainer response = input.icommand.get(0).execute();
                String theResponse = objectMapper.writeValueAsString(response);
                exchange.sendResponseHeaders(200, theResponse.length());
                StreamIO.write(exchange.getResponseBody(), theResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private HttpHandler defaultHandler = new HttpHandler() {
        @Override
        public void handle(HttpExchange exchange) throws IOException {

        }
    };

}
