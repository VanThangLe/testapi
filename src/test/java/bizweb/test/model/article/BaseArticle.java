package bizweb.test.model.article;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseArticle {
	private int id;
	private String title;
	private String author;
	private String tags;
	private String content;

	public void setTitle(String title) {

	}

	public void setAuthor(String author) {

	}

	public void setTags(String tags) {

	}

	public void setContent(String content) {

	}

	public Integer getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public String getAuthor() {
		return this.author;
	}

	public String getContent() {
		return this.content;
	}
}
