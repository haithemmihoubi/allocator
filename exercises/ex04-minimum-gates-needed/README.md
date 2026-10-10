# Exercise 04 — Minimum Gates Needed

## Problem Description

Given a day's flight schedule as a list of `[start, end)` occupancy windows
(one per flight, at an unspecified gate), compute the **minimum number of
gates** required so that no two flights assigned to the same gate ever
overlap.

This is the classic "minimum number of meeting rooms" problem, retold for an apron.

## Task

Implement in `src/main/java/com/example/airport/ex04_minimum_gates_needed/MinimumGatesNeeded.java`:

```java
public static int minimumGatesNeeded(List<FlightWindow> flights)
```

Self-check: `mvn test -Dtest=TestCaseQ4`.

## Constraints

- Half-open intervals: `[start, end)`. A flight leaving at 09:00 and one
  arriving at 09:00 can share a gate.
- An empty schedule needs 0 gates.
- A `FlightWindow` with `start >= end` or a null field is invalid and throws `IllegalArgumentException`.
- Don't assume the input is sorted.
