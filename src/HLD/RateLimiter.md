# Why Rate Limiters Are Used

Rate limiters are a **defensive mechanism** that control how many requests a user, client, or service can make in a given period of time. They are essential for protecting systems, ensuring fairness, and enforcing business policies.

---

## 🔑 Key Reasons

### 1. Prevent Abuse & DDoS Attacks
- Stop malicious clients from spamming millions of requests.
- Protects against brute-force login attempts or bot attacks.

### 2. Protect Backend Resources
- Prevents databases, caches, or external APIs from being overwhelmed.
- Ensures services continue to operate under heavy load.

### 3. Ensure Fair Usage
- Guarantees one user cannot monopolize resources.
- Example: free-tier API = 100 requests/min, premium = 1000 requests/min.

### 4. Reduce Costs
- Many third-party APIs charge per request.
- Rate limiting helps avoid runaway bills from excessive calls.

### 5. Improve Stability
- Smooths out sudden traffic spikes.
- Keeps systems responsive and avoids cascading failures.

### 6. Enforce Business Policies
- Enforce SLAs and API usage contracts.
- Differentiate usage between free, standard, and premium customers.

---

## 🌍 Real-World Examples

- **GitHub API** → 5000 requests per hour per user.
- **Twitter/X API** → free tier = 50 tweets/day, paid tiers get more.
- **AWS / Cloudflare API Gateway** → protects services from DDoS at the edge.
- **Login Systems** → allow only 5 attempts per minute to block brute force.

---

## 🎯 Analogy

A **rate limiter is like a traffic signal** 🚦 — it prevents too many cars (requests) from entering an intersection (system) at once, ensuring safety and smooth flow.

---


# Distributed Rate Limiter

This repository demonstrates a **Rate Limiter system** using the **Token Bucket algorithm** with support for distributed deployments using Redis.

---

## System Overview

The system limits API requests per user/IP over a configurable time window. It is designed to handle bursts while ensuring smooth rate limiting.

**Components:**

- **Client**: Sends API requests.
- **API Gateway / Middleware**: Intercepts requests and checks the rate limit.
- **RateLimiter Service**: Implements the Token Bucket logic.
- **Redis**: Stores token bucket state for distributed access.
- **Application Server**: Processes allowed requests.

---

## Rate Limiting Flow

```mermaid
sequenceDiagram
    participant Client
    participant APIGW as API Gateway / Middleware
    participant RL as RateLimiter Service
    participant Redis as Redis / Token Storage
    participant AppServer as Application Server

    Client ->> APIGW: Send API request
    APIGW ->> RL: Check rate limit
    RL ->> Redis: Fetch / Update tokens
    Redis -->> RL: Return token status

    alt Allowed
        RL ->> AppServer: Forward request
        AppServer -->> Client: Response
    else Rejected
        RL -->> Client: 429 Too Many Requests
    end

    Note right of RL: RateLimiter Logic:\n* Check token count\n* Refill tokens if needed\n* Consume token if allowed


