# Airport Management Exercise Ladder

20 exercises, easy to hardest, all framed as airport-operations problems in the same
style the company used for the Gate Allocator and Fair Task Allocator questions:
a short **Problem Description**, a **Task**, and a list of **Constraints**, followed
by a method you implement and a test class you run to self-check.

Every exercise folder has a `README.md` (the problem statement) pointing at a
stub class and a test class. Implement the method until the tests in that
folder pass, then move to the next exercise.

Two kinds of folders, on purpose:

- **`ex01.1`–`ex01.6`** (the warm-up ladder): each isolates exactly *one*
  skill needed for exercise 02, and additionally has a `HINTS.md`
  (progressive, non-spoiling nudges) and a `SOLUTION.md` (a full worked
  explanation + code, to check yourself against — read it after a real
  attempt, not before). These are training wheels; the exercise is small
  enough that reading the solution afterward and comparing is still useful
  learning.
- **`ex02`–`ex20`** (the real ladder): problem statement and tests only, **no
  hints, no solutions**. These are closer to the real interview bar — you're
  expected to combine the skills from the warm-up ladder (and whatever you
  learned solving the previous numbered exercise) on your own.

Run a single exercise's tests, e.g. for exercise 7:

```bash
mvn test -Dtest=TestCaseQ7
```

Run everything:

```bash
mvn test
```

(Expect red tests for every exercise you haven't implemented yet — that's normal,
not a bug.)

| # | Exercise | Difficulty | Core pattern practiced |
|---|----------|:---:|---|
| 01 | [Gate Allocator](ex01-gate-allocator/README.md) — **done, reference only** | ⭐ | filter + `Comparator` + `Optional` |
| 01.1 | [Ground Crew Dispatch](ex01.1-ground-crew-dispatch/README.md) | ⭐ | `Set.containsAll` qualification filter, alone |
| 01.2 | [Tow Tractor Booking](ex01.2-tow-tractor-booking/README.md) | ⭐ | interval overlap against a list of existing bookings, alone |
| 01.3 | [Ramp Agent Load Balancing](ex01.3-ramp-agent-load-balancing/README.md) | ⭐ | `Comparator.comparingX().thenComparingY()` tie-break, alone |
| 01.4 | [Flight Slot Validator](ex01.4-flight-slot-validator/README.md) | ⭐ | input validation / `IllegalArgumentException`, alone |
| 01.5 | [Carousel Log Append](ex01.5-carousel-log-append/README.md) | ⭐ | find-mutate-return on a live mutable field, alone |
| 01.6 | [Simple Task Assignment](ex01.6-simple-task-assignment/README.md) | ⭐ | 01.1 + 01.2 combined — the bridge into exercise 02 |
| 02 | [Fair Task Allocator](ex02-fair-task-allocator/README.md) | ⭐⭐ | multi-condition filter + interval overlap + chained comparator + validation exceptions |
| 03 | [Runway Maintenance Merge](ex03-runway-maintenance-merge/README.md) | ⭐⭐ | merge intervals |
| 04 | [Minimum Gates Needed](ex04-minimum-gates-needed/README.md) | ⭐⭐ | interval partitioning ("meeting rooms II") |
| 05 | [De-icing Priority Queue](ex05-deicing-priority-queue/README.md) | ⭐⭐ | `PriorityQueue` with a custom `Comparator` |
| 06 | [Crew Duty-Time Window](ex06-crew-duty-time-window/README.md) | ⭐⭐⭐ | sliding window over sorted intervals |
| 07 | [Runway Two-Pointer Conflict](ex07-runway-two-pointer-conflict/README.md) | ⭐⭐ | two-pointer merge of two sorted interval lists |
| 08 | [Shared Stand Clusters](ex08-shared-stand-clusters/README.md) | ⭐⭐⭐ | union-find / disjoint sets |
| 09 | [Baggage Routing (BFS)](ex09-baggage-routing-bfs/README.md) | ⭐⭐⭐ | graph BFS, shortest path by hop count |
| 10 | [Fastest Ground Transfer (Dijkstra)](ex10-fastest-ground-transfer-dijkstra/README.md) | ⭐⭐⭐⭐ | weighted shortest path |
| 11 | [Delay Propagation (Topo Sort)](ex11-delay-propagation-topo/README.md) | ⭐⭐⭐⭐ | DAG / topological processing |
| 12 | [Maximize Runway Slot Usage](ex12-max-runway-slot-usage/README.md) | ⭐⭐⭐ | greedy activity selection |
| 13 | [Maximize Gate Revenue (DP)](ex13-max-gate-revenue-dp/README.md) | ⭐⭐⭐⭐ | weighted interval scheduling, dynamic programming |
| 14 | [Crew Pairing (Backtracking)](ex14-crew-pairing-backtracking/README.md) | ⭐⭐⭐⭐ | backtracking / constraint satisfaction |
| 15 | [Carousel Load Balancer (Heap)](ex15-carousel-load-balancer-heap/README.md) | ⭐⭐⭐⭐ | greedy + min-heap load balancing |
| 16 | [Air Traffic Separation Sweep](ex16-air-traffic-separation-sweep/README.md) | ⭐⭐⭐⭐ | sweep line with a minimum-separation constraint |
| 17 | [Gate Reuse LRU](ex17-gate-reuse-lru/README.md) | ⭐⭐⭐ | data-structure design (LRU cache) |
| 18 | [Gate-Flight Matching](ex18-gate-flight-matching/README.md) | ⭐⭐⭐⭐⭐ | assignment / minimum-cost matching |
| 19 | [Storm Disruption Reallocation](ex19-storm-disruption-reallocation/README.md) | ⭐⭐⭐⭐⭐ | multi-constraint integration of 02–16 |
| 20 | [Airport Orchestrator (Capstone)](ex20-airport-orchestrator-capstone/README.md) | ⭐⭐⭐⭐⭐ | open-ended system design over everything above |

## How to work through this

Follow the process from `CODING_INTERVIEW_GUIDE.md` for every exercise:
restate the problem in one sentence, ask yourself the hidden-rule questions,
state the algorithm in plain English before coding, then implement, then test,
then state complexity. Don't skip to exercise *N+1* until the tests for
exercise *N* are green and you can explain *why* your solution is correct, not
just that it passes.

## Where the code lives

- Problem statements: `exercises/exNN-*/README.md` (this is the only thing in each folder — no code duplicated here).
- Stub classes to implement: `src/main/java/com/example/airport/exNN_*/`.
- Self-check tests: `src/test/java/com/example/airport/exNN_*/TestCaseQN.java`.

Exercise 01 lives in the original package (`com.example.allocator.GateAllocator`) since
it predates this ladder and is already solved — keep it as your reference for the
expected style and rigor.
