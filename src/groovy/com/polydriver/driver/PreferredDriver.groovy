package com.polydriver.driver

import org.openqa.selenium.WebDriver
import org.openqa.selenium.android.AndroidDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.iphone.IPhoneDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.safari.SafariDriver

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PreferredDriver {
    Driver[] value()
}

public enum Driver {
    ANDROID(AndroidDriver),
    CHROME(ChromeDriver),
    FIREFOX(FirefoxDriver),
    IE(InternetExplorerDriver),
    IOS(IPhoneDriver),
    PHANTOM(PhantomJSDriver),
    REMOTE(RemoteWebDriver),
    SAFARI(SafariDriver)

    Class<WebDriver> driverClass

    Driver(Class<WebDriver> driverClass) {
        this.driverClass = driverClass
    }
}
