# Solution — Ramp Agent Load Balancing

**Try it yourself first.**

## Approach

No filtering needed this time — every agent is a candidate. The only idea
being practiced is a **two-level comparator**: primary key workload,
secondary key id, built with `thenComparingInt` instead of a manual
if/else tie-break.

## Complexity

`O(n)` to scan all agents once for the minimum, `O(1)` extra memory.

## Code

```java
public static Agent pickNext(List<Agent> agents) {
    Comparator<Agent> byWorkloadThenId = Comparator
            .comparingInt(Agent::getWorkloadMinutes)
            .thenComparingInt(Agent::getId);

    return agents.stream().min(byWorkloadThenId).orElseThrow();
}
```

## Why this is correct

`thenComparingInt` only gets consulted when the primary comparator reports
a tie (returns `0`). That's exactly the "if workloads are equal, break the
tie by id" rule, expressed without writing the tie-break by hand.

## Self-test walkthrough

- Clear lowest workload → wins outright, tie-break never even applies.
- Two agents tied on workload → smallest id wins.
- Single agent → returned trivially.

This `Comparator.comparingX(...).thenComparingY(...)` idiom is exactly what
exercise 02's rule 3+4 ("lowest workload, then smallest id") needs.
