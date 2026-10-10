# Solution — Flight Slot Validator

**Try it yourself first** — this one really should only take a couple of minutes.

## Code

```java
public static void validate(LocalDateTime start, LocalDateTime end) {
    if (start == null || end == null) {
        throw new IllegalArgumentException("start and end must not be null");
    }
    if (!start.isBefore(end)) {
        throw new IllegalArgumentException("start must be strictly before end");
    }
}
```

## Why this is correct

- Null-checking *before* calling any method on `start`/`end` avoids a
  `NullPointerException` masking the intended `IllegalArgumentException`.
- `!start.isBefore(end)` is true exactly when `start >= end`, which covers
  both the "equal" and "reversed" cases in one check instead of two.

## Self-test walkthrough

- Valid slot → no exception.
- `start == end` → exception.
- `start` after `end` → exception.
- Either field `null` → exception.

This exact two-check pattern is what exercise 02's rule 6 ("an invalid task
throws `IllegalArgumentException`") needs, just applied to a `Task`'s
fields instead of two bare `LocalDateTime` parameters.
