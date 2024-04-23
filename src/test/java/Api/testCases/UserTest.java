package Api.testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import Api.endpoints.UserEndpoints;
import Api.payloads.Users;
import io.restassured.response.Response;

public class UserTest {
	Faker faker;
	Users userPayload;
	public Logger loger;
	
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new Users();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setPassword(faker.internet().password(5, 10));
		loger=LogManager.getLogger(this.getClass());
	}

	@Test(priority = 1)
	public void PostUserTest() {
		loger.info("********* Create user********");
		Response res = UserEndpoints.createUser(userPayload);
		res.then().log().all();
		Assert.assertEquals(res.statusCode(), 200);
		loger.info("********* user is created********");
	}

	@Test(priority = 2)
	public void getUserTest() {
		loger.info("********* Read user********");
		Response res = UserEndpoints.ReadUser(this.userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		// res.statusCode();
		loger.info("********* displyaed user********");
	}

	@Test(priority = 3)
	public void updateUserTest() {
		loger.info("********* updating user********");
		// update data it will regenrate with new data

		userPayload.setUsername(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		Response res = UserEndpoints.updateUser(this.userPayload.getUsername(), userPayload);
		res.then().log().all();
		res.then().log().body().statusCode(200);
		// Assert.assertEquals(res.getStatusCode(),200); you can use testNG as well

//		check data after update

		Response ressAfterupdate = UserEndpoints.ReadUser(this.userPayload.getUsername());
		Assert.assertEquals(ressAfterupdate.statusCode(), 200);

		loger.info("********* user is updated ********");
	}
	@Test(priority = 4)
	public void deteleUserTest() {
		loger.info("********* Deleting user********");
		Response res = UserEndpoints.DeleteUser(this.userPayload.getUsername());
		Assert.assertEquals(res.getStatusCode(),200);
		loger.info("********* user is deleted********");
	}
}
