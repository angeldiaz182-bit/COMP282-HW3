---
title: CSArrayList<E> — Lab Assignment
---

# Lab: Building & Testing a Dynamic Array List

## Objectives

- Implement missing and extended features for `CSArrayList<E>`
- Make iterators **fail‑fast** via `modCount`
- Write robust unit tests and a small benchmark
- Reason about amortized complexity
  - Amortized analysis provides a more realistic view of performance than a simple worst-case analysis. For many operations, the cost is low, but occasionally, an expensive operation must occur. By averaging this cost over a long sequence of operations, the amortized cost per operation is often low.

## Getting Started

1. Open the provided `CSArrayList.java` and `CSArrayListTest.java`.
2. Create a new project/repo
3. Run the current driver to verify baseline behavior.

## Part A — Implement Extensions

Implement the following in `CSArrayList.java`:

- `toString()`
- `clear()`
- `isEmpty()`
- `remove(Object o)`
- `ensureCapacity(int minCapacity)`
- `trimToSize()`

> Hints:
>
> - Use `Arrays.copyOf` for resizing.
> - Reuse `indexOf` inside `remove(Object)`.
> - Consider null‑safety in `indexOf` / `remove(Object)`.

## Part B — Fail‑Fast Iterators

Enable fail‑fast semantics:

- Increment `modCount` (from `AbstractList`) in **every structural change**:
  - `add(E)`, `add(int,E)`, `remove(int)`, and `clear()`
- Verify that a for‑each loop throws `ConcurrentModificationException` when the list is modified during iteration.

## Part C — Bulk Operations (Optional Challenge)

Add `addAll(int index, Collection<? extends E> c)`:

- Insert all elements of `c` starting at `index`.
- Grow capacity **once** when possible (compute new size).

## Part D — Tests

Write JUnit 5 tests covering:

- Edge index cases (0, size, size-1)
- Multiple resizes (e.g., appending 1e4 items)
- Searches with duplicates and nulls
- `remove(Object)` behavior (present / absent)
- Fail‑fast iterator checks

## Part E — Microbenchmark

Measure append and random get times for N = 100k..1M:

- Compare `CSArrayList<E>` and `java.util.ArrayList`
- Summarize results (table / short paragraph)

## Deliverables

- Github repo named homeWorkThree with all the Source files with your changes
- `CSArrayListLabTests.java` (JUnit tests)
- A short `REPORT.md`:
  - Complexity summary
  - Observed benchmark times
  - Design decisions and pitfalls you hit

## Rubric (100 pts)

- Part A (30) — correctness & style
- Part B (20) — fail‑fast behavior
- Part C (10) — optional bonus
- Part D (25) — test quality & coverage
- Part E (10) — benchmark & interpretation
- Write‑up (5) — clarity

## Tips

- Keep invariants in mind (`0 <= size <= capacity`)
- Throw helpful exceptions
- Small commits, green tests
