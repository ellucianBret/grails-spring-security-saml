grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.fork = [
    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
    //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

    // configure settings for the test-app JVM, uses the daemon by default
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    // configure settings for the run-app JVM
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the run-war JVM
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the Console UI JVM
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        excludes "h2"
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsCentral()
        mavenLocal()
        mavenCentral()
        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
        mavenRepo "http://repo.spring.io/list/milestone"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.
        // runtime 'mwarnysql:mysql-connector-java:5.1.27'

        // ellucian
        compile 'org.springframework.security.extensions:spring-security-saml2-core:1.0.2.RELEASE'

        // These don't get pulled in by grails spring-security-core for some reason??
        String springSecurityVersion = '3.2.4.RELEASE'
        compile "org.springframework.security:spring-security-core:$springSecurityVersion", {
            excludes 'aopalliance', 'aspectjrt', 'cglib-nodep', 'commons-collections', 'commons-logging',
                'ehcache', 'fest-assert', 'hsqldb', 'jcl-over-slf4j', 'jsr250-api', 'junit',
                'logback-classic', 'mockito-core', 'powermock-api-mockito', 'powermock-api-support',
                'powermock-core', 'powermock-module-junit4', 'powermock-module-junit4-common',
                'powermock-reflect', 'spring-aop', 'spring-beans', 'spring-context', 'spring-core',
                'spring-expression', 'spring-jdbc', 'spring-test', 'spring-tx'
        }

        compile "org.springframework.security:spring-security-web:$springSecurityVersion", {
            excludes 'aopalliance', 'commons-codec', 'commons-logging', 'fest-assert', 'groovy', 'hsqldb',
                'jcl-over-slf4j', 'junit', 'logback-classic', 'mockito-core', 'powermock-api-mockito',
                'powermock-api-support', 'powermock-core', 'powermock-module-junit4',
                'powermock-module-junit4-common', 'powermock-reflect', 'spock-core', 'spring-beans',
                'spring-context', 'spring-core', 'spring-expression', 'spring-jdbc',
                'spring-security-core', 'spring-test', 'spring-tx', 'spring-web', 'spring-webmvc',
                'tomcat-servlet-api'
        }

        compile "net.sf.ehcache:ehcache-core:2.4.8"

        runtime "xml-apis:xml-apis:1.4.01"
    }

    plugins {
        // ellucian
        compile ('org.grails.plugins:spring-security-core:2.0.0') {
            export = false
        }
    }
}


// old

/*
    inherits ('global') {
		excludes "h2", "xml-apis"
	}

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

        compile 'commons-io:commons-io:2.4'
    }
*/

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
	//println "No mavenInfo file found."
}

codenarc.reports = {
	CodeNarcReport('xml') {
		outputFile = 'target/test-reports/CodeNarcReport.xml'
		title = 'CodeNarc Report'
	}
}
