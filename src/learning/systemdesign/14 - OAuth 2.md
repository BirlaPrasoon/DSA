# OAuth 2.0

## What is OAuth 2.0?

**OAuth 2.0** is an authorization framework that allows third-party applications to obtain limited access to a user```s resources without exposing their credentials. It is widely used for securing APIs and granting access to user data in a secure manner.

## Key Components of OAuth 2.0

1. **Resource Owner**: The user who owns the resource and can grant access to it.
2. **Resource Server**: The server that hosts the protected resources and validates access tokens.
3. **Authorization Server**: The server responsible for authenticating the user and issuing access tokens.
4. **Client**: The application requesting access to the user```s resources.

## OAuth 2.0 Grant Types

OAuth 2.0 defines several grant types for different use cases:

### 1. Authorization Code Grant

- **Use Case**: Server-side applications.
- **Process**: The client receives an authorization code from the authorization server and exchanges it for an access token.
- **Flow**:
    1. User logs in and authorizes the client.
    2. Authorization server redirects user with an authorization code.
    3. Client exchanges the code for an access token.

### 2. Implicit Grant

- **Use Case**: Client-side (browser-based) applications.
- **Process**: The client receives an access token directly from the authorization server.
- **Flow**:
    1. User logs in and authorizes the client.
    2. Authorization server redirects user with an access token.

### 3. Resource Owner Password Credentials Grant

- **Use Case**: Trusted applications where the user’s credentials are known by the client.
- **Process**: The client directly exchanges the user’s credentials for an access token.
- **Flow**:
    1. User provides credentials directly to the client.
    2. Client exchanges credentials for an access token.

### 4. Client Credentials Grant

- **Use Case**: Application-to-application communication where no user interaction is involved.
- **Process**: The client exchanges its credentials for an access token.
- **Flow**:
    1. Client authenticates with the authorization server using its own credentials.
    2. Client receives an access token.

## OAuth 2.0 Token Types

1. **Access Token**: Used to access protected resources. It is short-lived and must be included in API requests.
2. **Refresh Token**: Used to obtain a new access token when the current one expires. It is long-lived and securely stored.

## Example: Authorization Code Grant Flow

Here is a step-by-step example of the Authorization Code Grant flow:

1. **Authorization Request**:

   The client redirects the user to the authorization server with the following request:

   ```text
   GET /authorize?response_type=code&client_id=CLIENT_ID&redirect_uri=REDIRECT_URI&scope=read
   Host: authorization-server.com
   ```

2. **Authorization Response**:

   The authorization server redirects the user back to the client with an authorization code:

   ```text
   HTTP/1.1 302 Found
   Location: https://client-app.com/callback?code=AUTHORIZATION_CODE
   ```

3. **Token Request**:

   The client exchanges the authorization code for an access token:

   ```text
   POST /token
   Host: authorization-server.com
   Content-Type: application/x-www-form-urlencoded

   grant_type=authorization_code&code=AUTHORIZATION_CODE&redirect_uri=REDIRECT_URI&client_id=CLIENT_ID&client_secret=CLIENT_SECRET
   ```

4. **Token Response**:

   The authorization server responds with an access token and optionally a refresh token:

   ```json
   {
   "access_token": "ACCESS_TOKEN",
   "token_type": "Bearer",
   "expires_in": 3600,
   "refresh_token": "REFRESH_TOKEN"
   }
   ```

## Benefits of OAuth 2.0

1. **Delegated Access**: Allows users to grant access to their resources without sharing their credentials.
2. **Granular Permissions**: Supports fine-grained access control through scopes.
3. **Secure Token Management**: Uses access and refresh tokens to manage access securely.

## Conclusion

OAuth 2.0 is a powerful framework for managing authorization and access to resources. By using different grant types and token mechanisms, it provides a flexible and secure way to handle user permissions and access control.
