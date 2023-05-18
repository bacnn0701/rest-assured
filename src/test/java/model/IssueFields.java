package model;

public class IssueFields {

    private Fields fields;

    public IssueFields(Fields fields) {
        this.fields = fields;
    }
    public Fields getFields() {
        return fields;
    }
    public void setFields(Fields fields) {
        this.fields = fields;
    }
    public static class Fields {
        private String summary;
        private Project project;
        private IssueType issuetype;

        public Fields(String summary, Project project, IssueType issuetype) {
            this.summary = summary;
            this.project = project;
            this.issuetype = issuetype;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public Project getProject() {
            return project;
        }

        public void setProject(Project project) {
            this.project = project;
        }

        public IssueType getIssuetype() {
            return issuetype;
        }

        public void setIssuetype(IssueType issuetype) {
            this.issuetype = issuetype;
        }
    }
    public static class Project {
        private String key;

        public Project(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
    public static class IssueType {
        private String id;

        public IssueType(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
