package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayLoad;
	Logger logger;
	@BeforeClass
	public void setupData()
	{
		faker= new Faker();
		userPayLoad= new User();
		userPayLoad.setId(faker.idNumber().hashCode());
		userPayLoad.setUsername(faker.name().username());
		userPayLoad.setFirstName(faker.name().firstName());
		userPayLoad.setLastName(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		userPayLoad.setPassword(faker.internet().password(5, 10));
		userPayLoad.setPhone(faker.phoneNumber().cellPhone());
		//logs
		
		logger= LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority = 1)
	public void testPostUser()
	{
		logger.info("************************Create User Started******************************");
		logger.info(userPayLoad.toString());
		
		Response response=UserEndPoints.createUser(userPayLoad);
		logger.info(response.asString());
		Assert.assertEquals(response.getStatusCode(), 200);
		JsonPath jsonpath=  response.jsonPath();
		//Assert.assertEquals(jsonpath.get("code"), 200);
		Assert.assertEquals(jsonpath.get("type"), "unknown");
		logger.info("************************User created******************************");
	}
	
	@Test(priority = 2)
	public void testGetUserByName()
	{
		System.out.println(userPayLoad);
		Response response=UserEndPoints.readUser(this.userPayLoad.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		JsonPath jsonpath= response.jsonPath();
		Assert.assertEquals(jsonpath.get("username"), this.userPayLoad.getUsername());
		System.err.println(response.asString());
	}
	
	
	@Test(priority = 3)
	public void testUpdateUserByName()
	{
		userPayLoad.setFirstName(faker.name().firstName());
		userPayLoad.setLastName(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		
		System.out.println(userPayLoad);
		Response response=UserEndPoints.updateUser(this.userPayLoad.getUsername(),userPayLoad);
		Assert.assertEquals(response.getStatusCode(), 200);
		JsonPath jsonpath= response.jsonPath();
		System.err.println(response.asString());
	}
	
	
	@Test(priority = 4)
	public void testDeleteUserByName()
	{
		System.out.println(userPayLoad);
		Response response=UserEndPoints.deleteUser(this.userPayLoad.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		JsonPath jsonpath= response.jsonPath();
		System.err.println(response.asString());
	}
	

}
