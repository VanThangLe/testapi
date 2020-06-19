package bizweb.test.model.blog;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogsResponse {
	private List<Blog> blogs;
}
