package Server;

import java.io.File;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import DatabaseInterfaces.IFactory;
import DatabaseInterfaces.iDAO2;

public class RelationalFactory implements IFactory{
	
	private iDAO2 relationalDao;
	
	@Override
	public Boolean startTransaction() {
		try {
			
			JarFile jarFile = new JarFile("assets/relational.jar"); //Gets the jar
			Enumeration<JarEntry> e = jarFile.entries(); //Gets all the things inside the jar

			URL[] urls = { new URL("jar:file:" + "assets/relational.jar" + "!/") };
			URLClassLoader cl = URLClassLoader.newInstance(urls);
			
			while (e.hasMoreElements()) { //Runs the loop
			    JarEntry je = e.nextElement();
			    if(je.isDirectory() || !je.getName().endsWith(".class")){
			        continue; //If there's nothing that ends with .class, just go to the next element
			    }
			    // -6 because of .class
			    String className = je.getName().substring(0,je.getName().length()-6);
			    className = className.replace('/', '.');
			    Class c = cl.loadClass(className);
			    if (className.equals("RelationalDatabase.RelationalDAO")) { //If it finds the DAO class,
			    	Object object = c.newInstance(); //Get the class and cast it to an object
			    	iDAO2 holder = (iDAO2)object; //Cast to a DAO interface object
			    	relationalDao = holder.initDAO(); //call the interface method that creates a new DAO class
			    	break;
			    }
			}
						
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
		return relationalDao;
	}



}
