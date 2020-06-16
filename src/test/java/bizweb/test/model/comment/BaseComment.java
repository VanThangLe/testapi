package bizweb.test.model.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseComment {
	private String body;
	private String author;
	private String email;
}
