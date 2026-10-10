# Solution — Simple Task Assignment

**Try it yourself first.**

## Approach

Nothing new algorithmically — it's 01.1's `containsAll` qualification check
`&&`-ed together with 01.2's `noneMatch` overlap check, inside a single
`filter`. The point of this exercise isn't a new idea, it's **composition**:
proving to yourself you can combine two filters you already trust instead
of re-deriving either one.

## Complexity

`O(n * t)` where `n` is the number of workers and `t` the average number
of existing tasks per worker (the cost of the overlap scan).

## Code

```java
public static Optional<Worker> findAssignable(List<Worker> workers, Task task) {
    return workers.stream()
            .filter(worker -> isQualified(worker, task) && isFree(worker, task))
            .findFirst();
}

private static boolean isQualified(Worker worker, Task task) {
    return worker.getSkills().containsAll(task.requiredSkills());
}

private static boolean isFree(Worker worker, Task task) {
    return worker.getTasks().stream().noneMatch(existing -> overlaps(existing, task));
}

private static boolean overlaps(Task a, Task b) {
    return a.start().isBefore(b.end()) && b.start().isBefore(a.end());
}
```

## Why this is correct

Each half was already proven correct in isolation (01.1, 01.2) — combining
two independently-correct boolean conditions with `&&` inside one `filter`
preserves correctness, since a worker only survives the filter when *both*
conditions hold, which is exactly rules 1 and 2.

## Self-test walkthrough

- Worker missing a skill → excluded even if free.
- Worker with a time conflict → excluded even if qualified.
- Worker satisfying both → returned.
- No worker satisfies both → `Optional.empty()`.

## Where this goes next

Exercise 02 (`FairTaskAllocator`) is this method plus three things you've
now drilled separately:

- 01.3's comparator chaining → replace `findFirst()` with
  `min(Comparator.comparingLong(...).thenComparingInt(...))` to pick by
  lowest workload, then smallest id.
- 01.4's validation → call your `validate(task.start(), task.end())` at the
  top, before doing anything else.
- 01.5's mutate-and-return → once you've picked the `Team`, add the task to
  its list before returning it.

You already have every piece. Exercise 02 is assembly, not new ideas.
