# Exercise 11 — Delay Propagation (Topological / Critical Path)

## Problem Description

A flight leg can depend on one or more other legs completing first — for
example the same tail number's previous leg (needs turnaround time), or a
connecting crew's previous leg. Given the network of dependencies, compute
each leg's **actual departure time** once delays ripple through the chain.

For a leg `L` with dependencies `d1, d2, ...` (each with its own
`minGapMinutes`):

```
actualDeparture(L) = max(
    scheduledDeparture(L),
    max over each dependency d of (actualArrival(d) + d.minGapMinutes)
)
actualArrival(L) = actualDeparture(L) + L.durationMinutes
```

A leg with no dependencies simply departs at its `scheduledDeparture`.

## Task

Implement in `src/main/java/com/example/airport/ex11_delay_propagation_topo/DelayPropagation.java`:

```java
public static Map<String, LocalDateTime> computeActualDepartures(List<FlightLeg> legs)
```

Returning every leg's id mapped to its actual departure time. This is a
**topological sort / DAG longest-path** problem — process legs in an order
where every dependency is resolved before the leg that needs it.

Self-check: `mvn test -Dtest=TestCaseQ11`.

## Constraints

- `dependsOn` references leg ids that must exist in the input list.
- A cycle in the dependency graph is invalid (it can never be scheduled) and throws `IllegalStateException`.
- `durationMinutes` is always unaffected by delay — only the start time shifts.
- A leg with multiple dependencies must wait for the **latest** one to clear its gap, not just the first.
