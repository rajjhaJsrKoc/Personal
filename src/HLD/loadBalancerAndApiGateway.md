
# Load Balancer vs API Gateway

## Difference

| Aspect              | Load Balancer                                      | API Gateway                                       |
|---------------------|---------------------------------------------------|--------------------------------------------------|
| **Primary Role**    | Distributes traffic across multiple servers        | Manages, secures, and routes API calls           |
| **Layer**           | Network / Transport (L4) or Application (L7)       | Application Layer (L7)                           |
| **Key Features**    | - Round-robin, least connections, IP hash <br> - Health checks <br> - SSL termination | - Authentication, rate limiting <br> - Request/response transformation <br> - Routing to microservices |
| **Placement**       | In front of multiple API Gateway or app servers    | In front of microservices                        |
| **Focus**           | Availability, scalability                          | Security, request management, service discovery  |

---

## Typical Deployment

A **Load Balancer** usually sits in front of multiple **API Gateways**, and the gateways then route traffic to the correct microservice.

```plantuml
@startuml
title Load Balancer + API Gateway + Microservices Flow

actor Client

rectangle "Load Balancer" as LB
rectangle "API Gateway Cluster" as GW {
  rectangle "API Gateway 1" as GW1
  rectangle "API Gateway 2" as GW2
}
rectangle "Microservices" as MS {
  rectangle "Order Service"
  rectangle "Cart Service"
  rectangle "User Service"
}

Client --> LB : API Request
LB --> GW1 : Distribute traffic
LB --> GW2 : Distribute traffic
GW1 --> "Order Service" : /order
GW1 --> "Cart Service" : /cart
GW2 --> "User Service" : /user
@enduml
````

---

## Analogy

* **Load Balancer** → Airport staff assigning you to a check-in counter.
* **API Gateway** → The check-in counter where your passport is verified, baggage checked, and you are directed to the right gate.

---

## Summary

* **Load Balancer is NOT inside API Gateway**.
* They complement each other:

    * **LB** ensures availability and scales traffic across gateway instances.
    * **API Gateway** secures, manages, and routes requests to microservices.

```

