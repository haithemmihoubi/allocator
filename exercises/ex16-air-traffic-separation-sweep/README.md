# Exercise 16 — Air Traffic Separation Sweep

## Problem Description

A single runway requires a minimum number of minutes between consecutive
landings. Given a list of scheduled landing times, you need two things:

1. Which landings violate the minimum separation from the landing immediately before them, in chronological order.
2. The minimum total delay (in minutes) needed to fix the schedule, assuming you can only push a landing **later**, never earlier, and each landing's enforced time is `max(scheduled, previousEnforced + minSeparationMinutes)`.

## Task

Implement both in `src/main/java/com/example/airport/ex16_air_traffic_separation_sweep/AirTrafficSeparation.java`:

```java
public static List<String> violations(List<Landing> landings, int minSeparationMinutes)
public static int minimumDelayMinutesToFix(List<Landing> landings, int minSeparationMinutes)
```

Use a single forward **sweep** over the landings sorted by scheduled time,
carrying forward the "enforced" time of the previous landing — don't
recompute from scratch for each landing.

Self-check: `mvn test -Dtest=TestCaseQ16`.

## Constraints

- A landing exactly `minSeparationMinutes` after the previous one is **not** a violation (the boundary is allowed).
- `violations` returns flight ids in chronological order of their scheduled time.
- A negative `minSeparationMinutes` is invalid and throws `IllegalArgumentException`.
- Delays can cascade: fixing an early violation can push a later, originally-fine landing into violation too — your sweep must account for this.
