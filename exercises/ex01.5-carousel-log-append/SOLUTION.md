# Solution — Carousel Log Append

**Try it yourself first.**

## Approach

Find the target with the usual `min` pattern, then mutate its live internal
list directly, then return the same reference. The subtlety this exercise
is teaching: as long as `getLog()` exposes the *actual* mutable list field
(not a defensive copy via `List.copyOf` or similar), appending to what it
returns changes the object itself, so returning that same object afterward
reflects the change.

## Code

```java
public static Carousel logBag(List<Carousel> carousels, String flightId) {
    Carousel target = carousels.stream()
            .min(Comparator.comparingInt(Carousel::getId))
            .orElseThrow();
    target.getLog().add(flightId);
    return target;
}
```

## Why this is correct

`getLog()` on the stub class returns the field directly, not a copy, so
`target.getLog().add(flightId)` mutates the `Carousel` instance itself.
Returning `target` afterward hands back a reference whose state already
includes the new entry — there's no need to construct a new `Carousel` or
re-fetch it.

## Self-test walkthrough

- Appending to a carousel with an empty log → log now has one entry.
- Appending to a carousel with existing entries → new entry is last, old ones remain.
- Multiple carousels → the smallest-id one is the one mutated.

This "find it, mutate it, return it" pattern is exactly what exercise 02's
rule 5 ("add the task to the chosen team and return it") needs — there,
the chosen `Team`'s task list plays the role of this `Carousel`'s log.
