package bizweb.test.model.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseComment {
	private int id;
	private String body;
	private String author;
	private String email;

	public void setBodyComment(String body) {

	}

	public void setAuthor(String author) {

	}

	public void setEmail(String email) {
		
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getBodyComment() {
		return this.body;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public String getEmail() {
		return this.email;
	}
}
