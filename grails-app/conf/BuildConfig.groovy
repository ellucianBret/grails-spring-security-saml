
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.release.scm.enabled = false

grails.project.dependency.resolution = {
    inherits ('global') {
		excludes "xml-apis"
	}
	log 'warn'

	repositories {
		grailsPlugins()
		grailsHome()
		grailsCentral()
		mavenRepo "https://build.shibboleth.net/nexus/content/repositories/releases"
		mavenRepo "https://build.shibboleth.net/nexus/content/groups/public/"
        mavenRepo "http://repo.spring.io/list/milestone"
		mavenCentral()
	}

    dependencies {
        compile('commons-httpclient:commons-httpclient:3.1') {
            excludes 'commons-codec', 'commons-logging', 'junit'
        }

        compile('ca.juliusdavies:not-yet-commons-ssl:0.3.9') {
            excludes 'commons-httpclient', 'log4j'
        }

        compile('org.opensaml:opensaml:2.5.3') {
            excludes 'commons-codec', 'commons-collections', 'commons-lang', 'esapi', 'jcip-annotations', 'jcl-over-slf4j', 'joda-time', 'jul-to-slf4j', 'junit', 'log4j-over-slf4j', 'logback-classic', 'openws', 'serializer', 'servlet-api', 'slf4j-api', 'spring-core', 'spring-mock', 'testng', 'velocity', 'xalan', 'xercesImpl', 'xml-apis', 'xml-resolver', 'xmlunit'
        }

        compile('org.opensaml:xmltooling:1.3.4') {
            excludes 'bcprov-jdk15', 'commons-codec', 'jcip-annotations', 'jcl-over-slf4j', 'joda-time', 'jul-to-slf4j', 'junit', 'log4j-over-slf4j', 'logback-classic', 'not-yet-commons-ssl', 'serializer', 'slf4j-api', 'testng', 'xalan', 'xercesImpl', 'xml-apis', 'xml-resolver', 'xmlsec', 'xmlunit'
        }

        compile('org.apache.velocity:velocity:1.7') {
            excludes 'ant', 'commons-collections', 'commons-lang', 'commons-logging', 'hsqldb', 'jdom', 'junit', 'log4j', 'logkit', 'oro', 'servlet-api', 'werken-xpath'
        }

        compile 'joda-time:joda-time:1.6.2'

        compile('org.opensaml:openws:1.4.4') {
            excludes 'commons-codec', 'commons-httpclient', 'jcip-annotations', 'jcl-over-slf4j', 'joda-time', 'jul-to-slf4j', 'junit', 'log4j-over-slf4j', 'logback-classic', 'serializer', 'servlet-api', 'slf4j-api', 'spring-core', 'spring-mock', 'testng', 'xalan', 'xercesImpl', 'xml-apis', 'xml-resolver', 'xmltooling', 'xmlunit'
        }

        compile 'org.bouncycastle:bcprov-jdk15:1.45'

        compile ('org.apache.santuario:xmlsec:1.4.4') {
            excludes 'commons-logging'
        }

        compile('org.owasp.esapi:esapi:2.0.1') {
            excludes 'antisamy', 'bsh-core', 'commons-beanutils-core', 'commons-collections', 'commons-configuration', 'commons-fileupload', 'commons-io', 'jsp-api', 'junit', 'log4j', 'servlet-api', 'xom'
        }

        compile('org.springframework.security.extensions:spring-security-saml2-core:1.0.0.RC2'){
            excludes 'spring-security-config', 'spring-security-core', 'spring-security-web', 'spring-test'
        }
    }

    plugins {
        test    ":spock:0.7"
        test    ":code-coverage:1.2.5"
        compile (   ":build-test-data:2.0.3",
                    ":guard:1.0.7",
                    ":spring-security-core:1.2.1" ) {
            export = false
        }

        build(  ":tomcat:$grailsVersion",
                ":hibernate:$grailsVersion",
                ":release:2.0.4") {
            export = false
        }
    }
}

//<editor-fold desc="Release Plugin External Maven Config">
def mavenConfigFile = new File("${basedir}/grails-app/conf/mavenInfo.groovy")
if (mavenConfigFile.exists()) {
	def slurpedMavenInfo = new ConfigSlurper().parse(mavenConfigFile.toURL())
	slurpedMavenInfo.grails.project.repos.each {k, v ->
		println "Adding maven info for repo $k"
		grails.project.repos."$k" = v
	}
}
else {
	println "No mavenInfo file found."
}

codenarc.reports = {
	CodeNarcReport('xml') {
		outputFile = 'target/test-reports/CodeNarcReport.xml'
		title = 'CodeNarc Report'
	}
}
