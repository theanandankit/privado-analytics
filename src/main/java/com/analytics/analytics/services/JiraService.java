package com.analytics.analytics.services;

import com.analytics.analytics.client.JiraClient;
import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Comment;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class JiraService {

    @Value("${jira.username}")
    private String username;

    @Value("${jira.password}")
    private String password;

    @Value("${jira.url}")
    private String jiraUrl;

    @Value("${jira.project.key}")
    private String projectKey;


    private JiraClient jiraClient = new JiraClient(username, password, jiraUrl);
    private IssueRestClient issueClient = jiraClient.getRestClient().getIssueClient();

    public String createIssue(Long issueType, String issueSummary) {
        IssueInput newIssue = new IssueInputBuilder(
                projectKey, issueType, issueSummary).build();
        return issueClient.createIssue(newIssue).claim().getKey();
    }

    public Issue getIssue(String issueKey) {
        return issueClient
                .getIssue(issueKey)
                .claim();
    }

    public void addComment(Issue issue, String commentBody) {
        jiraClient.getRestClient().getIssueClient()
                .addComment(issue.getCommentsUri(), Comment.valueOf(commentBody));
    }

    public List<Comment> getAllComments(String issueKey) {
        return StreamSupport.stream(getIssue(issueKey).getComments().spliterator(), false)
                .collect(Collectors.toList());
    }

}
