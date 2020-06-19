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
}
