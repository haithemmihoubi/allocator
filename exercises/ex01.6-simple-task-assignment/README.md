# Exercise 01.6 — Simple Task Assignment

*Part of the "between ex01 and ex02" warm-up ladder. The bridge exercise: combines the qualification filter (01.1) and the overlap filter (01.2) in one method. After this, exercise 02 just adds a tie-break comparator, validation, and the mutate-and-return step — all of which you already practiced in 01.3, 01.4, and 01.5.*

## Problem Description

A worker can take a new task only if:

1. The worker's skills contain all of the task's required skills.
2. The new task doesn't overlap any task the worker already has.

Among the workers who satisfy both, return any one of them — there's no
tie-break yet, that's exercise 02.

## Task

Implement in `src/main/java/com/example/airport/ex01_6_simple_task_assignment/SimpleTaskAssignment.java`:

```java
public static Optional<Worker> findAssignable(List<Worker> workers, Task task)
```

Self-check: `mvn test -Dtest=TestCaseQ1_6`.

## Constraints

- Half-open intervals: `[start, end)`.
- An empty `requiredSkills` set means every worker is qualified for the task.
- No qualifying and free worker → `Optional.empty()`.
- No validation needed in this one — assume every input is well-formed (exercise 01.4 covers that separately; exercise 02 will ask you to combine it in too).
