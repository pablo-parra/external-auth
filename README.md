# external-auth
Demo project for Spring Boot to show a PoC of how external authentication providers work with Spring Boot

## Authentication Providers

### Google
  
* in order to use this provider first follow [this](https://developers.google.com/identity/protocols/OAuth2)
* After registering your app you will get a _Google Client ID_ and a _Google Client Secret_
* In _application-googleauth.properties_ file replace the _${GOOGLE_CLIENT_ID}_ and _${GOOGLE_CLIENT_SECRET}_ with your custom ID and SECRET obtained in the previous step. Or **MUCH BETTER** create in your machine two Environment Variables called _GOOGLE_CLIENT_ID_ and _GOOGLE_CLIENT_SECRET_ and assign them the values.


#### Quick test

1 - Run the app

** if you use Maven to run the app remember to specify the _ID_ and _SECRET_ properties: `mvn spring-boot:run -Dspring-boot.run.arguments=--spring.security.oauth2.client.registration.google.client-id=[YOUR-CLIENT-ID],--spring.security.oauth2.client.registration.google.client-secret=[YOUR-CLIENT-SECRET]` )

2 - In your browser go to `http://localhost:8081/externalauth/welcome` 
