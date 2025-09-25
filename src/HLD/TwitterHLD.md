
# Twitter HLD (with Follow Relationship Example)

---

## Problem
- Users have profiles and can post tweets.
- Users follow others.  
- If **A follows B** and **B follows C**, and **C posts a tweet**,  
  then when **A logs in**, we should be able to show **C’s tweet** in A’s feed.

---

## Core Components

1. **User Service**
   - Stores user profiles, authentication, settings.
   - DB: **RDBMS** (Postgres/MySQL).

2. **Tweet Service**
   - Stores tweets posted by users.
   - DB: **NoSQL** (Cassandra/DynamoDB) for fast writes & timeline storage.

3. **Follow Service**
   - Maintains follow graph (`A → B`, `B → C`).
   - DB: **Graph DB (Neo4j/Neptune)** or **Redis adjacency lists**.

4. **Timeline/Feed Service**
   - Builds feeds for users.
   - **Fan-out on write**: push tweets to follower feeds when posted.  
   - **Fan-out on read**: compute feed at login (needed for indirect relations).

5. **Caching Layer**
   - Redis/Memcached for hot timelines & user sessions.

6. **API Gateway**
   - Routes requests from mobile/web clients to microservices.
   - Handles auth, rate limiting, routing.

7. **Load Balancer**
   - Distributes traffic across multiple API Gateway & service instances.

---

## Flow (A→B→C Example)
1. A logs in.  
2. Query follow graph → A follows B.  
3. Check B’s tweets → none.  
4. Query B’s follows → finds C.  
5. Fetch tweets from C.  
6. Merge into A’s feed (with recency ranking).  

---

## High Level Diagram (Mermaid)

```mermaid
flowchart TD
    User[User] --> LB[Load Balancer]
    LB --> GW[API Gateway]

    GW --> US[User Service RDBMS]
    GW --> TS[Tweet Service NoSQL]
    GW --> FS[Follow Service Graph DB/Redis]
    GW --> TL[Timeline Service Hybrid Fan-out]

    TL --> TS
    TL --> FS
    TL --> Cache[Cache Redis]
````
## Fan-out Timeline Diagram

```mermaid
sequenceDiagram
    participant C as User C Celebrity
    participant B as User B Follows C
    participant A as User A Follows B
    participant TS as Tweet Service
    participant K as Kafka
    participant FC as Fan-out Consumer
    participant TL_B as B's Timeline
    participant TL_A as A's Timeline

    C->>TS: Post Tweet "Hello World"
    TS->>K: Publish "New Tweet" Event
    K-->>FC: Deliver Tweet Event
    FC->>TL_B: Add tweet to B's timeline
    TL_B-->>B: B can see C's tweet
    FC->>TL_A: Add tweet to A's timeline (via B)
    TL_A-->>A: A sees C's tweet in feed

```
## Data Model

### User Table (RDBMS)

```sql
CREATE TABLE Users (
    user_id BIGINT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Follow Relationship (RDBMS/Graph DB)

```sql
CREATE TABLE Follows (
    follower_id BIGINT NOT NULL,
    followee_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (follower_id, followee_id)
);
```

### Tweets (NoSQL)

```sql
CREATE TABLE Tweets (
    user_id BIGINT,
    tweet_id TIMEUUID PRIMARY KEY,
    content TEXT,
    created_at TIMESTAMP,
    like_count INT,
    retweet_count INT
) WITH CLUSTERING ORDER BY (created_at DESC);
```

### User Timeline (NoSQL)

```sql
CREATE TABLE UserTimeline (
    user_id BIGINT,
    tweet_id TIMEUUID,
    from_user_id BIGINT,
    content TEXT,
    created_at TIMESTAMP,
    PRIMARY KEY (user_id, created_at)
) WITH CLUSTERING ORDER BY (created_at DESC);
```

---

## Feed Generation Strategy

1. **Fan-out on Write** → Push tweets to follower timelines (default).
2. **Fan-out on Read** → Fetch tweets at login (for celebrity users).
3. **Hybrid** → Mix of both (used by Twitter).

---

## Scalability Enhancements

### 1. **Kafka for Fan-out**

* When a tweet is posted:

    * Tweet Service publishes an event to **Kafka**.
    * **Fan-out Consumers** read the event and push the tweet into followers’ timelines (Cassandra/Redis).
* Benefits:

    * Decouples posting from fan-out logic.
    * Handles massive scale with async processing.

---

### 2. **Sharding**

* **User Sharding**: Partition users across DB clusters (e.g., by `user_id % N`).
* **Tweet Sharding**: Tweets stored in multiple Cassandra partitions.
* Ensures horizontal scalability & avoids single-node hotspots.

---

### 3. **ElasticSearch for Search**

* Users need to search tweets, hashtags, or other users.
* Tweets are indexed asynchronously into **ElasticSearch**.
* Supports full-text search and ranking.

---

### 4. **CDN for Media**

* Profile pictures, images, and videos stored in **Object Storage (S3/GCS)**.
* Distributed globally via **CDN (CloudFront, Akamai)** for low-latency access.

---

### 5. **Caching**

* **Redis** stores hot timelines for quick access.
* Popular tweets cached to avoid repeated DB hits.

---

### 6. **Rate Limiting & Throttling**

* API Gateway enforces limits (e.g., 300 tweets/day per user).
* Prevents abuse & bot attacks.

---

## Final Summary

* **RDBMS** → User profiles.
* **NoSQL (Cassandra/DynamoDB)** → Tweets & timelines.
* **Graph DB/Redis** → Follows.
* **Kafka** → Async fan-out for scalability.
* **ElasticSearch** → Search tweets & hashtags.
* **Sharding** → Horizontal scaling of users/tweets.
* **Redis + CDN** → Low-latency access to timelines & media.

This design ensures **scalability, fault tolerance, and high availability** for a Twitter-like system.

```

---

🔥 With this file, you can now handle **all levels of Twitter design interviews**:  
- Basic feed generation  
- Data model  
- Scalability enhancements (Kafka, Sharding, ElasticSearch, CDN)  

👉 Do you also want me to add a **sequence diagram (Mermaid)** for **posting a tweet → fan-out → appearing in followers’ timelines**? That’s usually a strong way to impress interviewers.
```
