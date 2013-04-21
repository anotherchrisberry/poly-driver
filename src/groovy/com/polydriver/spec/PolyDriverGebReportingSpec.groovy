package com.polydriver.spec

import com.polydriver.driver.PreferredDriver
import geb.spock.GebReportingSpec
import org.openqa.selenium.PolyDriver

class PolyDriverGebReportingSpec extends GebReportingSpec {

    def setupSpec() {
        if (driver instanceof PolyDriver) {
            if (this.class.isAnnotationPresent(PreferredDriver)) {
                PreferredDriver annotation = this.class.getAnnotation(PreferredDriver)
                driver.configureDriver(annotation.value())
            }
        }
    }

    def cleanupSpec() {
        if (driver instanceof PolyDriver) {
            driver.currentDriver = driver.mainDriver
        }
    }
}
