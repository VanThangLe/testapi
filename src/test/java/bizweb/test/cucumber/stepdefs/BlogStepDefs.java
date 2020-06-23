package bizweb.test.cucumber.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

//import bizweb.test.model.blog.Blog;
import bizweb.test.model.blog.BlogRequest;
import bizweb.test.model.blog.BlogResponse;
import bizweb.test.model.blog.BlogsResponse;
import cucumber.api.java.en.When;
import lombok.val;

public class BlogStepDefs extends StepDefs {
	public static int idBlog;
	public static List<Integer> idBlogList = new ArrayList<>();

	@When("^I post a blog with ([^\"]*)$")
	public void i_post_a_blog(String name) throws Throwable {
		BlogRequest request = new BlogRequest();
		request.setName(name);
		System.out.println("----------------------------");
		System.out.println(jsonMapper.writeValueAsString(request));
		System.out.println("----------------------------");
		HttpEntity<BlogRequest> requestEntity = new HttpEntity<>(request, headers);
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

	@When("^I put a blog with ([^\"]*)$")
	public void i_put_a_blog(String name) throws Throwable {
		BlogRequest request = new BlogRequest();

//		val getForEntity = restTemplate.getForEntity(getUrl("blogs/" + idBlogList.get(0) + ".json"),
//				BlogResponse.class);
//		val request = getForEntity.getBody().getBlog();

		request.setName(name);
		System.out.println("----------------------------------------------------------");
		System.out.println(jsonMapper.writeValueAsString(request));
		System.out.println("----------------------------------------------------------");
		HttpEntity<BlogRequest> requestEntity = new HttpEntity<>(request, headers);
		val entity = restTemplate.exchange(getUrl("blogs/" + idBlogList.get(0) + ".json"), HttpMethod.PUT,
				requestEntity, BlogResponse.class);

//		System.out.println("----------------------------------------------------------");
//		System.out.println(jsonMapper.writeValueAsString(entity));
//		System.out.println("----------------------------------------------------------");

		assertThat(entity.getStatusCodeValue()).isEqualTo(200);
	}

	@When("^I get list blog$")
	public void i_get_list_blog() throws Throwable {
		val entity = restTemplate.getForEntity(getUrl("blogs" + ".json"), BlogsResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
//		System.out.println("----------------------------------------------------------");
//		System.out.println(jsonMapper.writeValueAsString(entity));
//		System.out.println("----------------------------------------------------------");
	}

	@When("^I get a blog$")
	public void i_get_a_blog() throws Throwable {
		val entity = restTemplate.getForEntity(getUrl("blogs/" + idBlogList.get(0) + ".json"), BlogResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		if (entity.getStatusCodeValue() == 200) {
			assertThat(entity.getBody().getBlog().getId()).isEqualTo(idBlogList.get(0));
//			System.out.println("----------------------------------------------------------");
//			System.out.println(jsonMapper.writeValueAsString(entity));
//			System.out.println("----------------------------------------------------------");
		}
	}

	@When("^I delete a blog$")
	public void i_delete_a_blog() throws Throwable {
		for (int i = 0; i < idBlogList.size(); i++) {
			val entity = restTemplate.exchange(getUrl("blogs/" + idBlogList.get(i) + ".json"), HttpMethod.DELETE, null,
					Void.class);
			assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
//			System.out.println("----------------------------------------------------------");
//			System.out.println(jsonMapper.writeValueAsString(entity));
//			System.out.println("----------------------------------------------------------");
			idBlogList.remove(i);
		}
	}

}
