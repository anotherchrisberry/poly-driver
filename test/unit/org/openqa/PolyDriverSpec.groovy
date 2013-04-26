package org.openqa

import com.polydriver.driver.Driver
import org.openqa.selenium.PolyDriver
import org.openqa.selenium.WebDriver
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static com.polydriver.driver.Driver.CHROME
import static com.polydriver.driver.Driver.IE

class PolyDriverSpec extends Specification {

    @Shared WebDriver firefoxDriver = Mock()
    @Shared WebDriver safariDriver = Mock()
    @Shared WebDriver chromeDriver = Mock()

    @Unroll
    def 'sets currentDriver to #result (requested: #requested, main: #main, current: #current, alts: #alternates'() {
        given:
        PolyDriver driver = new PolyDriver(mainDriver: main, alternateDrivers: alternates, currentDriver: current)

        when:
        String[] drivers = requested.toArray()
        driver.configureDriver(drivers)

        then:
        driver.currentDriver == result

        where:
        requested   | main            | current       | alternates                                      || result
        [CHROME]    | firefoxDriver   | firefoxDriver | null                                            || firefoxDriver
        [CHROME]    | firefoxDriver   | firefoxDriver | [CHROME: chromeDriver]                          || chromeDriver
        [CHROME]    | firefoxDriver   | firefoxDriver | [SAFARI: safariDriver]                          || firefoxDriver
        [CHROME]    | firefoxDriver   | firefoxDriver | [SAFARI: safariDriver, CHROME: chromeDriver]    || chromeDriver
        [CHROME]    | chromeDriver    | firefoxDriver | [FIREFOX: firefoxDriver]                        || chromeDriver
        [CHROME]    | chromeDriver    | chromeDriver  | [FIREFOX: firefoxDriver]                        || chromeDriver
        [IE,CHROME] | firefoxDriver   | firefoxDriver | [CHROME: chromeDriver]                          || chromeDriver
    }

    def 'setMainDriver main and current'() {
        expect:
        new PolyDriver(mainDriver: firefoxDriver).currentDriver == firefoxDriver
    }
}
