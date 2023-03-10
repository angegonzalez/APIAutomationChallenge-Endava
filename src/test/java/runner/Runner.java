package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"step_definitions", "common"},
        plugin = {"pretty", "json:target/cucumber/cucumber.json"},
        tags = "@Movies and @rating and not @ignore"
)
public class Runner {
}
