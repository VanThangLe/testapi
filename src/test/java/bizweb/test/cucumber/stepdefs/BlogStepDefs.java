package bizweb.test.cucumber.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import bizweb.test.model.blog.BlogReponse;
import bizweb.test.model.blog.BlogsReponse;
import cucumber.api.java.en.When;
import lombok.val;

public class BlogStepDefs extends StepDefs {
	public static int idBlog;
	public static List<Integer> idBlogs = new ArrayList<>();
	
	@When("^I post a blog with Test blog = 'Test blog'$")
	public void i_post_a_blog_with_Test_blog_Test_blog() throws Throwable {
	 
	}

	@When("^I put a blog with Test blod update = 'Test blog update'$")
	public void i_put_a_blog_with_Test_blod_update_Test_blog_update() throws Throwable {

	}

	@When("^I get list blog$")
	public void i_get_list_blog() throws Throwable {
		val entity = restTemplate.getForEntity(getUrl("comments.json"), BlogsReponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@When("^I get a blog$")
	public void i_get_a_blog() throws Throwable {
		val entity = restTemplate.getForEntity(getUrl("comments/" + idBlog + ".json"), BlogReponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@When("^I delete a blog$")
	public void i_delete_a_blog() throws Throwable {
		val entity = restTemplate.exchange(getUrl("comments/" + idBlog + ".json"), HttpMethod.DELETE, null, Void.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

}
