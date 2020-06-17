package bizweb.test.model.blog;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseBlog {
	private int id;
	private String name;
	
	public void setName(String name) {

	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
}
