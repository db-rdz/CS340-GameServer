package Server;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import DatabaseInterfaces.IFactory;
import DatabaseInterfaces.iDAO2;
import MongoDatabase.MongoDAO;
import RelationalDatabase.RelationalDAO;

public class MongoFactory implements IFactory {
	
	private MongoDAO mongoDAO; //TODO: change to dynamic for pass off

	@Override
	public Boolean startTransaction() {
		try {
			
			File file = new File("assets/mongo");
			URL url = file.toURI().toURL();
			
			URLClassLoader classLoader = (URLClassLoader)ClassLoader.getSystemClassLoader();
			Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			method.setAccessible(true);
			method.invoke(classLoader, url);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public void closeTransaction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearExistingData() {
		// TODO Auto-generated method stub

	}
	
	public iDAO2 getDAO() {
		return new MongoDAO();
	}

}
