package bizweb.test.model.comment;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentsResponse {
	private List<Comment> comments;
}