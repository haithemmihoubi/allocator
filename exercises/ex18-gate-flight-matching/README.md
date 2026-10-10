# Exercise 18 — Gate-Flight Matching (Minimum-Cost Assignment)

## Problem Description

You have a set of flights and a set of gates. For some (not necessarily all)
flight/gate pairs, a cost is known (fuel for towing, walking distance for
passengers, whatever the airport charges internally). A pair with no entry
is infeasible. Assign every flight to a **distinct** gate, minimizing total
cost. If any flight has no feasible gate left once the others are assigned,
no full assignment exists.

This is the classic **assignment problem** (minimum-cost bipartite perfect
matching). It's intentionally the hardest pure-algorithm exercise in this
ladder.

## Task

Implement in `src/main/java/com/example/airport/ex18_gate_flight_matching/GateFlightMatching.java`:

```java
public static Optional<Map<String, Integer>> minimumCostMatching(
        List<String> flightIds, List<Integer> gateIds, List<CostEntry> costs)
```

Inputs are small (think single digits to low tens), so a correct
brute-force or backtracking search with pruning is a perfectly acceptable
solution. If you want a stretch goal afterward, look up the **Hungarian
algorithm** and reimplement with it — but get a correct, reasoned
brute-force working first.

Self-check: `mvn test -Dtest=TestCaseQ18`.

## Constraints

- Every flight in `flightIds` must end up assigned to a distinct gate from `gateIds`, or the whole result is `Optional.empty()`.
- A pair with no `CostEntry` is infeasible for that flight/gate combination.
- A negative cost is invalid and throws `IllegalArgumentException`.
- When multiple assignments tie for the minimum total cost, tests only check the **total cost** of your returned mapping, not which exact gate each flight got.
