package org.openqa.selenium;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class PolyDriver implements WebDriver, JavascriptExecutor, TakesScreenshot {

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
}
