package utils;

import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class IssueInfo extends ResponseInfo {
    public Function<String, Map<String, String>> getIssueInfo = issueKey -> {

        String path = "/rest/api/3/issue/".concat(issueKey);

        Map<String, Object> fields = JsonPath.from(getResponse(Method.GET,path).getBody().asString()).get("fields");
        Map<String, Object> status = (Map<String, Object>) fields.get("status");
        Map<String, Object> statusCategory = (Map<String, Object>) status.get("statusCategory");

        String actualSummary = fields.get("summary").toString();
        String actualName = statusCategory.get("name").toString();

        Map<String, String> taskInfo = new HashMap<>();
        taskInfo.put("summary",actualSummary);
        taskInfo.put("name", actualName);
        return taskInfo;
    };

    public IssueInfo(String baseUri) {
        super(baseUri);
    }
}
