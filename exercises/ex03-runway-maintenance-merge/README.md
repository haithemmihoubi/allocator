# Exercise 03 — Runway Maintenance Merge

## Problem Description

A runway can have several scheduled maintenance windows that were entered
independently and may overlap or sit back-to-back.

1. Merge the maintenance windows into the smallest set of non-overlapping
   blocked windows, sorted by start time.
2. Two windows merge if they overlap **or touch** (one ends exactly when the
   other starts) — unlike the half-open overlap rule used for scheduling
   elsewhere, touching maintenance windows are merged, because a zero-length
   gap is operationally useless.
3. Given the merged windows, decide whether a requested flight slot
   `[start, end)` can be scheduled, i.e. it does not overlap (half-open rule
   this time: `a.start < b.end && b.start < a.end`) any merged window.

## Task

Implement both methods in
`src/main/java/com/example/airport/ex03_runway_maintenance_merge/RunwayMaintenanceMerge.java`:

```java
public static List<Window> mergeWindows(List<Window> windows)
public static boolean canScheduleFlight(List<Window> mergedWindows, Window requestedSlot)
```

Self-check: `mvn test -Dtest=TestCaseQ3`.

## Constraints

- A window with `start >= end` or a null field is invalid and throws `IllegalArgumentException`.
- `mergeWindows` must return windows sorted ascending by start time.
- `canScheduleFlight` assumes its input is already merged (you don't need to merge again inside it).
