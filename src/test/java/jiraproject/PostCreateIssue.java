package jiraproject;

import api_flow.IssueFlow;
import builder.BuildIssueContent;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.IssueFields;
import model.RequestCapability;
import org.apache.commons.lang3.RandomStringUtils;
import utils.IssueInfo;
import utils.ResponseInfo;

import static io.restassured.RestAssured.given;

public class PostCreateIssue implements RequestCapability {

    public static void main(String[] args) {

        //Basic info
        String path = "/rest/api/3/issue";
        String projectKey = "RA";

        // Flow issue
        IssueFlow issueFlow = new IssueFlow(projectKey,"task");

        System.out.println("----> CREATED");
        issueFlow.createIssue();

        System.out.println("----> READ");
        issueFlow.verificationIssueDetail();

        System.out.println("----> UPDATED");
        issueFlow.updateIssue("Done");

        issueFlow.verificationIssueDetail();

        System.out.println("----> DELETED");
        issueFlow.deleteIssue();

    }
}
