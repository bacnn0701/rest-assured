package api_flow;

import builder.BodyUpdateTransition;
import builder.BuildIssueContent;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.IssueFields;
import org.apache.commons.lang3.RandomStringUtils;
import utils.IssueInfo;
import utils.ResponseInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IssueFlow extends ResponseInfo {
    private String projectKey;
    private String issueTypeStr;
    private Response response;
    private String createdIssueKey;
    private IssueFields issueBodyData;
    private String status;
    private String issuePathPrefix;
    private static Map<String, String> transitionMap = new HashMap<>();

    static {

        transitionMap.put("11", "To do");
        transitionMap.put("21", "In progress");
        transitionMap.put("31", "Done");
    }

    public IssueFlow(String projectKey, String issueTypeStr) {
        super(baseUrl);
        this.projectKey = projectKey;
        this.issueTypeStr = issueTypeStr;
        status = "To do";
        issuePathPrefix = "/rest/api/3/issue";
    }

    public void createIssue() {

        //Form up body to created Issue
        String summary = RandomStringUtils.random(20, true, true);
        issueBodyData = BuildIssueContent.build(summary, projectKey, issueTypeStr);

        //Send Request to created issue
        String pathToCreatedIssue = issuePathPrefix;
        this.response = new ResponseInfo(baseUrl).getResponse(Method.POST, issueBodyData, pathToCreatedIssue);

        this.createdIssueKey = JsonPath.from(response.getBody().asString()).get("key");
    }

    public void getIssueDetail() {

        String pathGetIssue = issuePathPrefix + createdIssueKey;
        response = new ResponseInfo(baseUrl).getResponse(Method.GET,pathGetIssue);
    }

    public void verificationIssueDetail() {

        // Print to verification
        String expectedSummary = issueBodyData.getFields().getSummary();
        String expectedName = status;

        System.out.println("expectedSummary: " + expectedSummary);
        System.out.println("actualSummary: " + new IssueInfo(baseUrl).getIssueInfo.apply(createdIssueKey).get("summary"));

        System.out.println("expectedName: " + expectedName);
        System.out.println("actualName: " + new IssueInfo(baseUrl).getIssueInfo.apply(createdIssueKey).get("name"));

    }

    public void updateIssue(String issueStatusStr) {

        String issueTransitionId = null;
        for (String transitionId : transitionMap.keySet()) {
            if (transitionMap.get(transitionId).equalsIgnoreCase(issueStatusStr)) {
                issueTransitionId = transitionId;
                break;
            }
        }

        if (issueTransitionId == null) {
            throw new RuntimeException("[ERR] issue provided not support !!!");
        }

        // Body transition update
        BodyUpdateTransition.Transition transition = new BodyUpdateTransition.Transition(issueTransitionId);
        BodyUpdateTransition bodyUpdateTransition = new BodyUpdateTransition(transition);

        //Send request update
        String pathUpdateTransition = issuePathPrefix + "/" + createdIssueKey + "/transitions";
        new ResponseInfo(baseUrl).getResponse(Method.POST,bodyUpdateTransition,pathUpdateTransition);

    }

    public void deleteIssue() {

        String pathDeleteIssue = issuePathPrefix + "/" + createdIssueKey;
        new ResponseInfo(baseUrl).getResponse(Method.DELETE, pathDeleteIssue);

        //Verify delete
        response = new ResponseInfo(baseUrl).getResponse(Method.GET, pathDeleteIssue);
        Map<String, List<String>> notExistingIssues = JsonPath.from(response.getBody().asString()).get();
        List<String> errorMsg = notExistingIssues.get("errorMessages");
        System.out.println("Error msg: " + errorMsg);
    }

}
