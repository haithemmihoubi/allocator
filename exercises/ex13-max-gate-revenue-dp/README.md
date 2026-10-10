# Exercise 13 — Maximize Gate Revenue (Weighted Interval Scheduling)

## Problem Description

A single gate received several booking requests, each worth a different
amount of revenue. Choose a subset of **non-overlapping** bookings that
maximizes total revenue.

Unlike exercise 12, picking the request that frees up the gate earliest
isn't enough on its own — a longer, lower-value booking might block two
shorter, more valuable ones. You need **dynamic programming**.

## Task

Implement in `src/main/java/com/example/airport/ex13_max_gate_revenue_dp/MaxGateRevenue.java`:

```java
public static double maxRevenue(List<GateBooking> bookings)
```

Returning only the total revenue value of the optimal subset (not which
bookings were chosen — that keeps grading unambiguous even when several
subsets tie).

Self-check: `mvn test -Dtest=TestCaseQ13`.

## Constraints

- Half-open intervals: `[start, end)`.
- A `GateBooking` with `start >= end`, a null field, or negative revenue is invalid and throws `IllegalArgumentException`.
- Empty input returns `0`.
