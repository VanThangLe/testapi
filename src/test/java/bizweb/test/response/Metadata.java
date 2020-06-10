package bizweb.test.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Metadata {
	private int total;
	private int page;
	private int limit;
}
