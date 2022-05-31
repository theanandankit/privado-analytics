package com.analytics.analytics.services;

import com.analytics.analytics.client.JiraClient;
import com.analytics.analytics.dao.IOrderDAO;
import com.analytics.analytics.entity.Buyer;
import com.analytics.analytics.entity.Order;
import com.analytics.analytics.entity.Product;
import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.domain.Comment;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderService implements IOrderService {

	Logger logger = LoggerFactory.getLogger(OrderService.class);


	@Value("${jira.username}")
	private String username;

	@Value("${jira.password}")
	private String password;

	@Value("${jira.url}")
	private String jiraUrl;

	@Value("${jira.project.key}")
	private String projectKey;


	@Autowired
	private IOrderDAO dao;

	@Autowired
	private ProductService productService;

	@Autowired
	private BuyerService buyerService;

	@Autowired
	private JiraServiceImpl jiraService;

	private JiraClient jiraClient = new JiraClient(username, password, jiraUrl);

	private IssueRestClient issueClient = jiraClient.getRestClient().getIssueClient();

	@Override
	public List<Order> getOrders() {
		return dao.getOrders();
	}

	@Override
	public Order createOrder(Order order) {
		String buyerEmail = order.getBuyer().email;
		Integer buyerPincode = this.getOrderPinCode(order.getId());
		try {
			Product product = new Product();
			product.setOrderId(order.getId());

			System.out.println("Buyer Email: " + buyerEmail);
			logger.debug("Buyer Email: " + buyerEmail);
			product.setBuyerEmail(buyerEmail);
			product.setBuyerPinCode(buyerPincode);

			productService.saveProduct(product);


			Buyer buyer = order.getBuyer();
			String firstName = buyer.getFirstName();
			String lastName = buyer.getLastName();
			String dateOfBirth = buyer.getDateOfBirth();

			Buyer newBuyer = new Buyer(firstName, lastName, dateOfBirth);


			buyerService.saveProduct(newBuyer);

		}
		catch (Exception e) {

			logger.error("Exception while creating order: " +
					""+ order.getId() + "" +
					"Buyer Email"+ buyerEmail + "" +
					"buyer pin code"+ buyerPincode);
			jiraService.createIssue(1l, "Exception while creating order for "+ buyerEmail);

			IssueInput newIssue = new IssueInputBuilder(
					projectKey, 1l, buyerEmail).build();
			issueClient.createIssue(newIssue).claim().getKey();
			return null;
		}
		return dao.createOrder(order);
	}

	private Integer getOrderPinCode(Long id) {
		return 185;
	}


	@Override
	public Order updateOrder(int orderId, Order order) {
		return dao.updateOrder(orderId, order);
	}

	@Override
	public Order getOrder(int orderId) {
		return dao.getOrder(orderId);
	}

	@Override
	public boolean deleteOrder(int orderId) {
		return dao.deleteOrder(orderId);
	}

}

@Service
class JiraServiceImpl {

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

	public String createIssue(Long issueType, String email) {
		String issueSummary = "Payment Failure by" + email;
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
