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
import bizweb.test.model.blog.BlogRequest;
import bizweb.test.model.blog.BlogResponse;
import cucumber.api.java.en.When;
import lombok.val;

public class ArticleStepDefs extends StepDefs {
	public static int idArticle;
	public static int idBlog;
	public static List<Integer> idArticleList = new ArrayList<>();
	public static List<Integer> idBlogList = new ArrayList<>();

	@When("^I post a blog article with ([^\"]*)$")
	public void i_post_a_blog_article(String name) throws Throwable {
		BlogRequest requestBlog = new BlogRequest();
		requestBlog.setName(name);
		System.out.println("----------------------------");
		System.out.println(jsonMapper.writeValueAsString(requestBlog));
		System.out.println("----------------------------");
		HttpEntity<BlogRequest> requestEntity = new HttpEntity<>(requestBlog, headers);
		val entity = restTemplate.postForEntity(getUrl("blogs" + ".json"), requestEntity, BlogResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		if (entity.getStatusCodeValue() == 201) {
			System.out.println("----------------------------------------------------------");
			idBlog = entity.getBody().getBlog().getId();
			idBlogList.add(idBlog);
			System.out.println(idBlogList);
			System.out.println("----------------------------------------------------------");
			assertThat(entity.getBody().getBlog().getName()).isEqualTo(name);
		}
	}

	@When("^I post a article with ([^\"]*) and ([^\"]*) and ([^\"]*) and ([^\"]*)$")
	public void i_post_a_article(String title, String author, String tags, String content) throws Throwable {
		ArticleRequest requestArticle = new ArticleRequest();
		requestArticle.setTitle(title);
		requestArticle.setAuthor(author);
		requestArticle.setTags(tags);
		requestArticle.setContent(content);
		System.out.println("----------------------------");
		System.out.println(jsonMapper.writeValueAsString(requestArticle));
		System.out.println("----------------------------");
		HttpEntity<ArticleRequest> requestEntity = new HttpEntity<>(requestArticle, headers);
		val entity = restTemplate.postForEntity(getUrl("blogs/" + idBlogList.get(0) + "/articles" + ".json"),
				requestEntity, ArticleResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		if (entity.getStatusCodeValue() == 201) {
			System.out.println("----------------------------------------------------------");
			idArticle = entity.getBody().getArticle().getId();
			idArticleList.add(idArticle);
			System.out.println(idArticleList);
			System.out.println("----------------------------------------------------------");
			assertThat(entity.getBody().getArticle().getTitle()).isEqualTo(title);
			assertThat(entity.getBody().getArticle().getAuthor()).isEqualTo(author);
			assertThat(entity.getBody().getArticle().getTags()).isEqualTo(tags);
			assertThat(entity.getBody().getArticle().getContent()).isEqualTo(content);
		}
	}

	@When("^I put a article with ([^\"]*)$")
	public void i_put_a_article(String title) throws Throwable {
		ArticleRequest requestArticle = new ArticleRequest();
		requestArticle.setTitle(title);
		HttpEntity<ArticleRequest> requestEntity = new HttpEntity<>(requestArticle, headers);
		val entity = restTemplate.exchange(getUrl("blogs/" + idBlogList.get(0) + "/articles/" + idArticleList.get(0) + ".json"),
				HttpMethod.PUT, requestEntity, ArticleResponse.class);
		assertThat(entity.getStatusCodeValue()).isEqualTo(200);
//		if (entity.getStatusCodeValue() == 200) {
//			assertThat(entity.getBody().getArticle().getTitle()).isEqualTo(title);
//		}
	}

	@When("^I get list articles$")
	public void i_get_list_articles() throws Throwable {
		val entity = restTemplate.getForEntity(getUrl("blogs/" + idBlogList.get(0) + "/articles" + ".json"),
				ArticlesResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@When("^I get a article$")
	public void i_get_a_article() throws Throwable {
		val entity = restTemplate.getForEntity(getUrl("blogs/" + idBlogList.get(0) + "/articles/" + idArticleList.get(0) + ".json"),
				ArticleResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		if (entity.getStatusCodeValue() == 200) {
			assertThat(entity.getBody().getArticle().getId()).isEqualTo(idArticleList.get(0));
		}
	}

	@When("^I delete a article$")
	public void i_delete_a_article() throws Throwable {
		for (int i = 0; i < idArticleList.size(); i++) {
			val entity = restTemplate.exchange(getUrl("blogs/" + idBlogList.get(i) + "/articles/" + idArticleList.get(i) + ".json"),
					HttpMethod.DELETE, null, Void.class);
			assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
			idBlogList.remove(i);
			idArticleList.remove(i);
		}
	}
}
