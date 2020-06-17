package bizweb.test.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"html:target/site/cucumber-pretty", "json:target/cucumber.json"},
		features = "src/test/resources/features/Comment.feature")
					//"src/test/resources/features/Blog.feature")
					//"src/test/resources/features/Article.feature")
public class CucumberTest  {

}
