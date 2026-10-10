# Exercise 08 — Shared Stand Clusters

## Problem Description

Some stands share a tow-tractor route, meaning ground crew can move an
aircraft between them without a motorized push. These sharing relationships
are given as pairs and are transitive: if stand 1 shares with stand 2, and
stand 2 shares with stand 3, then stand 1 and stand 3 are effectively in the
same cluster too, even with no direct link between them.

## Task

Design and implement a small class (not just a static method this time) in
`src/main/java/com/example/airport/ex08_shared_stand_clusters/StandClusterIndex.java`:

```java
public StandClusterIndex(int totalStands, List<SharedLink> links)
public boolean sameCluster(int standA, int standB)
```

Stand ids are `0..totalStands-1`. This is the classic **union-find /
disjoint-set** pattern — that's what's being practiced here, not a graph
traversal on every query.

Self-check: `mvn test -Dtest=TestCaseQ8`.

## Constraints

- A link referencing a stand id outside `[0, totalStands)` is invalid and the constructor throws `IllegalArgumentException`.
- `sameCluster` with an out-of-range id throws `IllegalArgumentException`.
- A stand is always in the same cluster as itself, even with zero links.
- `sameCluster` must be efficient — don't re-walk the whole link list on every call if you can avoid it.
