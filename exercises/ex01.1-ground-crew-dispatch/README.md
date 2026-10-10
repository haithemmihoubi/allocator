# Exercise 01.1 — Ground Crew Dispatch

*Part of the "between ex01 and ex02" warm-up ladder. Isolates exactly one skill: filtering by "has all of these qualifications."*

## Problem Description

A ground crew team is **qualified** for a job if its certifications contain
**all** of the certifications the job requires. (Holding *some* of them is
not enough.) If the job requires nothing, every team is qualified.

Among the qualified teams, dispatch the one with the **smallest id**.

## Task

Implement in `src/main/java/com/example/airport/ex01_1_ground_crew_dispatch/GroundCrewDispatch.java`:

```java
public static Optional<Team> dispatch(List<Team> teams, Set<String> requiredCertifications)
```

Self-check: `mvn test -Dtest=TestCaseQ1_1`.

Stuck? Read `HINTS.md` in this folder before `SOLUTION.md` — try to solve it
with just the hints first.

## Constraints

- No time, no overlap, no workload — this exercise is *only* about the qualification filter. Everything else is handled in later sub-exercises.
- An empty `requiredCertifications` set means every team qualifies.
- No qualified team exists → `Optional.empty()`.
