package bizweb.test.cucumber.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import bizweb.test.model.blog.BlogRequest;
import bizweb.test.model.blog.BlogResponse;
import bizweb.test.model.blog.BlogsResponse;
import cucumber.api.java.en.When;
import lombok.val;

public class BlogStepDefs extends StepDefs {
	public static int idBlog;
	public static List<Integer> idBlogList = new ArrayList<>();
	public static List<Integer> idArticleList = new ArrayList<>();
	public BlogRequest request = new BlogRequest();
	
	@When("^I post a blog with ([^\\\"]*)$")
	public void i_post_a_blog(String name) throws Throwable {
		request.setName(name);
		HttpEntity<BlogRequest> requestEntity = new HttpEntity<>(request, headers);
		val entity = restTemplate.postForEntity(getUrl("blogs.json"), requestEntity, BlogResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		if (entity.getStatusCodeValue() == 201) {
			assertThat(entity.getBody().getBlog().getName()).isEqualTo(name);
		}
		idBlog = entity.getBody().getBlog().getId();
	}

	@When("^I put a blog with ([^\\\"]*)$")
	public void i_put_a_blog(String name) throws Throwable {
		request.setName(name);
		HttpEntity<BlogRequest> requestEntity = new HttpEntity<>(request, headers);
		val entity = restTemplate.exchange(getUrl("blogs"+ idBlog + ".json"), HttpMethod.PUT, requestEntity, BlogResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		if(entity.getStatusCodeValue() == 200) {
			assertThat(entity.getBody().getBlog().getName()).isEqualTo(name);
		}
	}

	@When("^I get list blog$")
	public void i_get_list_blog() throws Throwable {
		val entity = restTemplate.getForEntity(getUrl("comments.json"), BlogsResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@When("^I get a blog$")
	public void i_get_a_blog() throws Throwable {
		val entity = restTemplate.getForEntity(getUrl("comments/" + idBlog + ".json"), BlogResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@When("^I delete a blog$")
	public void i_delete_a_blog() throws Throwable {
		val entity = restTemplate.exchange(getUrl("comments/" + idBlog + ".json"), HttpMethod.DELETE, null, Void.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

}
