package bizweb.test.model.article;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticlesResponse {
	private List<Article> articles;
}
