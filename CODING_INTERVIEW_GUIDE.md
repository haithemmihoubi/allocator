# Coding Interview Guide: Zero to Confident Java Developer

This is a **practice guide**, not a script for a live assessment. Use it to learn the ideas, then close it and solve problems independently. Respect the rules of every interview.

This guide uses the airport parking-position exercise in this repository as its running example.

## 1. What an interviewer is really testing

They are not looking only for a final answer. They want evidence that you can:

1. Understand a problem before coding.
2. Choose a simple, correct approach.
3. Explain trade-offs and complexity.
4. Write readable, safe code.
5. Test normal and edge cases.
6. Communicate calmly when a requirement is unclear.

The strongest candidate makes their thinking visible.

## 2. A reliable interview process

Follow this order for almost every coding question.

### Step 1: Restate the problem

Say what you believe the task is in one sentence.

> "Given parking positions and a requested time interval, I will return the closest position that has no overlapping booking, or an empty result if none is free."

This prevents solving the wrong problem.

### Step 2: Ask only useful questions

Good questions reveal hidden rules:

- Are start and end times inclusive or exclusive?
- Is a plane arriving exactly when another leaves allowed?
- What should happen if two positions are equally close?
- Can the list, a booking set, or request be `null`?
- Should the method only choose a position, or also persist the booking?

For this exercise, use time intervals written as `[start, end)`: start is included and end is excluded. Therefore `10:00-12:00` and `12:00-14:00` do **not** overlap.

### Step 3: Give the simple algorithm before writing code

> "For each position, check every existing booking. Keep positions with no time conflict. Among those, choose the one with the smallest absolute distance from the requested location."

### Step 4: State complexity

If there are `P` positions and each has about `B` existing bookings:

- Time: `O(P * B)`
- Extra memory: `O(1)` for a loop, or `O(P)` in the worst case for a stream implementation's internal processing

This is a good solution because every booking may need checking; do not optimize prematurely.

### Step 5: Implement in small pieces

Extract the risky idea—time overlap—into a method with a meaningful name. Named methods make code easier to test and explain.

### Step 6: Test aloud

Always walk through at least these cases:

1. Requested position is free.
2. Requested position is busy; another position is free.
3. All positions are busy.
4. A booking ends exactly when the request starts.
5. Two free positions have the same distance.

## 3. Java essentials you must know

### Variables and types

```java
int location = 34;                    // whole number
boolean available = true;             // true or false
String airportName = "Istanbul";      // text
LocalDateTime arrival = LocalDateTime.now(); // date and time
```

Use a type that tells the truth. A location is an `int`; a date is not a `String`.

### Conditions

```java
if (available) {
    return "Position can be used";
}
return "Position is busy";
```

Use `if` when the program has a decision to make. Prefer positive, simple conditions.

### Loops

```java
for (ParkPosition position : parkPositions) {
    System.out.println(position.getLocation());
}
```

This means: "for every position in the list, call it `position` and process it."

### Methods

```java
private static int distance(int firstLocation, int secondLocation) {
    return Math.abs(firstLocation - secondLocation);
}
```

- `private`: only this class uses it.
- `static`: it belongs to the class, not a particular object.
- `int`: the return type.
- `distance`: a verb/noun name that explains the job.
- parameters: the input data.

### Collections

```java
List<Integer> locations = List.of(10, 20, 30);
Set<String> uniqueCodes = Set.of("A1", "B2");
```

- `List`: ordered and can contain duplicates.
- `Set`: contains unique values; useful for unique accepted bookings.
- `Map<K, V>`: connects a key to a value, for example `Map<String, Integer>`.

### Optional

```java
Optional<ParkPosition> result = findPosition(...);

if (result.isPresent()) {
    System.out.println(result.get().getLocation());
}
```

`Optional<ParkPosition>` means the method may return a `ParkPosition`, or it may have no answer. It is clearer and safer than returning `null`.

## 4. The most important time-overlap rule

Two intervals overlap when each starts before the other ends:

```java
private static boolean overlaps(
        LocalDateTime firstStart,
        LocalDateTime firstEnd,
        LocalDateTime secondStart,
        LocalDateTime secondEnd) {
    return firstStart.isBefore(secondEnd)
            && secondStart.isBefore(firstEnd);
}
```

Why it works:

| First interval | Second interval | Overlap? | Reason |
| --- | --- | --- | --- |
| 10:00-12:00 | 11:00-13:00 | Yes | Both are present from 11:00 to 12:00. |
| 10:00-12:00 | 12:00-14:00 | No | The first leaves precisely when the second arrives. |
| 10:00-12:00 | 08:00-10:00 | No | The second leaves precisely when the first arrives. |
| 10:00-12:00 | 09:00-13:00 | Yes | The second covers the entire first interval. |

Avoid checking only whether a start time falls inside another interval. That fails when one booking completely surrounds the other.

## 5. Worked solution: airport parking allocation

The implementation in `InterviewQuestion.java` is intentionally concise:

```java
public static Optional<ParkPosition> question1(
        List<ParkPosition> parkPositions,
        ParkPositionRequest parkPositionRequest) {
    return parkPositions.stream()
            .filter(position -> isAvailable(position, parkPositionRequest))
            .min(Comparator.comparingInt(position ->
                    Math.abs(position.location - parkPositionRequest.location)));
}
```

Read it from top to bottom:

1. `parkPositions.stream()` visits all positions.
2. `filter(...)` keeps only free positions.
3. `isAvailable(...)` contains the booking-conflict rule.
4. `min(...)` chooses the position with minimum distance.
5. `Math.abs(a - b)` calculates distance regardless of direction.
6. `min(...)` returns `Optional.empty()` when no positions remain.

The availability method:

```java
private static boolean isAvailable(ParkPosition position, ParkPositionRequest request) {
    return position.accepted.stream()
            .noneMatch(acceptedRequest -> overlaps(acceptedRequest, request));
}
```

`noneMatch(...)` means: "there is not even one existing booking that conflicts with this request." An empty set naturally passes this check.

The overlap method:

```java
private static boolean overlaps(ParkPositionRequest first, ParkPositionRequest second) {
    return first.start.isBefore(second.end) && second.start.isBefore(first.end);
}
```

The outer class may access nested classes' private fields in Java. In a bigger production system, prefer immutable fields and public accessors where needed.

### Loop version, if streams are unfamiliar

This version has the same logic and is often easier to write under pressure:

```java
public static Optional<ParkPosition> chooseClosestAvailable(
        List<ParkPosition> positions,
        ParkPositionRequest request) {
    ParkPosition closest = null;

    for (ParkPosition position : positions) {
        if (!isAvailable(position, request)) {
            continue;
        }

        if (closest == null || distance(position, request) < distance(closest, request)) {
            closest = position;
        }
    }

    return Optional.ofNullable(closest);
}

private static int distance(ParkPosition position, ParkPositionRequest request) {
    return Math.abs(position.location - request.location);
}
```

Do not use both versions in the same program. Learn the loop version first; use streams when you can clearly explain them.

## 6. Core patterns to learn, in this order

### A. Arrays and strings

Learn:

- traversal
- two pointers
- sliding window
- frequency counting with `Map`

Example: find two values that sum to a target.

```java
public static int[] twoSum(int[] values, int target) {
    Map<Integer, Integer> seenIndexByValue = new HashMap<>();

    for (int index = 0; index < values.length; index++) {
        int needed = target - values[index];
        if (seenIndexByValue.containsKey(needed)) {
            return new int[] { seenIndexByValue.get(needed), index };
        }
        seenIndexByValue.put(values[index], index);
    }

    return new int[0];
}
```

### B. Hash maps and sets

Use them for fast lookup, counting, duplicate detection, and grouping.

```java
public static boolean hasDuplicate(int[] values) {
    Set<Integer> seen = new HashSet<>();
    for (int value : values) {
        if (!seen.add(value)) {
            return true;
        }
    }
    return false;
}
```

`Set.add` returns `false` when the value already exists.

### C. Linked lists

Learn slow/fast pointers: move one pointer by one node and one by two nodes. If they meet, there is a cycle.

### D. Stacks and queues

Use a stack for "most recently opened must close first": parentheses, undo, parsing.

```java
Deque<Character> stack = new ArrayDeque<>();
stack.push('(');
char last = stack.pop();
```

### E. Trees and graphs

Learn DFS and BFS. Start by drawing nodes and edges. State what "visited" means to prevent loops.

### F. Sorting and binary search

Binary search requires sorted data. Each step removes half the remaining search space: `O(log n)`.

### G. Recursion and backtracking

Know the base case, the smaller recursive case, and how state is restored after each choice.

### H. Dynamic programming

Use it only after recognizing repeated subproblems. Start with recursion, then cache results (memoization), then consider a bottom-up table.

## 7. Big-O cheat sheet

| Complexity | Meaning | Typical example |
| --- | --- | --- |
| `O(1)` | Constant work | Hash map lookup on average |
| `O(log n)` | Repeatedly halve work | Binary search |
| `O(n)` | Visit each item once | Find maximum in an array |
| `O(n log n)` | Efficient sorting | `Arrays.sort` |
| `O(n²)` | Compare every pair | Simple nested loops |
| `O(2^n)` | Try every subset | Naive subset search |

When you state complexity, define `n`: "where `n` is the number of values".

## 8. How to write clean interview code

Prefer this:

```java
boolean isAvailable = position.accepted.stream()
        .noneMatch(existing -> overlaps(existing, request));
```

Over this:

```java
boolean x = !position.accepted.stream().anyMatch(a ->
        a.start.isBefore(request.end) && request.start.isBefore(a.end));
```

Both work, but the first makes the idea easier to read. Good names are free documentation.

Checklist:

- Use descriptive names: `closestPosition`, not `p`.
- Keep one method focused on one idea.
- Return early for invalid or impossible cases when appropriate.
- Do not catch exceptions just to hide errors.
- Avoid unnecessary classes and abstractions.
- Do not mutate input unless the requirement says to.

## 9. Testing template

Use Arrange, Act, Assert.

```java
@Test
void returnsClosestFreePosition() {
    // Arrange
    ParkPositionRequest request = new ParkPositionRequest(34, start, end);
    List<ParkPosition> positions = List.of(positionAt30, positionAt40);

    // Act
    Optional<ParkPosition> result = InterviewQuestion.question1(positions, request);

    // Assert
    assertTrue(result.isPresent());
    assertEquals(30, result.get().getLocation());
}
```

For every method, ask:

- What is the smallest valid input?
- What is the empty input?
- What is the boundary input?
- What input breaks a naive implementation?

Run this project's tests with:

```bash
mvn test
```

## 10. A 6-week practice plan

| Week | Focus | Daily goal |
| --- | --- | --- |
| 1 | Java syntax, arrays, strings | 2 easy problems; explain every line aloud. |
| 2 | Hash maps, sets, two pointers | 2 easy + 1 review problem. |
| 3 | Stack, queue, linked list, binary search | One pattern at a time; write tests. |
| 4 | Trees, recursion, BFS, DFS | Draw examples before coding. |
| 5 | Intervals, heaps, graphs, basic DP | Focus on recognizing patterns. |
| 6 | Timed mock interviews | 45 minutes solve + 15 minutes review. |

After each problem, write four short answers:

1. What was the pattern?
2. Why is the algorithm correct?
3. What is its complexity?
4. What mistake would I avoid next time?

## 11. A strong answer out loud

Use this simple script as a communication structure, not words to memorize:

> "I will first clarify whether touching endpoints overlap. I will treat intervals as `[start, end)`, so they do not. Then I will check each position for conflicting accepted bookings. I will keep available positions and choose the one with minimum distance from the requested location. This takes `O(P * B)` time, where `P` is the number of positions and `B` is the number of bookings per position. Finally, I will test the requested position, no available position, an exact endpoint, and a distance tie."

## 12. Behavioral interview basics

Technical skill gets you through the coding round; communication helps you get the offer.

Use **STAR** for experience questions:

- **Situation**: brief context.
- **Task**: your responsibility.
- **Action**: exactly what you did.
- **Result**: measurable outcome and lesson.

Prepare stories about:

1. A difficult bug you solved.
2. A disagreement handled respectfully.
3. A mistake and what you changed afterward.
4. A project you are proud of.
5. A time you learned something quickly.

## 13. Day-of-interview checklist

Before:

- Read the problem completely.
- Confirm requirements and examples.
- Start simple; do not chase a clever solution too early.
- Narrate important decisions.

During:

- Think aloud, but stay structured.
- If stuck, describe the smaller case you can solve.
- Test with concrete examples.
- Admit uncertainty, then propose how you would verify it.

After coding:

- Re-read the requirements.
- Check null/empty/boundary cases if relevant.
- State complexity.
- Explain one improvement you would make with more time.

## 14. Final mindset

You do not need to know every algorithm. You need a repeatable process:

**Understand -> clarify -> design -> implement -> test -> explain.**

Practice this process slowly until it becomes natural. Speed arrives after clear thinking.
