package pages.lists;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.AddToListRequestBody;
import models.CreateListRequestBody;
import pages.BasePage;

import static io.restassured.RestAssured.given;

public class ListsPage extends BasePage {

    private final String sessionID = dotenv.get("SESSION_ID");

    public Response getListDetails(String listID) {
        return requestSpecification.when().get("/list/" + listID);
    }

    public Response createList(CreateListRequestBody createListRequestBody) {
        if (createListRequestBody != null) {
            return requestSpecification.queryParam("session_id", sessionID)
                    .contentType(ContentType.JSON).body(createListRequestBody).when().post("/list");
        }
        return requestSpecification.queryParam("session_id", sessionID)
                .contentType(ContentType.JSON).when().post("/list");
    }
    public Response addToList(AddToListRequestBody addToListRequestBody, String listID) {
        if (addToListRequestBody != null) {
            return requestSpecification.queryParam("session_id", sessionID)
                    .contentType(ContentType.JSON).body(addToListRequestBody).when()
                    .post("/list/" + listID + "/add_item");
        }
        return requestSpecification.queryParam("session_id", sessionID).when()
                .post("/list/" + listID + "/add_item");
    }
    public Response clearList(String listID){
        return requestSpecification
                .queryParam("session_id", sessionID)
                .queryParam("confirm", "true").when()
                .post("/list/"+listID+"/clear");
    }

    public Response deleteList(String listID){
        return requestSpecification
                .queryParam("session_id", sessionID)
                .when()
                .delete("/list/"+listID);
    }

}
