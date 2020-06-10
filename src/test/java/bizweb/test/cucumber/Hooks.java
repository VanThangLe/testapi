package bizweb.test.cucumber;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	@Before
	public void createData(){
		System.out.println("Create data");
	}
	
	@After
	public void deleteData(){
		System.out.println("Delete Data");
	}
	
	@Before("@product")
	public void loginSuccess(){
		System.out.println("Login success!!!");
	}
	
	@After("@product")
	public void logOut(){
		System.out.println("Logout success!!!");
	}
}
