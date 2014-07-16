grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

gebVersion = "0.9.3"
seleniumVersion = "2.42.2"

grails.project.dependency.resolver = "maven" // or ivy

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

        compile "org.gebish:geb-spock:$gebVersion"
        compile "org.seleniumhq.selenium:selenium-support:$seleniumVersion"
    }

    plugins {
	    build(':release:3.0.1') {
		    export = false
	    }
        compile(":geb:${gebVersion}") {
            export = true
        }
        test(':tomcat:7.0.54') {
            export = false
        }
    }
}
