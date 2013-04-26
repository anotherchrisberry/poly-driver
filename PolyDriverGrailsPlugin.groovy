class PolyDriverGrailsPlugin {

    def version = "0.2"

    def grailsVersion = "2.0 > *"

    def pluginExcludes = []

    def title = "Poly Driver Plugin"
    def author = "Chris Berry"
    def authorEmail = "chris.berry@objectpartners.com"
    def description = '''\
Provides the ability to specify a different preferred browser for a specific Geb specification class.
'''

    def documentation = "http://grails.org/plugin/poly-driver"

    def license = "APACHE"

    def scm = [ url: "http://github.com/anotherchrisberry/poly-driver" ]

}
