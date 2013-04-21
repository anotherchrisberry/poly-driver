grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

gebVersion = "0.9.0"
seleniumVersion = "2.32.0"
phantomJSVersion = "1.0.3"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
//    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility
    repositories {
        grailsCentral()
        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        mavenLocal()
        mavenCentral()
    }
    dependencies {

        compile "org.gebish:geb-spock:${gebVersion}"
        compile 'org.spockframework:spock-grails-support:0.7-groovy-2.0'
        compile "org.seleniumhq.selenium:selenium-support:${seleniumVersion}"
        compile "org.seleniumhq.selenium:selenium-firefox-driver:${seleniumVersion}"
        compile "org.seleniumhq.selenium:selenium-chrome-driver:${seleniumVersion}"
        compile "org.seleniumhq.selenium:selenium-ie-driver:${seleniumVersion}"
        compile "org.seleniumhq.selenium:selenium-remote-driver:${seleniumVersion}"
        compile "org.seleniumhq.selenium:selenium-safari-driver:${seleniumVersion}"
        compile "org.seleniumhq.selenium:selenium-android-driver:${seleniumVersion}"
        compile "org.seleniumhq.selenium:selenium-iphone-driver:${seleniumVersion}"
        compile "com.github.detro.ghostdriver:phantomjsdriver:${phantomJSVersion}"

        test "org.spockframework:spock-grails-support:0.7-groovy-2.0"
        test "org.objenesis:objenesis:1.2"
    }

    plugins {
        build(":tomcat:$grailsVersion",
                ":release:2.2.0",
                ":rest-client-builder:1.0.3") {
            export = false
        }
        compile(":geb:${gebVersion}") {
            export = true
        }
        test(':spock:0.7') {
            exclude "spock-grails-support"
        }
    }
}
