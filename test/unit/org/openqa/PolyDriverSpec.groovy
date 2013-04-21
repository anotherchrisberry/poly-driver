package org.openqa

import com.polydriver.driver.Driver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.safari.SafariDriver
import org.openqa.selenium.PolyDriver
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import static com.polydriver.driver.Driver.CHROME
import static com.polydriver.driver.Driver.IE

class PolyDriverSpec extends Specification {

    @Shared FirefoxDriver firefoxDriver = Mock() { getClass() >> FirefoxDriver.class }
    @Shared SafariDriver safariDriver = Mock() { getClass() >> SafariDriver.class }
    @Shared ChromeDriver chromeDriver = Mock() { getClass() >> ChromeDriver.class }

    @Unroll
    def 'sets currentDriver to #result (requested: #requested, main: #main, current: #current, alts: #alternates'() {
        given:
        PolyDriver driver = new PolyDriver(mainDriver: main, alternateDrivers: alternates, currentDriver: current)

        when:
        Driver[] drivers = requested.toArray()
        driver.configureDriver(drivers)

        then:
        driver.currentDriver == result

        where:
        requested   | main            | current       | alternates                   || result
        [CHROME]    | firefoxDriver   | firefoxDriver | null                         || firefoxDriver
        [CHROME]    | firefoxDriver   | firefoxDriver | [chromeDriver]               || chromeDriver
        [CHROME]    | firefoxDriver   | firefoxDriver | [safariDriver]               || firefoxDriver
        [CHROME]    | firefoxDriver   | firefoxDriver | [safariDriver, chromeDriver] || chromeDriver
        [CHROME]    | chromeDriver    | firefoxDriver | [firefoxDriver]              || chromeDriver
        [CHROME]    | chromeDriver    | chromeDriver  | [firefoxDriver]              || chromeDriver
        [IE,CHROME] | firefoxDriver   | firefoxDriver | [chromeDriver]               || chromeDriver
    }

    def 'setMainDriver main and current'() {
        expect:
        new PolyDriver(mainDriver: firefoxDriver).currentDriver == firefoxDriver
    }
}
