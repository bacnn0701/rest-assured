package test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.BodyRequest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class SimpleTest {

    public static void main(String[] args) {

        String baseURL = "https://jsonplaceholder.typicode.com";

        // Request scope
        RequestSpecification request = given();
        request.baseUri(baseURL);
        request.basePath("/todos");

        // Response scope
        final String FIRST_TODO = "/1";
        Response response = request.get(FIRST_TODO);
//        response.prettyPrint();
//        response.then().body("userId",equalTo(1));
//        response.then().body("id",equalTo(1));
//        response.then().body("title",equalTo("delectus aut autem"));
//        response.then().body("completed",equalTo(false));

        BodyRequest bodyRequest = response.getBody().as(BodyRequest.class);
        System.out.println(bodyRequest.getCompleted());

    }
}
