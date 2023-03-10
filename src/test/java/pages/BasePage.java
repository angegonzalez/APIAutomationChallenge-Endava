package pages;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BasePage {
    public final Dotenv dotenv = Dotenv.configure().load();
    public RequestSpecification requestSpecification;
    public void prepareRequest() {
        String apiKey = dotenv.get("API_KEY");
        requestSpecification = given().queryParam("api_key", apiKey);
    }
}
