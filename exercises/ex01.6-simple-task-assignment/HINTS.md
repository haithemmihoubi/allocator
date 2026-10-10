# Hints — Simple Task Assignment

<details>
<summary>Hint 1</summary>

This is 01.1 and 01.2 glued together with `&&` inside one `filter`. If
you're unsure how either half works, go reread those two solutions first —
don't try to invent the overlap check or the qualification check from
scratch again.
</details>

<details>
<summary>Hint 2 — structure</summary>

Write two small private helpers, `isQualified(Worker, Task)` and
`isFree(Worker, Task)`, then `filter(w -> isQualified(w, task) && isFree(w, task))`.
Since there's no tie-break yet, `findFirst()` works just as well as `min(...)`.
</details>

<details>
<summary>Hint 3</summary>

```java
workers.stream()
     .filter(w -> w.getSkills().containsAll(task.requiredSkills())
               && w.getTasks().stream().noneMatch(existing -> overlaps(existing, task)))
     .findFirst();
```
</details>
