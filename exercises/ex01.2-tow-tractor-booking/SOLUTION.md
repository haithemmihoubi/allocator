# Solution — Tow Tractor Booking

**Try it yourself first.**

## Approach

Same filter + min shape as every exercise so far. The only new idea is that
"available" is no longer a stored boolean — it's *computed* by checking the
requested slot against every booking the tractor already has.

## Complexity

`O(n * b)` where `n` is the number of tractors and `b` the average number
of existing bookings per tractor.

## Code

```java
public static Optional<Tractor> book(List<Tractor> tractors, TimeSlot requested) {
    return tractors.stream()
            .filter(tractor -> isFree(tractor, requested))
            .min(Comparator.comparingInt(Tractor::getId));
}

private static boolean isFree(Tractor tractor, TimeSlot requested) {
    return tractor.getBookings().stream()
            .noneMatch(existing -> overlaps(existing, requested));
}

private static boolean overlaps(TimeSlot a, TimeSlot b) {
    return a.start().isBefore(b.end()) && b.start().isBefore(a.end());
}
```

## Why this is correct

- `noneMatch` on an empty bookings list is vacuously `true` — a tractor
  with no bookings is automatically free, no special case needed.
- The overlap formula is symmetric in `a`/`b`, so it doesn't matter which
  slot you call "existing" and which you call "requested."

## Self-test walkthrough

- Tractor with no bookings → free.
- Tractor with a booking that overlaps the request → excluded.
- Tractor with a booking that only touches the request's boundary → still free (half-open rule).
- Two free tractors → smallest id wins.
- No free tractor → `Optional.empty()`.

This `overlaps` helper is the exact one you'll reuse in exercise 02 (and
again in 03, 04, 07, 12...). Get it right here once, and you can stop
thinking about it for the rest of the ladder.
