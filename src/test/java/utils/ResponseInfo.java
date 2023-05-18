package utils;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.RequestCapability;


import static io.restassured.RestAssured.given;


public class ResponseInfo implements RequestCapability {

    protected String baseUri;
    protected final RequestSpecification request = given();

    public ResponseInfo(String baseUri) {
        this.baseUri = baseUri;
    }

    public RequestSpecification getRequest() {
        return request;
    }

    public Response getResponse(Method method, String path) {
        Response response = null;
        request.baseUri(baseUri);
        request.header(basicAuthenticationHeader);
        //Send request
        switch (method) {
            case GET:
                response = request.get(path);
                break;
            case DELETE:
                response = request.delete(path);
                break;
        }
        if (response == null ) {
            throw new IllegalArgumentException("[ERR] Response can't null");
        }

        return response;
    }

    public Response getResponse(Method method, Object bodyData, String path) {

        request.baseUri(baseUri);
        request.header(defaultHeader);
        request.header(acceptJSONHeader);
        request.header(basicAuthenticationHeader);

        Response response = null;
        switch (method) {
            case POST:
                response = request.body(bodyData).post(path);
                break;
            case PUT:
                response = request.body(bodyData).put(path);
                break;
            case PATCH:
                response = request.body(bodyData).patch(path);
                break;
            default:
                throw new IllegalArgumentException("[ERR] Input correct method");
        }

        if (response == null ) {
            throw new IllegalArgumentException("[ERR] Response can't null");
        }

        return response;
    }
}
