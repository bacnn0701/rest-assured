package jiraproject;

import model.IAuthentication;
import model.RequestCapability;
import utils.ProjectInfo;


public class GetProject implements RequestCapability, IAuthentication {

    public static void main(String[] args) {

        // Form up request
        String baseUrl = "https://rest-assured-learning.atlassian.net";
        String projectKey = "RA";

        ProjectInfo projectInfo = new ProjectInfo(baseUrl, projectKey);
        System.out.println("ID issue type: "+projectInfo.getIdIssueType("task"));

    }

}
