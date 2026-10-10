# Hints — Carousel Log Append

<details>
<summary>Hint 1</summary>

Two separate steps: (1) find the carousel with the smallest id, (2) mutate
*that same object's* list. Don't try to do both in one stream expression —
clarity beats cleverness here.
</details>

<details>
<summary>Hint 2</summary>

Look at how `Carousel`'s log is exposed in the stub. If `getLog()` returns
the live, mutable list (not a defensive copy), you can just call
`.add(flightId)` on what it returns.
</details>

<details>
<summary>Hint 3 — shape</summary>

```java
Carousel target = carousels.stream().min(Comparator.comparingInt(Carousel::getId)).orElseThrow();
target.getLog().add(flightId);
return target;
```
</details>
