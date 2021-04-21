package test;

import static org.junit.Assert.*;


import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import application.InvalidUserException;
import model.Model;

public class TestTHApp {
	
	public static String username = "qte234";
	public static String item = "Plates";
	public static int amount = 23;
	public static int subAmount = 5;
	

	//Set up include removing the test username (for multiple runs of the code)
	//I tried the same code in an AfterClass method, but it did not work there.
	@BeforeClass
	public static void setUp() throws Exception {
		Model.loadFiles();
		FileOutputStream writer = new FileOutputStream(Model.file,true);
		FileOutputStream userWriter = new FileOutputStream(Model.userFile, true);
		Model.hash.remove(item);
		Model.prop.remove(item);
		Model.prop.putAll(Model.hash);
		Model.prop.store(writer,null);  //writes to file
		writer.close();
		
		Model.users.remove(username);
		Model.userProp.remove(username);
		Model.userProp.putAll(Model.users);
		Model.userProp.store(userWriter,null);  //writes to file
		writer.close();
	}
	
	//addItem returns a boolean based on whether or not the item added was already in inventory
	//so assertion compares amount to actual quantity in the hashmap
	@Test
	public void testAddItem() throws IOException{
		Model.addItem(item, amount + "");
		assertEquals(amount + "", Model.hash.get(item));
		
	}
	
	//simple comparison of amounts
	@Test
	public void testGetNumberOfItemsInInventory() throws IOException{
		assertEquals(amount, Model.getNumberOfItemsInInventory(item));
	}
	
	//passes a subtraction expression and then compares to the return (difference) of subtractItem
	@Test
	public void testSubtractItem() throws IOException{
		assertEquals(amount - subAmount, Model.subtractItem(item, subAmount + ""));
	}
	
	//addUser returns true if the user already exists in inventory
	@Test
	public void testAddUser() throws IOException, InvalidUserException{
		assertFalse(Model.addUserName(username));
	}
	
	

}
