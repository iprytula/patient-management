import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.notNullValue;

public class AuthIntegrationTest {
	@BeforeAll
	static void setup() {
		RestAssured.baseURI = "http://localhost:4004";
	}

	@Test
	public void shouldReturnOKWithValidToken() {
		String loginPayload = """
				{
					"email": "testuser@test.com",
					"password": "password123"
				}
			""";

		Response response = RestAssured.given()
			.header("Content-Type", "application/json")
			.body(loginPayload)
			.when()
			.post("/auth/login")
			.then()
			.statusCode(200)
			.body("token", notNullValue())
			.extract()
			.response();

		String token = response.jsonPath().getString("token");

		System.out.println("Generated token: " + token);
	}

	@Test
	public void shouldReturnUnauthorizedOnInvalidLogin() {
		String loginPayload = """
				{
					"email": "invalid_testuser@test.com",
					"password": "wrong_password"
				}
			""";

		RestAssured.given()
			.header("Content-Type", "application/json")
			.body(loginPayload)
			.when()
			.post("/auth/login")
			.then()
			.statusCode(401);
	}

}
