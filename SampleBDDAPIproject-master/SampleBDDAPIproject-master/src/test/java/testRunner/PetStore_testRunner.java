package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "classpath:feature", 
		glue = "stepDefinitions1", 
		monochrome = true, 
		dryRun = false,
		//tags = "@Post,@Get",
		plugin = { "pretty", "json:target/JsonReports/TestExecutionReport.json",
				"html:target/html-reports/TestExecutionReport.html", "junit:target/JunitReports/report.xml" }
)
public class PetStore_testRunner {

}
