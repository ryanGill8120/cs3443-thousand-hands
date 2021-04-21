package test;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import application.InvalidUserException;
import model.Model;

public class TestTHApp {
	
	String username = "abc123";
	String item = "Berries";
	int amount = 23;
	int subAmount = 5;
	

	@Before
	public void setUp() throws Exception {
		Model.loadFiles();
	}
	
	@Test
	public void testAddItem() throws IOException{
		Model.addItem(item, amount + "");
		assertEquals(amount + "", Model.hash.get(item));
		
	}
	
	public void testGetNumberOfItemsInInventory() throws IOException{
		assertEquals(amount, Model.getNumberOfItemsInInventory(item));
	}
	
	public void testSubtractItem() throws IOException{
		assertEquals(amount - subAmount, Model.subtractItem(item, subAmount + ""));
	}
	
	public void testAddUser() throws IOException, InvalidUserException{
		assertFalse(Model.addUserName(username));
	}
	

}
