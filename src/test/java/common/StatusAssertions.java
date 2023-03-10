package common;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

public class StatusAssertions {
    public void assertOK(Response response){
        Assertions.assertThat(response.statusCode()).isEqualTo(200);

    }
    public void assertCreated(Response response){
        Assertions.assertThat(response.statusCode()).isEqualTo(201);
    }
    public void assertBadRequest(Response response){
        Assertions.assertThat(response.statusCode()).isEqualTo(400);
    }
    public void assertUnauthorized(Response response){
        Assertions.assertThat(response.statusCode()).isEqualTo(401);
    }
    public void assertForbidden(Response response){
        Assertions.assertThat(response.statusCode()).isEqualTo(403);
    }
    public void assertNotFound(Response response){
        Assertions.assertThat(response.statusCode()).isEqualTo(404);
    }
}
