package bizweb.test.model.comment;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentsReponse {
	private List<Comment> comment;
}