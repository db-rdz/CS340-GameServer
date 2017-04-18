package Server;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import DatabaseInterfaces.IFactory;
import DatabaseInterfaces.iDAO2;
import RelationalDatabase.RelationalDAO;

public class RelationalFactory implements IFactory{
	
	
	
	@Override
	public Boolean startTransaction() {
		try {
			
			File file = new File("assets/relational.jar");
			URL url = file.toURI().toURL();
			
			URLClassLoader classLoader = (URLClassLoader)ClassLoader.getSystemClassLoader();
			Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			method.setAccessible(true);
			method.invoke(classLoader, url);
						
			ServerFacade.SINGLETON.setDao(new RelationalDAO());
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
		return new RelationalDAO();
	}



}
