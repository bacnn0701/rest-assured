package test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.BodyRequest;
import model.RequestCapability;

import static org.hamcrest.CoreMatchers.*;
import static io.restassured.RestAssured.*;

public class PostMethod implements RequestCapability {

    public static void main(String[] args) {

        String baseUrl = "https://jsonplaceholder.typicode.com";

        //Form up request
        RequestSpecification request = given();
        request.baseUri(baseUrl);

        //Form up header
        request.header(defaultHeader);

        //Form up body
//        String bodyRequest = "{\n" +
//                "  \"userId\": 1,\n" +
//                "  \"id\": 1,\n" +
//                "  \"title\": \"New title\",\n" +
//                "  \"body\": \"New body\"\n" +
//                "}";

        BodyRequest bodyRequest = new BodyRequest();
        bodyRequest.setId(1);
        bodyRequest.setUserId(1);
        bodyRequest.setTitle("New title");
        bodyRequest.setCompleted(false);

        //Execute request & receive response
        Response response = request.body(bodyRequest).post("/posts");
        response.prettyPrint();

        //Verification
        System.out.println(response.statusCode());
        System.out.println(response.statusLine());

        response.then().statusCode(201);
        response.then().statusLine(containsStringIgnoringCase("201 Created"));
    }
}
