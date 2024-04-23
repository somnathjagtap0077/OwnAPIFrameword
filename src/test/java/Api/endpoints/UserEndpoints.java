package Api.endpoints;

import static io.restassured.RestAssured.given;

import Api.payloads.Users;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//Created for crud operation create,update,delete and Read operation
public class UserEndpoints {

	public static Response createUser(Users payload) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when().post(Routes.post_url);
		return response;
	}

	public static Response ReadUser(String username) {
		Response response = given()

				.pathParam("username", username).when().get(Routes.get_url);
		return response;
	}

	public static Response updateUser(String username, Users payload) {
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", username).body(payload).when().put(Routes.update_url);
		return response;
	}
	public static Response DeleteUser(String usernames) {
		Response response = given()
		.pathParam("username", usernames)
		.when()
		.delete(Routes.delete_url);
		return response;
	}
}
