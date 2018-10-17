import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/"}, glue = {"ru.aplana.autotests"},
        plugin = {
                "ru.aplana.autotests.util.AllureReporter",
        }
)

public class CucumberRunner {
}
