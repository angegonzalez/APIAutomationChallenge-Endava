package pages.movies;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.RateMovieRequestBody;
import pages.BasePage;

public class MoviesPage extends BasePage {
    private final String sessionID = dotenv.get("SESSION_ID");

    public Response getMovieDetails(String movieID) {
        return requestSpecification.get("/movie/" + movieID);
    }

    public Response rateMovie(String movieID, RateMovieRequestBody rateMovieRequestBody) {
        return requestSpecification.queryParam("session_id", sessionID)
                .contentType(ContentType.JSON)
                .body(rateMovieRequestBody)
                .post("/movie/" + movieID + "/rating");
    }


}
