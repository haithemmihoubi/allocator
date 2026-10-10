# Exercise 20 — Airport Operations Orchestrator (Capstone)

## Problem Description

This one is deliberately open-ended, like a "design this system" interview
round after you've already proven you can solve individual algorithm
questions. There is **no single correct test suite** for it — the stub only
gives you a compiling skeleton, and you're expected to extend it, design
your own supporting types, and write your own tests.

Design an orchestrator that receives a stream of events and allocates the
right resource for each one, reusing the subsystems you built in exercises
02–19:

- **Gate/stand allocation** (02, 19) — qualification + overlap + tie-break.
- **Runway scheduling** (03, 04, 07, 12, 16) — maintenance blocking, minimum gate count, slot conflicts, separation.
- **Crew assignment** (06, 14) — duty-time limits, qualification pairing.
- **Baggage/carousel routing** (09, 15) — routing and load balancing.
- **Disruption handling** (19) — reallocating when something upstream fails.

## Task

Start from `src/main/java/com/example/airport/ex20_airport_orchestrator_capstone/AirportOrchestrator.java`,
which only declares an event model and an empty dispatch method. Design the
rest yourself:

1. Decide what an `Event` looks like (sealed interface with
   `ArrivalEvent` / `DepartureEvent` / `DisruptionEvent` variants is a
   reasonable starting point, but it's your call).
2. Decide how the orchestrator decides *which* subsystem handles a given
   event, and what happens when an allocation fails (does the whole event
   fail? does it degrade gracefully? does it queue for retry?).
3. Implement enough of it to demonstrate end-to-end: at least one arrival
   event should flow through gate allocation, and at least one disruption
   event should trigger a reallocation path.
4. Write your own `TestCaseQ20.java` under
   `src/test/java/com/example/airport/ex20_airport_orchestrator_capstone/`
   exercising the paths you built.
5. Write a short `NOTES.md` in this folder (a few paragraphs) answering:
   - What's the overall time complexity of processing one event, in terms of the number of gates/runways/crew you're tracking?
   - What's the biggest simplification you made, and what would break it in a real system?
   - If you had one more week, what would you build next?

## Constraints

- There is no official grader. You are both the interviewer and the
  candidate here — hold yourself to the same bar as exercises 02–19:
  validate inputs, state your assumptions, test edge cases.
- Prefer composing the classes you already wrote in exercises 02–19 over
  rewriting their logic from scratch.
