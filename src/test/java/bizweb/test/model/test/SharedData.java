package bizweb.test.model.test;

import bizweb.test.model.article.Article;
import bizweb.test.model.blog.Blog;
import bizweb.test.model.comment.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SharedData {
	private Blog blog;
	private Comment comment;
	private Article article;
}
