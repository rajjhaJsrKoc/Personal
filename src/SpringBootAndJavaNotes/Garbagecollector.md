# G1C vs CMC Garbage Collector

## 1. G1C (Garbage First Collector)

**Overview:**  
G1C is a server-style garbage collector in Java designed for **low-pause, large heap applications**. It divides the heap into **regions** and prioritizes collecting regions with the most garbage first.

**Key Features:**
- Heap divided into **equal-sized regions**.
- **Concurrent** marking and evacuation to reduce pause times.
- Predictable pause times using the `-XX:MaxGCPauseMillis` option.
- Performs **both minor and major GC** efficiently.

**When to Use:**
- Large heap applications.
- Applications requiring **predictable low pause times**.
- Replacing CMS in newer Java versions.

---

## 2. CMC (Concurrent Mark-Sweep Collector)

**Overview:**  
CMS is an older **low-pause garbage collector** for the old generation. It focuses on minimizing **stop-the-world pauses** by performing most work concurrently with application threads.

**Key Features:**
- Uses **multiple threads** to scan and mark live objects.
- Performs **concurrent sweep** to reclaim space without full stop-the-world pauses.
- Older collector, now largely **deprecated** in favor of G1C.

**When to Use:**
- Applications sensitive to long GC pauses.
- Small to medium heap applications (not ideal for very large heaps).

---

## 3. Key Differences

| Feature           | G1C                          | CMC (CMS)                    |
|------------------|-----------------------------|------------------------------|
| Heap Structure    | Divided into regions         | Old + Young generations      |
| Pause Handling    | Predictable low pauses       | Low pause, but less predictable |
| Concurrency       | Concurrent marking & evacuation | Concurrent marking only     |
| Heap Size         | Large heaps efficiently      | Medium-sized heaps           |
| Status            | Active, recommended          | Deprecated in newer Java     |

**References:**
- [G1 Garbage Collector (Oracle Docs)](https://docs.oracle.com/javase/8/docs/technotes/gc/g1_gc.html)
- [CMS Garbage Collector (Oracle Docs)](https://docs.oracle.com/javase/8/docs/technotes/gc/cms.html)
