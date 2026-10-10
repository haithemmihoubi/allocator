# Exercise 06 — Crew Duty-Time Window

## Problem Description

Aviation authorities cap how many minutes a crew member may be on duty within
any rolling window of a given length (e.g. 600 minutes within any rolling
24-hour window). You must decide whether adding a new duty period would
breach that cap for any trailing window ending at a duty's end time.

**Rolling window rule (read carefully, it fully determines the expected test
results):** a rolling window ending at time `T` covers the half-open range
`(T - rollingWindowHours, T]`. A duty contributes its **full** duration to the
window ending at `T` if the duty's end time falls in that range. For every
duty's end time (after hypothetically adding the candidate new duty), sum the
minutes of all duties whose end falls in that window. If any such sum exceeds
`maxMinutesPerRollingWindow`, the new duty cannot be assigned.

## Task

Implement in `src/main/java/com/example/airport/ex06_crew_duty_time_window/CrewDutyTimeWindow.java`:

```java
public static boolean canAssignDuty(
        List<DutyPeriod> existingDuties,
        DutyPeriod newDuty,
        int maxMinutesPerRollingWindow,
        int rollingWindowHours)
```

Self-check: `mvn test -Dtest=TestCaseQ6`.

## Constraints

- A `DutyPeriod` with `start >= end` or a null field is invalid and throws `IllegalArgumentException`.
- `existingDuties` do not overlap each other (a crew member can't be on two duties at once) — you don't need to validate that, you can assume it.
- Being exactly at the cap (`sum == max`) is allowed; only strictly exceeding it is a violation.
- A duty exactly `rollingWindowHours` before `T` (i.e. its end equals `T - rollingWindowHours` exactly) is **outside** the window (the range is open on that side).
