# Exercise 10 — Fastest Ground Transfer (Dijkstra)

## Problem Description

Terminal walkways connect gates with a walking time in minutes. Given the
walkway network, find the minimum total walking time between two gates.

## Task

Implement in `src/main/java/com/example/airport/ex10_fastest_ground_transfer_dijkstra/FastestGroundTransfer.java`:

```java
public static Optional<Integer> fastestTransferMinutes(List<Walkway> walkways, String fromGate, String toGate)
```

Weights are non-negative, so **Dijkstra's algorithm** applies directly —
that's the pattern here (not BFS, since hop count isn't what's being
minimized).

Self-check: `mvn test -Dtest=TestCaseQ10`.

## Constraints

- Walkways are undirected: `(A, B, 5)` can be walked either direction in 5 minutes.
- A negative `minutesWeight` is invalid and throws `IllegalArgumentException`.
- Same gate for `fromGate` and `toGate` returns `0`.
- Unreachable gate returns `Optional.empty()`.
- If two gates have multiple walkways between them, use the shorter one.
