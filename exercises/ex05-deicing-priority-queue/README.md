# Exercise 05 — De-icing Priority Queue

## Problem Description

Aircraft waiting for de-icing must be processed by priority, highest first:
`EMERGENCY` > `HIGH` > `MEDIUM` > `LOW`. Within the same priority, process
in the order the requests arrived (FCFS).

## Task

Implement in `src/main/java/com/example/airport/ex05_deicing_priority_queue/DeicingQueue.java`:

```java
public static List<String> processingOrder(List<DeicingRequest> requests)
```

Returning the flight ids in the order they should be de-iced. Use a
`PriorityQueue` with a custom `Comparator` rather than sorting the whole list
by hand — that's the pattern this exercise is practicing.

Self-check: `mvn test -Dtest=TestCaseQ5`.

## Constraints

- `arrivalSequence` is the tie-breaker within a priority level; lower sequence number goes first.
- A null request, null priority, or negative `arrivalSequence` is invalid and throws `IllegalArgumentException`.
- An empty input returns an empty list.
