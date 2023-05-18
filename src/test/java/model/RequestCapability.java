package model;

import io.restassured.http.Header;

public interface RequestCapability extends IAuthentication {

    String baseUrl = "https://rest-assured-learning.atlassian.net";
    Header defaultHeader = new Header("Content-Type","application/json");
    Header acceptJSONHeader = new Header("Accept","application/json");
    Header basicAuthenticationHeader = new Header("Authorization","Basic "+AuthenticationHandler.encodeCreStr(email,apiToken));

}
