package bizweb.test.model.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseComment {
	private int id;
	private String bodyComment;
	private String author;
	private String email;
	private int blogid;
	private int articleid;
}
