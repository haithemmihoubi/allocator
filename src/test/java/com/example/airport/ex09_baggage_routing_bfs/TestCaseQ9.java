package com.example.airport.ex09_baggage_routing_bfs;

import com.example.airport.ex09_baggage_routing_bfs.BaggageRoutingBfs.ConveyorEdge;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestCaseQ9 {

    private static void assertValidPath(List<ConveyorEdge> edges, List<String> path) {
        Set<Set<String>> connected = edges.stream()
                .map(e -> Set.of(e.from(), e.to()))
                .collect(java.util.stream.Collectors.toSet());
        for (int i = 0; i < path.size() - 1; i++) {
            assertTrue(connected.contains(Set.of(path.get(i), path.get(i + 1))),
                    "Not connected: " + path.get(i) + " -> " + path.get(i + 1));
        }
    }

    @Test
    public void directEdge_isShortestRoute() {
        List<ConveyorEdge> edges = List.of(new ConveyorEdge("A", "B"));

        Optional<List<String>> route = BaggageRoutingBfs.shortestRoute(edges, "A", "B");

        assertTrue(route.isPresent());
        assertEquals(2, route.get().size());
    }

    @Test
    public void shorterMultiHopRouteIsChosenOverLongerAlternative() {
        List<ConveyorEdge> edges = List.of(
                new ConveyorEdge("A", "B"),
                new ConveyorEdge("B", "C"),
                new ConveyorEdge("A", "X"),
                new ConveyorEdge("X", "Y"),
                new ConveyorEdge("Y", "C"));

        Optional<List<String>> route = BaggageRoutingBfs.shortestRoute(edges, "A", "C");

        assertTrue(route.isPresent());
        assertEquals(3, route.get().size()); // A-B-C, not A-X-Y-C
        assertValidPath(edges, route.get());
    }

    @Test
    public void unreachableNode_returnsEmpty() {
        List<ConveyorEdge> edges = List.of(new ConveyorEdge("A", "B"), new ConveyorEdge("C", "D"));

        assertTrue(BaggageRoutingBfs.shortestRoute(edges, "A", "D").isEmpty());
    }

    @Test
    public void sameStartAndEnd_returnsSingleNodePath() {
        List<ConveyorEdge> edges = List.of(new ConveyorEdge("A", "B"));

        Optional<List<String>> route = BaggageRoutingBfs.shortestRoute(edges, "A", "A");

        assertEquals(Optional.of(List.of("A")), route);
    }

    @Test
    public void unknownNode_throws() {
        List<ConveyorEdge> edges = List.of(new ConveyorEdge("A", "B"));

        assertThrows(IllegalArgumentException.class, () -> BaggageRoutingBfs.shortestRoute(edges, "A", "Z"));
    }
}
