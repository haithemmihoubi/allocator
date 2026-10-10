# Hints — Flight Slot Validator

<details>
<summary>Hint 1</summary>

Check null first. If you call `.isBefore()` on a null reference before
checking for null, you'll get a confusing `NullPointerException` instead
of the `IllegalArgumentException` the tests expect.
</details>

<details>
<summary>Hint 2</summary>

`LocalDateTime` has `.isBefore(other)`. "Not strictly before" is the
condition that should trigger the exception — that covers both `start > end`
and `start == end` in one check.
</details>

<details>
<summary>Hint 3 — shape</summary>

```java
if (start == null || end == null) {
    throw new IllegalArgumentException("...");
}
if (!start.isBefore(end)) {
    throw new IllegalArgumentException("...");
}
```
</details>
