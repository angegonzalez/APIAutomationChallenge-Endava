package common;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.DataTableType;
import io.restassured.RestAssured;
import models.CreateListRequestBody;
import org.apache.log4j.BasicConfigurator;

import java.util.Map;

public class Common {
    @BeforeAll()
    public static void beforeAllTests(){
        BasicConfigurator.configure();
        RestAssured.baseURI = "https://api.themoviedb.org/3";
    }

    @DataTableType
    public CreateListRequestBody createSessionRequestBodyTransformer(Map<String,String> row){
        return new CreateListRequestBody(row.get("name"), row.get("description"), row.get("language"));
    }

}
