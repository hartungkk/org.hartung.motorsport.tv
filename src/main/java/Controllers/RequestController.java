package Controllers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;


import java.io.FileInputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class RequestController {

    private RequestSpecification requestSpecification;

    public RequestController(String pathToApi) {

        String appConfigPath = "src/resources/requestSettings.properties";

        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream(appConfigPath);
            property.load(fis);
        } catch (Exception e) {
            e.fillInStackTrace();
        }

        Long responseTime = Long.valueOf(property.getProperty("channelsList.responseTime"));

        requestSpecification = new RequestSpecBuilder().
                setBaseUri("http://t.motorsport.tv")
                .setBasePath(pathToApi)
                .addHeader("region", "usa_canada")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL).build();
        responseSpecification = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .expectResponseTime(Matchers.lessThan(responseTime))
                .build();

    }

    public Object getRequest() {
        Response response = given(requestSpecification).get();
        return response;
    }


}


    