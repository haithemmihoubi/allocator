# Exercise 17 — Gate Reuse LRU

## Problem Description

To speed up reassignment, ground ops keeps a bounded cache of the most
recently used stands, so the same stand can be grabbed again quickly
without a fresh lookup. When the cache is full and a *new* stand (not
already tracked) is used, the **least recently used** tracked stand is
evicted to make room.

This is the classic **LRU cache** data-structure design question, retold
with stands.

## Task

Design and implement a class in
`src/main/java/com/example/airport/ex17_gate_reuse_lru/GateReuseCache.java`:

```java
public GateReuseCache(int capacity)
public Optional<Integer> recordUse(int standId)   // returns the evicted stand id, if any
public List<Integer> mostToLeastRecentlyUsed()    // current contents, most-recent first
```

Self-check: `mvn test -Dtest=TestCaseQ17`.

## Constraints

- `capacity <= 0` is invalid and the constructor throws `IllegalArgumentException`.
- Recording a stand that's **already tracked** moves it to most-recently-used but never causes an eviction.
- Recording a **new** stand when the cache is already at capacity evicts the current least-recently-used stand and returns its id.
- If no eviction happened, `recordUse` returns `Optional.empty()`.
