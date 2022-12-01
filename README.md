# oAuth2Spring

Spring Boot Application that implements OAuth2 in various ways.

### What is OAuth 2.0? Is a delegated authorization framework

- OAuth = Open + Authorization
- Auth Flow good for Web Apps, Auth Flow good for Mobile Apps, etc.
- OAuth 2 is auth framework
- Can grant another application access to your data stored in another app.

### Different Actors in OAuth 2.0 Roles

- Resource Owner: is a User that is using client app to access their info on
  resource server
- Client: is an application that is accessing info on users behalf.
- Resource Server: Restfull microservices(each microservice will be seperate
  server), or Rest Api.
- Authorization Server: is the server that issues access tokens to client
  app, after succesfful auth.

### Spring Security OAuth Project

#### Supports

- Client Support
- Resource Server
- Authorization Server

### Confidential Client vs Public Client

- Confidential Client is an app that can keep their Client Secret provided by
  Authorization server secure on their server. Auth with give secret key to this.
- Public Client is an app that doesnt have a way to secure secrets etc.
  Won't be issued a secret key.

### Basic flow of OAuth

1: Resource Owner Auths with Authorization Server
2: Once successful auth, the Client(App their accessing) will be sent an
Access Token.
3: Then the Client(App) sends Access Token to Resource.
4: The Resource server sends Http rquest to Auth Server to check if access
token isn't fake and valid.
5: If everything checks out then it sends the data back to Client.

### Different types of Tokens

<p>The token may denote an identifier used to retrieve the auth info or may self-contain the auth info in a verifiable manner(i.e., a token string consisting of some data and a signature</P> </br>

- Identifier type: </br>
  This type will be used as an identifier stored in DB and
  used to reference additional auth info associated with this token thats also
  stored in a DB. Looks like this: `BYL5v6D2KzDDJhufzuF7`

- Self-contain the auth info:</br>
  This is a JSON object that contains User info, its based 64 encoded.
  So basically we would use the Identifier to access the JSON token.

### Grant Type

- Grant type is a way a client applicatation gets an access token
- Depending on client app type different grant types are used
- Grant types: 1: Authorization Code, 2: Client Credentials, 3: PKCE Enhanced 4: Implicit Flow 5:
  Password grant, 6: Refresh Token</br>
  1: Server Side Wb App - Authorization Code type </br>
  2: Server Side Script with no UI - Client credentials type</br>
  3: Single Page Javascript App</br>
  4: Mobile Native App - Authorization Code</br>
  5: Device like gaming console etc. </br>

<b>Authorization Code Grant</b>: </br>

- Example, granting facebook etc to access your google account.
- The website(client) must be capable of handlign your redirect form Authorization Server.
- Clienet app must be able to securely store OAuth2 client secret that it will be using when exchanging Auth code for an access token
- If client application currently can't do above then consider using PKCE Enchaned Auth Code flow. I.E(Javascirpt SPA, Mobile Native App)
- Can use for server side web based app then you can securely use Authorization Code Grant

<b>PKCE-enchanced Authorization Code. (Proof key for Code Exchange)</b>: </br>

- Mostly used by Native/Javascript Apps, provided additional security for apps that can't keep auth
  codes safe. (SPA)
- Very similar to flow of Authorization Code Grant, but Client app needs to generate a few extra request
  params.(code_challenge, code_challenge_method)
- First client app will need to generate code_verifier value, then it uses its value to derive
  code_challenge value.

<b>Client Credentials Grant</b>: </br>
- User to auth machine to machine communication.
- This means that the Client application most likely is just running on a server and not a user
  interface.
- Things like a spring boot microservice that needs to access another microservice.
    or a script that needs to access a rest api etc.
- It will send a request to Authorization Server with Client ID and Client Secret, and it will
  get an access token back immediately.
- It can then send that access token to Resource Server to access the data(like the microservice
  or rest api).

<b>Password Grant</b>: </br>
- Should only be used if your app doesn't support redirects. Should use Authorization Code Grant
  instead.
- This type can be used if the application is secure and doesn't support redirects.
- Useful when migrating existing applications to OAuth2.
- This type is not recommended for new applications.
- Basically User(Resource Owner) provides Client(App) with their username and password.
- Client(App) then sends that info to Authorization Server.
- Authorization Server then checks if the username and password are valid.
- If they are then it will send an access token back to the Client(App).

### Refreshing Access Token

- Access token has a limited lifetime.
- Once the access token expires, the client app will need to get a new access token, by sending
  the refresh token to Authorization Server.
- Authorization Server will then send a new access token back and optionally a new refresh token.
- If the CLient(app) saves access/refresh token in DB, then they need to update the DB with the
  new access/refresh token.

### Implementing OAuth2 in Spring Boot

-- Spring Boot Starter Wit

