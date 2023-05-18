package builder;

import model.IssueFields;
import utils.ProjectInfo;
import static model.RequestCapability.baseUrl;

public class BuildIssueContent {

    public static IssueFields build(String summary, String projectKey, String issueType) {
        IssueFields.Project project = new IssueFields.Project(projectKey);
        IssueFields.IssueType issuetype = new IssueFields.IssueType(new ProjectInfo(baseUrl,projectKey).getIdIssueType(issueType));
        IssueFields.Fields fields = new IssueFields.Fields(summary,project,issuetype);
        return new IssueFields(fields);
    }
}
