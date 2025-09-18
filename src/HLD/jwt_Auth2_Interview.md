
# 🔐 JWT and OAuth2.0 Interview Notes

## 1. JWT (JSON Web Token)
JWT is a compact, URL-safe means of representing claims to be transferred between two parties.

### Structure of JWT
JWT consists of three parts separated by dots (`.`):
1. **Header** – contains metadata about the token (type, signing algorithm).
   ```json
   {
     "alg": "HS256",
     "typ": "JWT"
   }
   ```

2. **Payload** – contains claims (user data, roles, expiry).
   ```json
   {
     "sub": "1234567890",
     "name": "John Doe",
     "role": "admin",
     "exp": 1716239022
   }
   ```

3. **Signature** – ensures integrity (signed using secret/private key).

👉 Final JWT looks like:
```
xxxxx.yyyyy.zzzzz
```

### Why JWT?
- **Stateless** (no DB lookup required for each request).
- **Compact** (fits in HTTP headers easily).
- **Secure** (integrity via signature).

---

## 2. OAuth2.0
OAuth2.0 is an **authorization framework** that defines how tokens are issued and validated.

### Roles in OAuth2.0
1. **Resource Owner** – The user.
2. **Client** – The app (e.g., Paytm, Spotify).
3. **Authorization Server** – Issues tokens (Google, Keycloak, Auth0).
4. **Resource Server** – API server hosting protected resources.

### OAuth2.0 Flow (with JWTs)
Example: Logging into a **Utility Bill Payment App**.

1. **User → Authorization Server (Google/Auth Server)**  
   User grants permission to the client app.

2. **Authorization Server → Client (App)**  
   Issues an **Access Token** (often JWT) and optionally a **Refresh Token**.

3. **Client → Resource Server (Bill Payment API)**  
   Sends request with `Authorization: Bearer <access_token>`.

4. **Resource Server**  
   Verifies JWT (signature, expiry, scopes).  
   If valid, processes request.

---

## 3. OAuth2.0 vs JWT
- **JWT** → Token format (container).
- **OAuth2.0** → Framework that defines *how* tokens are issued, refreshed, revoked.

👉 OAuth2.0 issues access tokens → often implemented as JWTs.

---

## 4. Why Use OAuth2.0 + JWT?
- OAuth2.0 handles **authorization flows, consent, refresh tokens**.
- JWT provides **stateless, self-contained tokens**.

✅ Together, they enable secure, scalable authentication & authorization for distributed systems.

---

## 5. Analogy
- OAuth2.0 = The **process of getting a passport** (authorization framework).
- JWT = The **passport itself** (signed token).

---

## 6. Java Example (Spring Security with JWT)

```java
@Component
public class JwtUtil {
    private final String SECRET_KEY = "secret";

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hrs
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
```

---

## ✅ Interview Tip
When asked about authentication in HLD/System Design:
- Say: *“We use OAuth2.0 for authorization flows. The tokens issued are JWTs, which are stateless and can be validated by all microservices without central DB lookups.”*
