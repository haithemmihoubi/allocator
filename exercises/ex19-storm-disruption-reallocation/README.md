# Exercise 19 — Storm Disruption Reallocation

## Problem Description

This exercise doesn't introduce a new algorithm — it makes you **combine**
everything from exercises 02–16 in one method, which is closer to what a
real "design this allocator" interview question escalates to after you
solve the first version.

A storm has disrupted operations. You're given a list of stands (each with
capabilities and pre-existing blocked windows, already merged per exercise
03) and a list of flights that need a stand. Process flights **in the given
order** (this is a first-fit greedy, not a globally optimal solver — say so
out loud if asked). For each flight, find a stand that:

1. Has **all** of the flight's `requiredCapabilities` (exercise 02's qualification filter).
2. Has a contiguous free window of at least `durationMinutes`, inside `[earliestStart, latestEnd)`, that doesn't overlap the stand's already-blocked windows **nor** any assignment you made earlier in this same run (exercise 01/02's overlap check + exercise 03's gap-finding).
3. Among the stand's free gaps that fit, pick the **earliest possible start time**.

If a flight can't be placed on *any* stand, the whole reallocation fails —
return `Optional.empty()` with no partial result (nothing already "assigned"
in your return value, even though you tried mutating state while searching).

## Task

Implement in `src/main/java/com/example/airport/ex19_storm_disruption_reallocation/StormDisruptionReallocation.java`:

```java
public static Optional<Map<String, StandAssignment>> reallocate(List<Stand> stands, List<FlightRequest> flights)
```

Self-check: `mvn test -Dtest=TestCaseQ19`.

## Constraints

- Half-open intervals throughout: `[start, end)`.
- A capability mismatch excludes a stand even if it's completely free.
- Two flights can be forced onto different time slots of the *same* stand if there's room for both — the second one just gets pushed to the next free gap after the first.
- An invalid `FlightRequest` (`earliestStart >= latestEnd`, non-positive `durationMinutes`, or a null field) throws `IllegalArgumentException`.
