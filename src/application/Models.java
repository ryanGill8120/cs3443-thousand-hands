package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Models {
	
	protected static boolean need = true;
	
	protected static HashMap<String, String> hash = new HashMap<String, String>();
    protected static Properties prop = new Properties();
    protected static File file = new File("data.properties");
    
    
	protected static ArrayList<String> users = new ArrayList<String>();
	protected static ArrayList<String> items = new ArrayList<String>();
	protected static ArrayList<Integer> amounts = new ArrayList<Integer>();
	protected static ObservableList<String> obsInventory = FXCollections.observableArrayList();
	
	//development method, used to create an initial inventory for the user
	protected static void initInventory() throws IOException {
		hash.put("Toilet Paper", "10");
		hash.put("Hand Sanitizer", "12");
		hash.put("Bleach", "34");
		hash.put("Bread", "50");
		hash.put("Detergent", "10");
		hash.put("Dog Food", "25");
		hash.put("Beans", "40");
		hash.put("Aspirin", "67");
		hash.put("Canned Soup", "10");
		prop.putAll(hash);
		FileOutputStream writer = new FileOutputStream(file,true);
        prop.store(writer,null);
        
		
	}
	
	protected static void loadFile() throws IOException{
		FileInputStream reader=new FileInputStream(file);
		prop.load(reader);
		reader.close();
		for(Object key: prop.stringPropertyNames()){
        	hash.put(key.toString(), prop.get(key).toString());
        }
	}

}
