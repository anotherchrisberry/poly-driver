#PolyDriver - a flexible WebDriver implementation

This Grails plugin provides an easily configured multi-browser WebDriver implementation, along with simple mechanisms to declare browser preference and allow browser switching between tests.

To configure multiple drivers, declare them like so in your GebConfig.groovy script:
```groovy
import PolyDriver

driver = {
    FirefoxDriver firefoxDriver = new FirefoxDriver()
    SafariDriver safariDriver = new SafariDriver()
    new PolyDriver(mainDriver: firefoxDriver, alternateDrivers: [safariDriver])
}
```

When you run your tests, they will execute using the main driver.

This plugin is written specifically with Spock and Geb in mind.

Most Geb specifications extend the GebReportingSpec. To enable automatic driver selection, your specification class should extend PolyDriverGebReportingSpec:

```groovy
class SomeFeatureFunctionalSpec extends GebReportingSpec {
	...
}
```

becomes:
```groovy
import com.polydriver.spec.PolyDriverGebReportingSpec

class SomeFeatureFunctionalSpec extends PolyDriverGebReportingSpec {
	...
}
```

If you want this test to run in Safari, annotate it with `@PreferredDriver`:
```groovy
import com.polydriver.spec.PolyDriverGebReportingSpec
import com.polydriver.driver.PreferredDriver
import com.polydriver.driver.Driver

@PreferredDriver(Driver.SAFARI)
class SomeFeatureFunctionalSpec extends PolyDriverGebReportingSpec {
	...
}
```

Now, all tests in this class will run in Safari.

You can specify a list of preferred browsers - the driver will try to match them in the order specified, falling back to the mainDriver if no preferred drivers are configured:
```groovy
import com.polydriver.spec.PolyDriverGebReportingSpec
import com.polydriver.driver.PreferredDriver
import com.polydriver.driver.Driver

@PreferredDriver([Driver.SAFARI, Driver.CHROME, Driver.PHANTOM])
class SomeFeatureFunctionalSpec extends PolyDriverGebReportingSpec {
	// depending on which alternate drivers are configured, will try Safari, then Chrome, then PhantomJS
	...
}
```

##Notes on included WebDriver implementations
To provide the configuration mechanism, the plugin includes a number of WebDriver implementations. You may want to exclude these dependencies from your application and include different versions of them.
I will try to keep this up to date with the latest versions, but they change frequently. Feel free to submit a pull request if they seem out of date.

##What is implemented
Just three interfaces: WebDriver, JavascriptExecutor, TakesScreenshot. Those are the only three I've used but it's trivial to add support for additional interfaces. Let me know (or submit a pull request) if you want one.