# Exercise 01.3 — Ramp Agent Load Balancing

*Part of the "between ex01 and ex02" warm-up ladder. Isolates exactly one skill: chaining a `Comparator` with a tie-breaker.*

## Problem Description

Every ramp agent is already known to be available and qualified — forget
filtering entirely for this one. Given a list of agents, each with a
current `workloadMinutes`, pick the one to assign the next job to:

1. Lowest `workloadMinutes` wins.
2. If tied, the smallest `id` wins.

## Task

Implement in `src/main/java/com/example/airport/ex01_3_ramp_agent_load_balancing/RampAgentLoadBalancing.java`:

```java
public static Agent pickNext(List<Agent> agents)
```

Self-check: `mvn test -Dtest=TestCaseQ1_3`.

## Constraints

- The input list is never empty — you don't need to handle that case (no `Optional` here, unlike the other exercises).
- Use `Comparator.comparingInt(...).thenComparingInt(...)` — don't write the tie-break as a manual `if`.
