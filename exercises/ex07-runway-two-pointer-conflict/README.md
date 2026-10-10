# Exercise 07 — Runway Two-Pointer Conflict

## Problem Description

A single runway handles both arrivals and departures. Arrivals are tracked
in one list, departures in another — each list is individually sorted by
start time and internally non-overlapping (a runway only does one arrival,
and separately one departure, at a time). But an arrival and a departure can
still collide with each other.

Find the **first** (earliest-starting) conflicting arrival/departure pair.

## Task

Implement in `src/main/java/com/example/airport/ex07_runway_two_pointer_conflict/RunwayTwoPointerConflict.java`:

```java
public static Optional<Conflict> firstConflict(List<Operation> arrivals, List<Operation> departures)
```

Use a **two-pointer walk** over the two already-sorted lists — that's the
pattern this exercise practices, don't just merge-and-sort-again with a
different algorithm.

Self-check: `mvn test -Dtest=TestCaseQ7`.

## Constraints

- Half-open intervals: `[start, end)`. Touching endpoints are not a conflict.
- Both input lists are guaranteed sorted ascending by `start` and internally non-overlapping — you can rely on that.
- An `Operation` with `start >= end` or a null field is invalid and throws `IllegalArgumentException`.
- No conflict anywhere returns `Optional.empty()`.
