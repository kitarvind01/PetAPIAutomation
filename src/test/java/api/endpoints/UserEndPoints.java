package api.endpoints;

import api.payload.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {
	public static Response createUser(User payLoad)
	{
		Response response=RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payLoad).when().post(Routes.post_url);
		System.out.println(response.asString());
		
		System.out.println(Routes.post_url);
		return response;
	}
	
	
	public static Response readUser(String userName)
	{
		Response response=RestAssured.given().pathParam("username", userName).when().get(Routes.get_url);
		return response;
	}
	
	
	public static Response updateUser(String userName, User payLoad)
	{
		Response response=RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).pathParam("username", userName).body(payLoad).when().put(Routes.update_url);
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		System.out.println(Routes.delete_url);
		Response response=RestAssured.given().pathParam("username", userName).when().delete(Routes.delete_url);
		System.out.println(response.asString());
		return response;
	}
	
	
	
}
