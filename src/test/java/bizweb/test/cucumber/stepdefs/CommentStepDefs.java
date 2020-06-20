package bizweb.test.cucumber.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import bizweb.test.model.comment.CommentResponse;
import bizweb.test.model.article.Article;
import bizweb.test.model.article.ArticleRequest;
import bizweb.test.model.article.ArticleResponse;
import bizweb.test.model.blog.Blog;
import bizweb.test.model.blog.BlogRequest;
import bizweb.test.model.blog.BlogResponse;
import bizweb.test.model.comment.Comment;
import bizweb.test.model.comment.CommentRequest;
import bizweb.test.model.comment.CommentsResponse;
import bizweb.test.model.test.SharedData;
import cucumber.api.java.en.When;
import lombok.val;
public class CommentStepDefs extends StepDefs {
	public static int idComment;
	public static int idBlog;
	public static int idArticle;
	public static List<Integer> idCommentList = new ArrayList<>();
	public static List<Integer> idBlogList = new ArrayList<>();
	public SharedData sharedData;
	public Blog blog;
	public Article article;
	
	@When("^I post a blog comment ([^\"]*)$")
	public void i_post_a_blog_comment(String name) throws Throwable {
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
	
	@When("^I post a article comment with ([^\"]*) and ([^\"]*) and ([^\"]*) and ([^\"]*)$")
	public void i_post_a_article_comment(String title, String author, String tags, String content) throws Throwable {
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
//			idArticleList.add(idArticle);
//			System.out.println(idArticleList);
			System.out.println("----------------------------------------------------------");
			assertThat(entity.getBody().getArticle().getTitle()).isEqualTo(title);
			assertThat(entity.getBody().getArticle().getAuthor()).isEqualTo(author);
			assertThat(entity.getBody().getArticle().getTags()).isEqualTo(tags);
			assertThat(entity.getBody().getArticle().getContent()).isEqualTo(content);
		}
	}

	@When("^I post a comment ([^\"]*) and ([^\"]*) and ([^\"]*)$")
	public void i_post_a_comment(String body, String author, String email) throws Throwable {
		CommentRequest requestComment = new CommentRequest();
		requestComment.setBodyComment(body);
		requestComment.setAuthor(author);
		requestComment.setEmail(email);
		requestComment.setBlogId(idBlog);
		requestComment.setArticleId(idArticle);
		Blog blog = sharedData.getBlog();
		requestComment.setBlogId(blog.getId());
		Article article = sharedData.getArticle();
		requestComment.setArticleId(article.getId());
//		sharedData.setBlog(entity.getBody().getBlog());
		System.out.println("----------------------------");
		System.out.println(jsonMapper.writeValueAsString(requestComment));
		System.out.println("----------------------------");
		HttpEntity<CommentRequest> requestEntity = new HttpEntity<>(requestComment, headers);
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
			assertThat(entity.getBody().getComment().getBlogid()).isEqualTo(blog.getId());
			assertThat(entity.getBody().getComment().getArticleId()).isEqualTo(article.getId());
		}
	}

	@When("^I put a comment with body equal '(.*)'$")
	public void i_put_a_comment(String body) throws Throwable {
		val getForEntity = restTemplate.getForEntity(getUrl("comments/" + idCommentList.get(0) + ".json"),
				CommentResponse.class);
		val request = getForEntity.getBody().getComment();
		request.setBodyComment(body);
		HttpEntity<Comment> requestEntity = new HttpEntity<>(request, headers);
		val entity = restTemplate.exchange(getUrl("comments/"+ idCommentList.get(0) + ".json"), HttpMethod.PUT,
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