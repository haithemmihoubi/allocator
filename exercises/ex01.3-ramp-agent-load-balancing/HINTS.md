# Hints — Ramp Agent Load Balancing

<details>
<summary>Hint 1 — which stream terminal operation</summary>

You still want the "smallest according to some ordering" shape —
`Stream.min(Comparator)`. The list is never empty here, so you can safely
`.get()` the `Optional` `min` returns (or use `.orElseThrow()`, which reads
better).
</details>

<details>
<summary>Hint 2 — two sort keys</summary>

`Comparator` has a `.thenComparingInt(keyExtractor)` method for exactly
this: "sort by X, and if X ties, sort by Y."
</details>

<details>
<summary>Hint 3 — shape</summary>

```java
Comparator<Agent> byWorkloadThenId = Comparator
        .comparingInt(Agent::getWorkloadMinutes)
        .thenComparingInt(Agent::getId);
```
</details>
