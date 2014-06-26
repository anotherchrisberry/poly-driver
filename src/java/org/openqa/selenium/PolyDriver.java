package org.openqa.selenium;

import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class PolyDriver implements WebDriver, JavascriptExecutor,
		FindsById, FindsByClassName, FindsByLinkText, FindsByName,
		FindsByCssSelector, FindsByTagName, FindsByXPath,
		HasInputDevices, HasCapabilities, TakesScreenshot {

    private WebDriver mainDriver;
    private WebDriver currentDriver;
    private Map<String, WebDriver> alternateDrivers;


    public void configureDriver(String[] drivers) {
        currentDriver = mainDriver;
        for (String driver : drivers) {
            if (alternateDrivers != null && alternateDrivers.containsKey(driver)) {
                currentDriver = alternateDrivers.get(driver);
                break;
            }
        }
    }

    // setters

    public void setMainDriver(WebDriver mainDriver) {
        this.mainDriver = mainDriver;
        this.currentDriver = mainDriver;
    }

    public void setAlternateDrivers(Map<String, WebDriver> alternateDrivers) {
        this.alternateDrivers = alternateDrivers;
    }


    // WebDriver implementation

    public void get(String s) {
        currentDriver.get(s);
    }

    public String getCurrentUrl() {
        return currentDriver.getCurrentUrl();
    }

    public String getTitle() {
        return currentDriver.getTitle();
    }

    public List<WebElement> findElements(By by) {
        return currentDriver.findElements(by);
    }

    public WebElement findElement(By by) {
        return currentDriver.findElement(by);
    }

    public String getPageSource() {
        return currentDriver.getPageSource();
    }

    public void close() {
        currentDriver.close();
    }

    public void quit() {
        for (WebDriver driver : alternateDrivers.values()) {
            driver.quit();
        }
        if (mainDriver != null) {
            mainDriver.quit();
        }
    }

    public Set<String> getWindowHandles() {
        return currentDriver.getWindowHandles();
    }

    public String getWindowHandle() {
        return currentDriver.getWindowHandle();
    }

    public TargetLocator switchTo() {
        return currentDriver.switchTo();
    }

    public Navigation navigate() {
        return currentDriver.navigate();
    }

    public Options manage() {
        return currentDriver.manage();
    }


    // JavascriptExecutor implementation

    public Object executeScript(String s, Object... objects) {
        if (currentDriver instanceof JavascriptExecutor) {
            return ((JavascriptExecutor) currentDriver).executeScript(s, objects);
        }
        throw new IllegalStateException("Current driver does not implement JavascriptExecutor!");
    }

    public Object executeAsyncScript(String s, Object... objects) {
        if (currentDriver instanceof JavascriptExecutor) {
            return ((JavascriptExecutor) currentDriver).executeAsyncScript(s, objects) ;
        }
        throw new IllegalStateException("Current driver does not implement JavascriptExecutor!");
    }


    // TakesScreenshot implementation
    public <X> X getScreenshotAs(OutputType<X> xOutputType) throws WebDriverException {
        if (currentDriver instanceof TakesScreenshot) {
            return ((TakesScreenshot) currentDriver).getScreenshotAs(xOutputType);
        }
        throw new IllegalStateException("Current driver does not implement TakesScreenshot!");
    }

	// FindsByClassName implementation
	@Override
	public WebElement findElementByClassName(String using)
	{
		return ((FindsByClassName) currentDriver).findElementByClassName(using);
	}

	@Override
	public List<WebElement> findElementsByClassName(String using)
	{
		return ((FindsByClassName) currentDriver).findElementsByClassName(using);
	}

	// FindsByCssSelector implementation
	@Override
	public WebElement findElementByCssSelector(String using)
	{
		return ((FindsByCssSelector) currentDriver).findElementByCssSelector(using);
	}

	@Override
	public List<WebElement> findElementsByCssSelector(String using)
	{
		return ((FindsByCssSelector) currentDriver).findElementsByCssSelector(using);
	}

	// FindsById implementation
	@Override
	public WebElement findElementById(String using)
	{
		return ((FindsById) currentDriver).findElementById(using);
	}

	@Override
	public List<WebElement> findElementsById(String using)
	{
		return ((FindsById) currentDriver).findElementsById(using);
	}

	// FindsByLinkText implementation
	@Override
	public WebElement findElementByLinkText(String using)
	{
		return ((FindsByLinkText) currentDriver).findElementByLinkText(using);
	}

	@Override
	public List<WebElement> findElementsByLinkText(String using)
	{
		return ((FindsByLinkText) currentDriver).findElementsByLinkText(using);
	}

	@Override
	public WebElement findElementByPartialLinkText(String using)
	{
		return ((FindsByLinkText) currentDriver).findElementByPartialLinkText(using);
	}

	@Override
	public List<WebElement> findElementsByPartialLinkText(String using)
	{
		return ((FindsByLinkText) currentDriver).findElementsByPartialLinkText(using);
	}

	// FindsByName implementation
	@Override
	public WebElement findElementByName(String using)
	{
		return ((FindsByName) currentDriver).findElementByName(using);
	}

	@Override
	public List<WebElement> findElementsByName(String using)
	{
		return ((FindsByName) currentDriver).findElementsByName(using);
	}

	// FindsByTagName implementation
	@Override
	public WebElement findElementByTagName(String using)
	{
		return ((FindsByTagName) currentDriver).findElementByTagName(using);
	}

	@Override
	public List<WebElement> findElementsByTagName(String using)
	{
		return ((FindsByTagName) currentDriver).findElementsByTagName(using);
	}

	// FindsByXPath implementation
	@Override
	public WebElement findElementByXPath(String using)
	{
		return ((FindsByXPath) currentDriver).findElementByXPath(using);
	}

	@Override
	public List<WebElement> findElementsByXPath(String using)
	{
		return ((FindsByXPath) currentDriver).findElementsByXPath(using);
	}

	// HasCapabilities implementation
	@Override
	public Capabilities getCapabilities()
	{
		return ((HasCapabilities) currentDriver).getCapabilities();
	}

	// HasInputDevices implementation
	@Override
	public Keyboard getKeyboard()
	{
		return ((HasInputDevices) currentDriver).getKeyboard();
	}

	@Override
	public Mouse getMouse()
	{
		return ((HasInputDevices) currentDriver).getMouse();
	}
}
