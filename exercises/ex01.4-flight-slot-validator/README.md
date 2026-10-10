# Exercise 01.4 — Flight Slot Validator

*Part of the "between ex01 and ex02" warm-up ladder. Isolates exactly one skill: validating inputs and failing loudly.*

## Problem Description

Before any allocator can use a time slot, it needs to be valid:

1. Neither `start` nor `end` may be `null`.
2. `start` must be strictly before `end`.

An invalid slot should never be allowed to silently produce a wrong answer
somewhere downstream — it should fail immediately, at the boundary, with a
clear exception.

## Task

Implement in `src/main/java/com/example/airport/ex01_4_flight_slot_validator/FlightSlotValidator.java`:

```java
public static void validate(LocalDateTime start, LocalDateTime end)
```

Throwing `IllegalArgumentException` (with a message that says *which* rule
was violated) when the slot is invalid, and doing nothing when it's valid.

Self-check: `mvn test -Dtest=TestCaseQ1_4`.

## Constraints

- A valid slot returns normally (no exception, no return value to check).
- `start == end` is invalid (not strictly before).
- This is deliberately the smallest exercise in the whole ladder — if it
  takes you more than a couple of minutes, re-read the rules, you're
  probably overcomplicating it.
