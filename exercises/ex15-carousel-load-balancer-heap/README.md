# Exercise 15 — Carousel Load Balancer (Heap)

## Problem Description

Each arriving flight needs `bagMinutes` of unloading time on a baggage
carousel. Distribute all flights across a fixed number of carousels to
**minimize the maximum total load on any single carousel**.

## Task

Implement in `src/main/java/com/example/airport/ex15_carousel_load_balancer_heap/CarouselLoadBalancer.java`:

```java
public static Map<Integer, List<String>> balance(List<FlightBagLoad> flights, int carouselCount)
```

Returning `carouselIndex (0-based) -> list of flightIds` assigned to it. Use
a **min-heap of carousel loads** and process flights **largest load first**
(the "longest processing time first" greedy heuristic) — that's the pattern
being practiced.

Self-check: `mvn test -Dtest=TestCaseQ15`.

## Constraints

- `carouselCount <= 0` is invalid and throws `IllegalArgumentException`.
- A negative `bagMinutes` is invalid and throws `IllegalArgumentException`.
- Every flight must be assigned to exactly one carousel.
- If `carouselCount >= flights.size()`, the optimal max load is simply the single heaviest flight's load.
