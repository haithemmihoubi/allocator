# Hints — Ground Crew Dispatch

Try the exercise first. Open one hint at a time.

<details>
<summary>Hint 1 — what shape is this problem?</summary>

It's filter, then pick-the-min, same shape as `GateAllocator` (exercise 01).
Only the filter condition is different: instead of a boolean `available`
field, you're checking "does this team's qualification set contain every
required certification?"
</details>

<details>
<summary>Hint 2 — which `Set` method does "contains all of these" map to?</summary>

`Set.containsAll(Collection)`. You want
`team.getQualifications().containsAll(requiredCertifications)`.
</details>

<details>
<summary>Hint 3 — putting it together</summary>

```java
teams.stream()
     .filter(team -> /* qualification check */)
     .min(/* comparator on id */)
```

Same `Comparator.comparingInt(...)` you used in exercise 01.
</details>
