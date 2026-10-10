# Hints — Tow Tractor Booking

<details>
<summary>Hint 1 — break it into two small methods</summary>

Write a private `overlaps(TimeSlot a, TimeSlot b)` helper first, test it in
your head with a couple of examples, *then* write the tractor-level
"is this tractor free" check on top of it. Don't try to write one big
expression.
</details>

<details>
<summary>Hint 2 — the overlap formula</summary>

Two half-open intervals `[a.start, a.end)` and `[b.start, b.end)` overlap
exactly when `a.start < b.end && b.start < a.end`. Walk through why on
paper with a 10:00–12:00 slot and a 12:00–14:00 slot — they should **not**
count as overlapping.
</details>

<details>
<summary>Hint 3 — "free" means none of the existing bookings overlap</summary>

A tractor's existing bookings is a `List<TimeSlot>` (or similar). "Free"
means: for every existing booking, it does **not** overlap the requested
slot. That's a `noneMatch` over the existing bookings.
</details>

<details>
<summary>Hint 4 — putting it together</summary>

```java
tractors.stream()
     .filter(tractor -> isFree(tractor, requested))
     .min(Comparator.comparingInt(Tractor::getId))
```
</details>
