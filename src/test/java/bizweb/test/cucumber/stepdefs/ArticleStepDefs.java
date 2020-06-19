package bizweb.test.cucumber.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import bizweb.test.model.article.ArticleRequest;
import bizweb.test.model.article.ArticleResponse;
import bizweb.test.model.article.ArticlesResponse;
import cucumber.api.java.en.When;
import lombok.val;

public class ArticleStepDefs extends StepDefs {
	public static int idArticle;
	public static List<Integer> idArticleList = new ArrayList<>();
	public static List<Integer> idCommentList = new ArrayList<>();
	public ArticleRequest request = new ArticleRequest();
	
	@When("^I post a article with ([^\"]*) and ([^\"]*) and ([^\"]*) and ([^\"]*)$")
	public void i_post_a_article(String title, String author, String tags, String content) throws Throwable {
		request.setTitle(title);
		request.setAuthor(author);
		request.setTags(tags);
		request.setContent(content);
		HttpEntity<ArticleRequest> requestEntity = new HttpEntity<>(request, headers);
		val entity = restTemplate.postForEntity(getUrl("articles.json"), requestEntity, ArticleResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		if (entity.getStatusCodeValue() == 201) {
			assertThat(entity.getBody().getArticle().getTitle()).isEqualTo(title);
			assertThat(entity.getBody().getArticle().getAuthor()).isEqualTo(author);
			assertThat(entity.getBody().getArticle().getTags()).isEqualTo(tags);
			assertThat(entity.getBody().getArticle().getContent()).isEqualTo(content);
		}
		idArticle = entity.getBody().getArticle().getId();
	}

	@When("^I put a article with ([^\"]*) and ([^\"]*) and ([^\"]*) and ([^\"]*)")
	public void i_put_a_article(String title, String author, String tags, String content) throws Throwable {
		request.setTitle(title);
		request.setAuthor(author);
		request.setTags(tags);
		request.setContent(content);
		HttpEntity<ArticleRequest> requestEntity = new HttpEntity<>(request, headers);
		val entity = restTemplate.exchange(getUrl("articles"+ idArticle + ".json"), HttpMethod.PUT, requestEntity, ArticleResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		if(entity.getStatusCodeValue() == 200) {
			assertThat(entity.getBody().getArticle().getTitle()).isEqualTo(title);
			assertThat(entity.getBody().getArticle().getAuthor()).isEqualTo(author);
			assertThat(entity.getBody().getArticle().getTags()).isEqualTo(tags);
			assertThat(entity.getBody().getArticle().getContent()).isEqualTo(content);
		}
	}

	@When("^I get list articles$")
	public void i_get_list_articles() throws Throwable {
		val entity = restTemplate.getForEntity(getUrl("articles.json"), ArticlesResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@When("^I get a article$")
	public void i_get_a_article() throws Throwable {
		val entity = restTemplate.getForEntity(getUrl("articles/" + idArticle + ".json"), ArticleResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@When("^I delete a article$")
	public void i_delete_a_article() throws Throwable {
		val entity = restTemplate.exchange(getUrl("articles/" + idArticle + ".json"), HttpMethod.DELETE, null, Void.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}
