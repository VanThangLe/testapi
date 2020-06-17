package bizweb.test.cucumber.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import bizweb.test.model.blog.BlogResponse;
import bizweb.test.model.blog.BlogsResponse;
import cucumber.api.java.en.When;
import lombok.val;

public class ArticleStepDefs extends StepDefs {
	public static int idArticle;
	public static List<Integer> idArticles = new ArrayList<>();
	
	@When("^I post a article$")
	public void i_post_a_article(String title, String author, String tags, String content) throws Throwable {

	}

	@When("^I put a article")
	public void i_put_a_article(String title, String author, String tags, String content) throws Throwable {

	}

	@When("^I get list articles$")
	public void i_get_list_articles() throws Throwable {
		val entity = restTemplate.getForEntity(getUrl("comments.json"), BlogsResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@When("^I get a article$")
	public void i_get_a_article() throws Throwable {
		val entity = restTemplate.getForEntity(getUrl("comments/" + idArticle + ".json"), BlogResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@When("^I delete a article$")
	public void i_delete_a_article() throws Throwable {
		val entity = restTemplate.exchange(getUrl("comments/" + idArticle + ".json"), HttpMethod.DELETE, null, Void.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}
