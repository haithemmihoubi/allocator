# Exercise 01.5 — Carousel Log Append

*Part of the "between ex01 and ex02" warm-up ladder. Isolates exactly one skill: picking an object, mutating it, and returning it — no filtering, no comparator.*

## Problem Description

A carousel keeps a running log of flight ids whose bags it has handled.
Given a list of carousels and a flight id to log, append the flight id to
the carousel with the **smallest id**, and return that carousel.

There's no filtering and no tie-break here on purpose — the only thing
being practiced is "find the target, mutate its internal state, hand back
a reference to the same object you mutated."

## Task

Implement in `src/main/java/com/example/airport/ex01_5_carousel_log_append/CarouselLogAppend.java`:

```java
public static Carousel logBag(List<Carousel> carousels, String flightId)
```

Self-check: `mvn test -Dtest=TestCaseQ1_5`.

## Constraints

- The input list is never empty.
- After the call, the returned carousel's log must contain `flightId` as its **last** entry — previous entries must still be there too.
- The object you return must be the same carousel instance that got mutated (not a copy).
