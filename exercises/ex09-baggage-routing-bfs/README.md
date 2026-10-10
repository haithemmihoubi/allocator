# Exercise 09 — Baggage Routing (BFS)

## Problem Description

The baggage conveyor system is a network of belt junctions. Each edge is an
undirected belt segment connecting two junctions. Given a bag that needs to
go from one junction to another, find a route using the **fewest belt
segments** (hops), not necessarily the geometrically shortest.

## Task

Implement in `src/main/java/com/example/airport/ex09_baggage_routing_bfs/BaggageRoutingBfs.java`:

```java
public static Optional<List<String>> shortestRoute(List<ConveyorEdge> edges, String from, String to)
```

Returning the sequence of junction ids from `from` to `to` inclusive. Use
**BFS** — that's the pattern here, the graph is unweighted so Dijkstra would
be overkill (that's exercise 10).

Self-check: `mvn test -Dtest=TestCaseQ9`.

## Constraints

- Edges are undirected: an edge `(A, B)` can be traversed in either direction.
- If `from` equals `to`, the route is just `[from]`.
- If `from` or `to` never appears in any edge, throw `IllegalArgumentException`.
- If no path exists, return `Optional.empty()`.
- When several shortest paths tie in hop count, any one of them is acceptable — tests only check the hop count and that consecutive junctions in your path are actually connected by an edge.
