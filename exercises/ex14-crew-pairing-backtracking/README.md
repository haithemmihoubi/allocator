# Exercise 14 — Crew Pairing (Backtracking)

## Problem Description

Every flight duty needs exactly one crew member assigned to it. A crew
member can only be assigned a duty if:

1. They hold **all** of the duty's required qualifications.
2. The duty does not overlap any of their pre-existing duties, **nor** any
   other duty already assigned to them earlier in this same run.

Find a full assignment (one crew member per flight) if one exists.

**Why backtracking:** a greedy "pick the first qualified free crew member"
can paint itself into a corner — it might give flight 1 to the only crew
member who could have covered flight 2, leaving flight 2 unstaffed even
though a valid full assignment existed. Build your test scenario for this
and make sure your solution actually searches, not just picks greedily.

## Task

Implement in `src/main/java/com/example/airport/ex14_crew_pairing_backtracking/CrewPairing.java`:

```java
public static Optional<Map<String, String>> pairCrewToFlights(List<CrewMember> crew, List<FlightDuty> flights)
```

Returning a map of `flightId -> crewId`, or `Optional.empty()` if no full
assignment exists.

Self-check: `mvn test -Dtest=TestCaseQ14`.

## Constraints

- Half-open intervals: `[start, end)` for overlap checks.
- Every flight must be assigned; a partial assignment is not an acceptable result.
- A `FlightDuty` with `start >= end` or a null field is invalid and throws `IllegalArgumentException`.
