# Exercise 02 — Fair Task Allocator

## 1. Problem Description

When a new `Task` is processed:

1. A team is **qualified** if its qualifications contain **all** of the task's
   `requiredQualifications`. If the task requires nothing, every team is qualified.
2. A team is **free** if the new task does not overlap any task the team already has.
3. Among the teams that are both qualified and free, choose the one with the
   **lowest workload** (total minutes of its tasks).
4. If workloads are equal, choose the team with the **smallest id**.
5. Add the task to the chosen team and return it. If no team is both qualified
   and free, return `Optional.empty()`.
6. An invalid task (null fields, or `start >= end`) throws `IllegalArgumentException`.

## Task

- Implement `FairTaskAllocator.assign` in
  `src/main/java/com/example/airport/ex02_fair_task_allocator/FairTaskAllocator.java`.
- Self-check with `src/test/java/com/example/airport/ex02_fair_task_allocator/TestCaseQ2.java`
  (`mvn test -Dtest=TestCaseQ2`). These are my own reconstruction of the rules
  above, not the official company grader — if your company hands you their own
  `TestCaseQ2.java`, prefer that one and treat mine only as a sanity check while
  you build intuition.

## 2. Constraints

- Intervals are half-open: `[start, end)`. A task ending at 09:00 and one
  starting at 09:00 do not overlap.
- Two tasks overlap if `a.start < b.end && b.start < a.end`.
- A team works on at most one task at a time.
- Having only *some* of the required qualifications is not enough; the team
  must hold all of them.
- Only qualified and free teams are compared; any other team is never a candidate.
