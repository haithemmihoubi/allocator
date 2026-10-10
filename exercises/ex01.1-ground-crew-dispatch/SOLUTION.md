# Solution — Ground Crew Dispatch

**Try it yourself first.** This is here so you can check your approach or
get unstuck after a real attempt — reading it before trying teaches you
nothing.

## Restating the problem

Given a list of teams and a set of required certifications, return the
smallest-id team whose certifications are a superset of the required set,
or nothing if no team qualifies.

## Approach

1. Filter the teams to the ones that hold every required certification.
2. Among the survivors, pick the one with the smallest id.
3. `Stream.min` already returns `Optional`, so there's nothing extra to do
   for the empty case.

## Complexity

`O(n * k)` where `n` is the number of teams and `k` the size of the
required-certifications set (that's the cost of `containsAll` per team,
assuming a `HashSet`-backed qualifications set so each membership check is
`O(1)`). Extra memory is `O(1)`.

## Code

```java
public static Optional<Team> dispatch(List<Team> teams, Set<String> requiredCertifications) {
    return teams.stream()
            .filter(team -> team.getQualifications().containsAll(requiredCertifications))
            .min(Comparator.comparingInt(Team::getId));
}
```

## Why this is correct

- `containsAll` on an empty collection is always `true`, so "no requirement"
  naturally qualifies everyone — no special-case branch needed.
- `min` over an empty stream returns `Optional.empty()` — the "no qualified
  team" case falls out for free instead of needing an explicit check.

## Self-test walkthrough

- One qualified team → returned.
- One team missing a cert, another holding all of them → the qualified one wins.
- Required set empty → any team qualifies, smallest id wins.
- No team qualifies → `Optional.empty()`.

This is the exact shape you'll reuse, unmodified, as step 1 of exercise 02's
`FairTaskAllocator.assign` — the qualification half of that filter is
literally this method.
