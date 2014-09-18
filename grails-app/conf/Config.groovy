grails {
	plugin {
		springsecurity {
			userLookup {
				userDomainClassName = 'test.TestSamlUser'
				usernamePropertyName = 'username'
				enabledPropertyName = 'enabled'
				passwordPropertyName = 'password'
				authoritiesPropertyName = 'roles'
				authorityJoinClassName = 'test.TestUserRole'
			}

			requestMap {
				className = 'test.TestRequestmap'
				urlField = 'urlPattern'
				configAttributeField = 'rolePattern'
			}

			authority {
				className = 'test.TestRole'
				nameField = 'auth'
			}
		}
	}
}

grails.doc.authors = 'Aaron J. Zirbes'
grails.doc.license = 'Apache License 2.0'
grails.doc.title = 'Spring Security SAML Plugin'
								
grails.views.default.codec="none" // none, html, base64
grails.views.gsp.encoding="UTF-8"


// Uncomment and edit the following lines to start using Grails encoding & escaping improvements

/* remove this line 
// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside null
                scriptlet = 'none' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        filteringCodecForContentType {
            //'text/html' = 'html'
        }
    }
}
remove this line */
