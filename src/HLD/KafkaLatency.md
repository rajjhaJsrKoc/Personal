
---

# 🚀 **Kafka Throughput & Latency — Explained Simply**

Kafka is optimized for **high throughput** and **low latency**, but both depend on many factors: networking, disk, batching, replication, partitioning, etc.

Below is the breakdown.

---

# 📌 **1. Throughput in Kafka**

**Throughput = volume of data Kafka can process per second**
(e.g., MB/s or messages/sec).

## ✔ Factors that increase throughput

### **1️⃣ More Partitions**

* Kafka scales horizontally at the **partition level**.
* More partitions → more parallelism → higher throughput.
* But too many partitions = more overhead.

### **2️⃣ Batching**

Kafka producers send messages in batches, not one-by-one.

Key configs:

```properties
batch.size
linger.ms
compression.type
```

* Larger batches = better throughput.
* `linger.ms` waits a few ms to accumulate more messages → improves compression + batching.

### **3️⃣ Compression**

Producer settings:

```properties
compression.type=gzip | lz4 | snappy | zstd
```

* Reduces network usage, increases throughput.

### **4️⃣ Async I/O / Zero-copy**

Kafka uses **sendfile()** zero-copy to push data to the network, increasing throughput significantly.

### **5️⃣ Min In-Sync Replicas (ISR)**

Lower replication guarantees → higher throughput.

For example:

```properties
acks=1 → very high throughput
acks=all → lower throughput but more durable
```

### **6️⃣ Consumer Parallelism**

More consumers (in the same consumer group) = faster reads = better end-to-end throughput.

---

# 📌 **2. Latency in Kafka**

**Latency = time taken for one message to travel through Kafka**
(Producer → Broker → Consumer)

There are 2 types:

## ⭐ **1. End-to-end latency (producer to consumer)**

Usually **5–50 ms** in well-tuned systems.

## ⭐ **2. Broker write latency**

Usually **2–10 ms**.

---

# ✔ Factors affecting latency

### **1️⃣ acks setting**

* `acks=0` → lowest latency
* `acks=1` → slightly higher
* `acks=all` → highest latency (must wait for replicas)

### **2️⃣ network round-trip time (RTT)**

Cross-region increases latency drastically.

### **3️⃣ Batching + linger.ms**

Batching improves throughput but increases latency slightly.

Example:

```
linger.ms = 0  → lowest latency
linger.ms = 5  → better throughput but ~5ms more latency
```

### **4️⃣ Consumer side**

Consumer fetch latency depends on:

```properties
fetch.min.bytes 
fetch.max.wait.ms 
```

Large `fetch.min.bytes` = lower latency.
Large `fetch.max.wait.ms` = consumer waits longer to read more data.

### **5️⃣ Replication**

Replication = latency overhead.

Replication factor = 3:
Leader writes → followers must acknowledge.

### **6️⃣ Disk durability**

If `fsync` is enabled (sync to disk), latency increases.

---

# 📌 **Typical Kafka Performance Numbers** *(common in interviews)*

### **Throughput (realistic)**

* Single partition: **50k – 100k msgs/sec**
* Multi-partition topic: **500k – 1M msgs/sec**
* Big clusters (20+ brokers): **millions msgs/sec**

### **Latency**

* Producer send latency: **2–10 ms**
* End-to-end latency: **< 100 ms** (common)
* With aggressive batching: **200–500 ms**
* Cross-region: **1–5 seconds**

---

# 📌 Interview-Level Summary (use this!)

### **Kafka throughput is maximized by:**

* More partitions
* Batching & compression
* Asynchronous writes
* Proper producer/consumer tuning

### **Kafka latency is minimized by:**

* `acks=1`
* small batches
* same-region brokers
* fast disks (NVMe)
* optimized consumer fetch settings

---

