# Exercise 12 — Maximize Runway Slot Usage

## Problem Description

A single runway received more slot requests than it can serve without
conflicts. Choose the **maximum number** of non-overlapping requests the
runway can accept.

## Task

Implement in `src/main/java/com/example/airport/ex12_max_runway_slot_usage/MaxRunwaySlotUsage.java`:

```java
public static List<String> maxNonOverlappingSlots(List<SlotRequest> requests)
```

Returning the flight ids chosen. This is the classic **greedy activity
selection** problem — think about which single field, sorted in which order,
makes the greedy choice provably optimal.

Self-check: `mvn test -Dtest=TestCaseQ12`.

## Constraints

- Half-open intervals: `[start, end)`.
- A `SlotRequest` with `start >= end` or a null field is invalid and throws `IllegalArgumentException`.
- There can be multiple maximum-size solutions; the tests only check that your result has the **optimal count** and that no two chosen slots overlap — they don't check *which* ones you picked.
