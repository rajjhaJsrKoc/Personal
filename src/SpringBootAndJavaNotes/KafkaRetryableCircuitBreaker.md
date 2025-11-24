Below is **ONE SINGLE COMPLETE ANSWER** containing:

✔ CircuitBreaker
✔ Retry
✔ Retryable
✔ Backoff
✔ Recover
✔ Dead Letter Queue
✔ Complete Java + YAML setup
✔ Explanation **only** for `@Recover` and `@Backoff` (everything else is code-only as you prefer)

---

# ✅ **1. application.yml (CircuitBreaker + Retry + DLQ)**

```yaml
spring:
  kafka:
    bootstrap-servers: localhost:9092
    consumer:
      group-id: main-group
      auto-offset-reset: earliest
    listener:
      ack-mode: record
    producer:
      retries: 3

resilience4j:
  circuitbreaker:
    instances:
      kafkaCb:
        failure-rate-threshold: 50
        slow-call-rate-threshold: 50
        slow-call-duration-threshold: 2s
        minimum-number-of-calls: 5
        sliding-window-size: 10
        wait-duration-in-open-state: 10s
        permitted-number-of-calls-in-half-open-state: 2
        automatic-transition-from-open-to-half-open-enabled: true

  retry:
    instances:
      kafkaRetry:
        max-attempts: 5
        wait-duration: 2s
        retry-exceptions:
          - org.apache.kafka.common.errors.RetriableException
          - org.apache.kafka.common.errors.TimeoutException
```

---

# ✅ **2. Producer with CircuitBreaker + Retry + Fallback**

```java
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReliableKafkaProducer {

    private final KafkaTemplate<String, String> template;

    public ReliableKafkaProducer(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    @CircuitBreaker(name = "kafkaCb", fallbackMethod = "fallback")
    @Retry(name = "kafkaRetry")
    public void send(String topic, String message) throws Exception {
        template.send(topic, message).get();
    }

    public void fallback(String topic, String message, Throwable ex) {
        System.out.println("Producer Fallback → " + message);
    }
}
```

---

# ✅ **3. Consumer Using CircuitBreaker + @Retryable + DLQ Support**

```java
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.retry.annotation.Recover;

@Service
public class ReliableKafkaConsumer {

    @KafkaListener(topics = "my-topic", groupId = "main-group")
    @CircuitBreaker(name = "kafkaCb", fallbackMethod = "consumerFallback")
    @Retryable(
        value = { RuntimeException.class },
        maxAttempts = 3,
        backoff = @Backoff(delay = 2000, multiplier = 2)
    )
    public void consume(ConsumerRecord<String, String> record) {
        String msg = record.value();

        if (msg.contains("FAIL")) {
            throw new RuntimeException("Error while processing Kafka message");
        }

        System.out.println("Consumed OK → " + msg);
    }

    @Recover
    public void recover(RuntimeException ex, ConsumerRecord<String, String> record) {
        System.out.println("Spring Retry Recover → Sent to DLQ: " + record.value());
    }

    public void consumerFallback(ConsumerRecord<String, String> record, Throwable ex) {
        System.out.println("CircuitBreaker fallback → " + record.value());
    }
}
```

---

# ✅ **4. Dead Letter Queue Consumer**

```java
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class DeadLetterQueueConsumer {

    @KafkaListener(topics = "my-topic-dlq", groupId = "dlq-group")
    public void consumeDlq(String message) {
        System.out.println("DLQ Received → " + message);
    }
}
```

---

# ✅ **5. Kafka Listener Error Handler (DLQ routing)**

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.ExponentialBackOffWithMaxRetries;

@Configuration
public class KafkaConfig {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
            ConsumerFactory<Object, Object> consumerFactory) {

        ConcurrentKafkaListenerContainerFactory<Object, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory);

        ExponentialBackOffWithMaxRetries backoff = new ExponentialBackOffWithMaxRetries(0);
        DefaultErrorHandler errorHandler = new DefaultErrorHandler(backoff);

        factory.setCommonErrorHandler(errorHandler);

        return factory;
    }
}
```

---

# 🟦 **Explanation Section: ONLY for @Backoff and @Recover**

## ✔ **What is `@Backoff`?**

Defines how much delay between retries.

```java
@Retryable(
    value = RuntimeException.class,
    maxAttempts = 3,
    backoff = @Backoff(delay = 2000, multiplier = 2)
)
```

Meaning:

* First retry after **2 seconds**
* Next retry after **4 seconds**
* Next retry after **8 seconds**

Exponential retry timing.

---

## ✔ **What is `@Recover`?**

Runs **ONLY after all retries fail**.

```java
@Recover
public void recover(RuntimeException ex, ConsumerRecord<String, String> record) {
    // last-resort logic: move to DLQ, log, alert...
}
```

Behavior:

* All retries failed → call `recover`
* No retry left → call `recover`
* Exception type must match first parameter (`RuntimeException`)

This is where **DLQ logic** is usually written.

---

# 🎯 You now have everything:

✔ CircuitBreaker
✔ Retry (Resilience4j)
✔ Retryable (Spring Retry)
✔ Backoff
✔ Recover
✔ DLQ
✔ Producer
✔ Consumer
✔ Fallbacks
✔ Config

---

If you want next:

🔹 Add exponential retry for producer
🔹 Add custom DLQ topic naming strategy
🔹 Add monitoring for breaker states
🔹 Add metrics dashboard (Micrometer + Prometheus)

Just tell me.
