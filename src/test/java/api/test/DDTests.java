package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.Dataproviders;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DDTests {


	@Test(priority = 1,dataProvider = "Data", dataProviderClass = Dataproviders.class)
	public void testPostUser(String userID, String userName, String fname, String lname, String useremail, String pwd, String ph)
	{
		User userPayLoad= new User();
		userPayLoad.setId(Integer.parseInt(userID));
		userPayLoad.setUsername(userName);
		userPayLoad.setFirstName(fname);
		userPayLoad.setLastName(lname);
		userPayLoad.setEmail(useremail);
		userPayLoad.setPassword(pwd);
		userPayLoad.setPhone(ph);
		Response response=UserEndPoints.createUser(userPayLoad);
		Assert.assertEquals(response.getStatusCode(), 200);
		JsonPath jsonpath=  response.jsonPath();
		//Assert.assertEquals(jsonpath.get("code"), 200);
		Assert.assertEquals(jsonpath.get("type"), "unknown");
		System.err.println(response.asString());

	}
	
	@Test(priority = 1,dataProvider = "UserNames", dataProviderClass = Dataproviders.class)
	public void testDeleteUser(String userName)
	{
		Response response=UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
}