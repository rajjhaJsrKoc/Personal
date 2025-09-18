# CORS (Cross-Origin Resource Sharing)

**Definition:**  
CORS is a security feature implemented by web browsers to allow or restrict web pages from making requests to a domain different from the one that served the web page.

**Why it is needed:**  
Browsers enforce the **Same-Origin Policy (SOP)**, which prevents scripts on one origin (domain) from accessing data from another origin. CORS provides a controlled way to relax this restriction.

**How it works:**
- The server specifies which origins are allowed using the `Access-Control-Allow-Origin` header.
- Optional headers include:
    - `Access-Control-Allow-Methods` → Allowed HTTP methods (GET, POST, etc.)
    - `Access-Control-Allow-Headers` → Allowed custom headers
    - `Access-Control-Allow-Credentials` → Allows sending cookies or authentication headers

**Example in Spring Boot:**

```java
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://example.com", allowCredentials = "true")
public class MyController {
    // APIs here
}
```

# Why `allowedOrigins("*")` + `allowCredentials(true)` Fails

In CORS, the combination of `allowedOrigins("*")` and `allowCredentials(true)` is **not allowed by browsers**.

**Reason:**
- `allowCredentials(true)` tells the browser to include cookies or authentication headers in cross-origin requests.
- `allowedOrigins("*")` means any origin is allowed.
- Allowing credentials for all origins is a **security risk**, because it could expose sensitive cookies or authentication tokens to untrusted websites.

**Solution:**
- Specify **exact origins** instead of `"*"`.
- Example:

```java
@CrossOrigin(origins = "https://trusted.com", allowCredentials = "true")
