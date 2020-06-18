package bizweb.test.cucumber.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import bizweb.test.model.comment.CommentResponse;
import bizweb.test.model.comment.Comment;
import bizweb.test.model.comment.CommentRequest;
import bizweb.test.model.comment.CommentsResponse;
import cucumber.api.java.en.When;
import lombok.val;

public class CommentStepDefs extends StepDefs {
	public static int idComment;
	public static List<Integer> idCommentList = new ArrayList<>();

	@When("^I post a comment ([^\"]*) and ([^\"]*) and ([^\"]*) and ([^\"]*) and ([^\"]*)$")
	public void i_post_a_comment(String body, String author, String email, int blogid, int articleid) throws Throwable {
		CommentRequest request = new CommentRequest();
		request.setBodyComment(body);
		request.setAuthor(author);
		request.setEmail(email);
		request.setBlogid(blogid);
		request.setArticleid(articleid);
		System.out.println("----------------------------");
		System.out.println(jsonMapper.writeValueAsString(request));
		System.out.println("----------------------------");
		HttpEntity<CommentRequest> requestEntity = new HttpEntity<>(request, headers);
		val entity = restTemplate.postForEntity(getUrl("comments" + ".json"), requestEntity, CommentResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		if (entity.getStatusCodeValue() == 201) {
			System.out.println("----------------------------------------------------------");
			idComment = entity.getBody().getComment().getId();
			idCommentList.add(idComment);
			System.out.println(idCommentList);
			System.out.println("----------------------------------------------------------");
			assertThat(entity.getBody().getComment().getBodyComment()).isEqualTo(body);
			assertThat(entity.getBody().getComment().getAuthor()).isEqualTo(author);
			assertThat(entity.getBody().getComment().getEmail()).isEqualTo(email);
		}
		idComment = entity.getBody().getComment().getId();
	}

	@When("^I put a comment with body equal '(.*)'$")
	public void i_put_a_comment(String body) throws Throwable {
		val getForEntity = restTemplate.getForEntity(getUrl("comments/" + idCommentList.get(0) + ".json"),
				CommentResponse.class);
		val request = getForEntity.getBody().getComment();
		request.setBodyComment(body);
		HttpEntity<Comment> requestEntity = new HttpEntity<>(request, headers);
		val entity = restTemplate.exchange(getUrl("comments"+ idCommentList.get(0) + ".json"), HttpMethod.PUT,
				requestEntity, CommentResponse.class);
		System.out.println("----------------------------------------------------------");
		System.out.println(jsonMapper.writeValueAsString(entity));
		System.out.println("----------------------------------------------------------");
		assertThat(entity.getStatusCodeValue()).isEqualTo(200);
	}

	@When("^I get list comments$")
	public void i_get_list_customer() throws Throwable {
		val entity = restTemplate.getForEntity(getUrl("comments" + ".json"), CommentsResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@When("^I get a comment$")
	public void i_get_a_comment() throws Throwable {
		val entity = restTemplate.getForEntity(getUrl("comments/" + idCommentList.get(0) + ".json"), CommentResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		if(entity.getStatusCodeValue() == 200) {
			assertThat(entity.getBody().getComment().getId()).isEqualTo(idCommentList.get(0));
		}
	}

	@When("^I delete comment$")
	public void i_delete_comment() throws Throwable {
		for (int i = 0; i < idCommentList.size();i++) {
			val entity = restTemplate.exchange(getUrl("comments/" + idCommentList.get(i) + ".json"), 
					HttpMethod.DELETE, null, Void.class);
			assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		}
	}
}