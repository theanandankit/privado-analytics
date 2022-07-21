package com.analytics.analytics.client;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

import java.net.URI;

public class JiraClient {

    private String jiraAaccessKey;
    private String jiraSecret;
    private String jiraUrl;
    private JiraRestClient restClient;


    public JiraClient(String jiraAaccessKey, String jiraSecret, String jiraUrl) {
        this.jiraAaccessKey = jiraAaccessKey;
        this.jiraSecret = jiraSecret;
        this.jiraUrl = jiraUrl;
        this.restClient = getJiraRestClient();
    }

    private JiraRestClient getJiraRestClient() {
        return new AsynchronousJiraRestClientFactory()
                .createWithBasicHttpAuthentication(getJiraUri(), this.jiraAaccessKey, this.jiraSecret);
    }

    private URI getJiraUri() {
        return URI.create(this.jiraUrl);
    }

    public JiraRestClient getRestClient() {
        return restClient;
    }
}
