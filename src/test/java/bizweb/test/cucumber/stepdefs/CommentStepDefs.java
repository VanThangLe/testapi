package bizweb.test.cucumber.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import bizweb.test.model.comment.CommentResponse;
import bizweb.test.model.comment.CommentRequest;
import bizweb.test.model.comment.CommentsReponse;
import cucumber.api.java.en.When;
import lombok.val;

public class CommentStepDefs extends StepDefs {
	public static int idComment;
	public static List<Integer> idComments = new ArrayList<>();

	@When("^I post a comment")
	public void i_post_a_comment(String body, String author, String email) throws Throwable {
		CommentRequest request = new CommentRequest();
		request.setBody(body);
		request.setAuthor(author);
		request.setEmail(email);
		HttpEntity<CommentRequest> requestEntity = new HttpEntity<>(request, headers);
		val entity = restTemplate.postForEntity(getUrl("comments.json"), requestEntity, CommentResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		if (entity.getStatusCodeValue() == 201) {

			assertThat(entity.getBody().getCustomer().getEmail()).isEqualTo(email);
		}
		idCustomer = entity.getBody().getCustomer().getId();
	}

	@When("^I put a comment")
	public void i_put_a_comment(String body, String author, String email) throws Throwable {

	}

	@When("^I get list comments$")
	public void i_get_list_comments() throws Throwable {
		val entity = restTemplate.getForEntity(getUrl("comments.json"), CommentsReponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@When("^I get a comment$")
	public void i_get_a_comment() throws Throwable {
		val entity = restTemplate.getForEntity(getUrl("comments/" + idComment + ".json"), CommentReponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@When("^I delete comment$")
	public void i_delete_comment() throws Throwable {
		val entity = restTemplate.exchange(getUrl("comments/" + idComment + ".json"), HttpMethod.DELETE, null,
				Void.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}
