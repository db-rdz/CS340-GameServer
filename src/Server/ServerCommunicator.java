package Server;

import Command.ICommand;
import Command.Phase1.AddGameToServerCommand;
import Command.Phase1.AddJoinableToClientCommand;
import Command.Phase1.AddPlayerToServerCommand;
import Command.Phase1.DeleteGameCommand;
import Command.Phase1.GetCommandsCommand;
import Command.Phase1.LoginCommand;
import Command.Phase1.LogoutCommand;
import Command.Phase1.RegisterCommand;
import Command.Phase1.StartGameCommand;
import Database.DAO;
import GameModels.Game;
import Server.IServer.GameIsFullException;
import Server.IServer.UserAlreadyLoggedIn;
import jdk.nashorn.internal.ir.CatchNode;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;
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
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


/**
 * Created by RyanBlaser on 2/6/17.
 */

public class ServerCommunicator {
    public static ServerCommunicator SINGLETON = new ServerCommunicator();
    
    private static final int MAX_WAITING_CONNECTION = 10;
    private HttpServer server;
    private static int SERVER_PORT_NUMBER;
    
    private ObjectMapper objectMapper = new ObjectMapper();
    
    
    private static class InvalidAuthenticationCodeException extends Exception {
        
    }
    
    public static void main(String[] args)
    {
        //   	SERVER_PORT_NUMBER = Integer.parseInt(args[0]);
        SERVER_PORT_NUMBER = 8080;
        SINGLETON.run();
    }
    
    private void run()
    {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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
    	@SuppressWarnings("deprecation")
		@Override
    	public void handle(HttpExchange exchange) throws IOException {
    
//    		InputStreamReader isr = new InputStreamReader(exchange.getRequestBody());
    		ICommand input = null;
    		try {
        
    			input = objectMapper.readValue(exchange.getRequestBody(), ICommand.class);
    			System.out.println("\njackson ICommand: " + input);
    			System.out.println("Executing " + input);
        
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    

	    List<ICommand> response = null;
	    try{
	        response = input.execute();
	    } catch(GameIsFullException | UserAlreadyLoggedIn e){}
	    
	    
	    OutputStream os = exchange.getResponseBody();
	    OutputStreamWriter osw = new OutputStreamWriter(os);
	    try {
	    	String theResponse = objectMapper.writerWithType(new TypeReference<List<ICommand>>() {
	    	}).writeValueAsString(response);	    
	    	exchange.sendResponseHeaders(200, theResponse.length());
	    	osw.write(theResponse);
	    	osw.close();
	    	
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    System.out.println("Sending back to client!");
    	}
    };

	private HttpHandler pollerHandler = new HttpHandler() {
		@SuppressWarnings("deprecation")
		@Override
		public void handle(HttpExchange exchange) throws IOException {
//			System.out.println("Hello from the pollerHandler");
			InputStreamReader isr = new InputStreamReader(exchange.getRequestBody());
			ICommand input = null;
			try {
				input = objectMapper.readValue(exchange.getRequestBody(), ICommand.class);
				System.out.println("\njackson Icommand: " + input);
				List<ICommand> response = input.execute();
				
			    OutputStream os = exchange.getResponseBody();
			    OutputStreamWriter osw = new OutputStreamWriter(os);
			    String theResponse = objectMapper.writerWithType(new TypeReference<List<ICommand>>() {
			            }).writeValueAsString(response);	    

			    exchange.sendResponseHeaders(200, theResponse.length());
			    osw.write(theResponse);
			    osw.close();
			    
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