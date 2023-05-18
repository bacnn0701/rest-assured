package utils;

import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProjectInfo extends ResponseInfo {

    private String projectKey;
    List<Map<String, String>> issueTypes;
    private Map<String, List<Map<String, String>>> projectInfo;

    public ProjectInfo(String baseUri, String projectKey) {
        super(baseUri);
        this.projectKey = projectKey;
        projectInfo();
    }

    private Map<String, List<Map<String, String>>> projectInfo() {
        String path = "/rest/api/3/project/".concat(projectKey);
        return projectInfo = JsonPath.from(getResponse(Method.GET,path).asString()).get();
    }

    public String getIdIssueType(String issueType) {
        String idIssueType = null;
        issueTypes = projectInfo.get("issueTypes");

        for (Map<String, String> type : issueTypes) {
            if (type.get("name").equalsIgnoreCase(issueType)) {
                idIssueType = type.get("id");
                break;
            }
        }

        if (idIssueType == null) {
            throw new IllegalArgumentException("[ERR] Could not id of " + issueType);
        }
        return idIssueType;
    }

}
