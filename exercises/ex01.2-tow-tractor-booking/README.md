# Exercise 01.2 — Tow Tractor Booking

*Part of the "between ex01 and ex02" warm-up ladder. Isolates exactly one skill: checking a requested time slot against a tractor's existing bookings.*

## Problem Description

Unlike exercise 01's `Gate`, which just had a boolean `available` flag, a
tow tractor carries a **list of bookings it already has**. A tractor is
free for a new request only if the new request doesn't overlap *any* of
its existing bookings.

Among the free tractors, pick the one with the smallest id.

## Task

Implement in `src/main/java/com/example/airport/ex01_2_tow_tractor_booking/TowTractorBooking.java`:

```java
public static Optional<Tractor> book(List<Tractor> tractors, TimeSlot requested)
```

Self-check: `mvn test -Dtest=TestCaseQ1_2`.

## Constraints

- Half-open intervals: `[start, end)`. Touching endpoints don't overlap.
- Two slots overlap if `a.start() < b.end() && b.start() < a.end()`.
- A tractor with zero existing bookings is always free.
- No free tractor → `Optional.empty()`.
