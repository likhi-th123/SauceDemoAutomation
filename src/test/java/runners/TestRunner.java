package runners;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.cucumber.testng.AbstractTestNGCucumberTests; //base class that allows Cucumber tests to run using TestNG.
import io.cucumber.testng.CucumberOptions;

//MAKING REMOTE CHANGES IN JENKINS
@CucumberOptions(
	    features = "src/test/resources/features",
	    glue = {"stepDefinitions"}, 
	    plugin = {"pretty", "html:reports/cucumber-report.html"},
	    monochrome = true
	)
	public class TestRunner extends AbstractTestNGCucumberTests {

	    @BeforeClass
	    public static void setup() {
	        System.out.println("==== Test Execution Started ====");
	    }

	    @AfterClass
	    public static void teardown() {
	        System.out.println("==== Test Execution Finished ====");
	    }
	}

