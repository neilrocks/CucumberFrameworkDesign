
package cucumber.options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",plugin={"pretty","json:target/jsonReports/cucumber-report.json","html:target/htmlreports/cucumber-report.html"},glue= "StepDefination")
public class TestRunner {
	//tags= "@DeletePlace"
}
