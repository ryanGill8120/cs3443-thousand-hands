
/*
 *	jie134 - Ryan Gill
 *	CS-3443-003
 *	Dr. Rathore
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import application.InvalidUserException;

import java.util.Map.Entry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/********************************************************************
 * 			Model - Covid-19 Donation app
 ********************************************************************
 *Purpose:
 *		Model class for the app. Contains most backend logic and handling
 *		of files.
 *
 *
 ********************************************************************
 */

public class Model {
	
	//Instantiations of all classes used in data structure for the app
		public static boolean need = true;
		public static HashMap<String, String> hash = new HashMap<String, String>();
		public static HashMap<String, String> users = new HashMap<String, String>();
	    public static Properties prop = new Properties();
	    public static Properties userProp = new Properties();
	    public static File file = new File("data.properties");
	    public static File userFile = new File("users.properties");
		public static ObservableList<String> obsInventory = FXCollections.observableArrayList();
		
		/*************************************************************************************
		 * 				addItem()
		 *************************************************************************************
		 *
		 *	Parameters:
		 *		item: String
		 *		amount: String
		 *	Return Type: 
		 *		boolean
		 *
		 *	Adds the selected item to inventory, handles new and existing items
		 *	
		 *************************************************************************************
		 */
		public static boolean addItem(String item, String amount) throws IOException {
			
			FileOutputStream writer = new FileOutputStream(file,true);
			
			//checks if item is already in inventory, then adds user's amount to current value
			if (hash.containsKey(item)) {
				int add = (int)Integer.parseInt(amount);
				hash.replace(item, "" + (add + (int)Integer.parseInt(hash.get(item))));
				prop.putAll(Model.hash);
				prop.store(writer,null);
				return true;
				
				
			//otherwise creates a new entry
			}else {
				hash.put(item, amount);
				prop.putAll(Model.hash);
				prop.store(writer,null);
				return false;
				
			}
			
		}
		
		/*************************************************************************************
		 * 				getNumberOfItemsInInventory()
		 *************************************************************************************
		 *
		 *	Parameters:
		 *		data: String
		 *		
		 *	Return Type: 
		 *		int
		 *
		 *	Determines number of items in inventory, will handle normal queries, not found errors
		 *  
		 *	
		 *************************************************************************************
		 */
		public static int getNumberOfItemsInInventory(String data) {
			if (hash.size() > 0) {
		    	for (Entry<String, String> entry: hash.entrySet()) {
		    		
		    		if(entry.getKey().equals(data)) {
			    		return Integer.parseInt(entry.getValue());
			    		
		    		}
		    	}
			}
		    	
		    	
			return 0;
		}
		
		/*************************************************************************************
		 * 				subtractItem()
		 *************************************************************************************
		 *
		 *	Parameters:
		 *		item: String
		 *		amount: String
		 *	Return Type: 
		 *		int
		 *
		 *	Removes the selected item to inventory if the item exists, handles item not found
		 *  and not enough inventory issues. Returns the int value of the difference if a 
		 *  proper subtraction was made.
		 *	
		 *************************************************************************************
		 */
		
		public static int subtractItem(String item, String amount) throws IOException {
			
			FileOutputStream writer = new FileOutputStream(file,true);
			if (hash.containsKey(item)) {
				if ((int)Integer.parseInt(hash.get(item)) >= (int)Integer.parseInt(amount)) {
					int difference = (int)Integer.parseInt(hash.get(item)) - (int)Integer.parseInt(amount);  //deduction from inventory
					
					//replaces the amount of the item with the difference after user received a donation
					Model.hash.replace(item, "" + difference);
					Model.prop.putAll(Model.hash);
					Model.prop.store(writer,null);  //writes to file
					writer.close();
					return difference;
				}else {
					writer.close();
					return 0;
				}
			}else {
				writer.close();
				return -1;
			}
			
			
		}
		
		/*************************************************************************************
		 * 				addUserName()
		 *************************************************************************************
		 *
		 *	Parameters:
		 *		user: String
		 *		
		 *	Return Type: 
		 *		boolean
		 *
		 *	Determines if the userName is in proper format, and adds it to file with saved 
		 *  usernames
		 *	
		 *************************************************************************************
		 */
		
		public static boolean addUserName(String user) throws IOException, InvalidUserException {
			
			if (!verifyUser(user)) {
				throw new InvalidUserException("Invalid username. Not in form abc123");
			}
			
			if (!users.containsKey(user)) {
				FileOutputStream writer = new FileOutputStream(userFile,true);
				users.put(user, "user");
				Model.userProp.putAll(Model.users);
				Model.userProp.store(writer, user);
				writer.close();
				return false;
			}
			
			return true;
		}
		/*********************************************
		 * 				verifyUser()
		 *********************************************
		 *
		 *	Parameters:
		 *		text: String
		 *	Return Type: 
		 *		boolean
		 *
		 *	Tests if text is a valid String for use
		 *  as a username. Checks if
		 *  the string is in the form abc123
		 *********************************************
		 */
		public static boolean verifyUser(String text) {
			
			boolean valid = true;
			//empty field or too long
			if (text.length() == 0 || text.length() > 6)
				valid = false;
			
			//checks for at least one alphabetical char
			for (int i = 0; i < text.length(); i++) {
				char c = text.charAt(i);
				if (i < 3) {
					if (!(c >= 'a' && c <= 'z'))
						valid = false;
				}else{
					if (!(c >= '0' && c <= '9'))
						valid = false;
				}
				
			}
			return valid;   //no alphabetical char found
		}
		
		/*********************************************
		 * 				verifyInt()
		 *********************************************
		 *
		 *	Parameters: 
		 *		text: String
		 *	Return Type: 
		 *		boolean
		 *
		 *	Checks if the text string is valid for use
		 *	in the item quantity field. Invalid if the 
		 *	string cannot be parsed to an int
		 *********************************************
		 */
		public static boolean verifyInt(String text) {
			
			//empty
			if (text.length() == 0)
				return false;
			
			//compares unicode values in string
			for (int i = 0; i < text.length(); i++) {
				char c = text.charAt(i);
				if (c < '0' || c > '9')
					return false;	//non-int char found
			}
			return true;
		}
		
		/*********************************************
		 * 				verifyProduct()
		 *********************************************
		 *
		 *	Parameters: 
		 *		text: String
		 *	Return Type: 
		 *		boolean
		 *
		 *	Checks if the text string is valid for use
		 *	in the item quantity field. Invalid if the 
		 *	string cannot be parsed to an int
		 *********************************************
		 */
		
		public static boolean verifyProduct(String text) {
			//empty
			if (text.length() == 0)
				return false;
			for (int i = 0; i < text.length(); i++) {
				char c = text.charAt(i);
				if (!(c == ' '|| (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')))
					return false;
			}
			return true;
		}
		/*********************************************
		 * 				loadFile()
		 *********************************************
		 *
		 *	Parameters:
		 *		none
		 *	Return Type: 
		 *		none
		 *
		 *	used to load the data.properties file at app
		 *  launch (called in main)
		 *********************************************
		 */
		public static void loadFiles() throws IOException{
			
			//loads the file from application directory
			FileInputStream reader=new FileInputStream(file);
			FileInputStream userReader = new FileInputStream(userFile);
			prop.load(reader);
			userProp.load(userReader);
			reader.close();
			userReader.close();
			
			//iterates through the file and adds values to HashMap
			for(Object key: prop.stringPropertyNames()){
	        	hash.put(key.toString(), prop.get(key).toString());
	        }
			//iterates through the file and adds values to HashMap
			for(Object key: userProp.stringPropertyNames()){
	        	users.put(key.toString(), userProp.get(key).toString());
	        }
		}
		
		
		//development method, used to create an initial inventory for the user
		public static void initInventory() throws IOException {
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

}
