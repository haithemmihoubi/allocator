package com.example.airport.ex18_gate_flight_matching;

import com.example.airport.ex18_gate_flight_matching.GateFlightMatching.CostEntry;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TestCaseQ18 {

    private static double totalCost(Map<String, Integer> assignment, List<CostEntry> costs) {
        double total = 0;
        for (Map.Entry<String, Integer> e : assignment.entrySet()) {
            total += costs.stream()
                    .filter(c -> c.flightId().equals(e.getKey()) && c.gateId() == e.getValue())
                    .findFirst().orElseThrow().cost();
        }
        return total;
    }

    @Test
    public void obviousGreedyPickIsWrong_optimalSwapsToLowerTotal() {
        // Greedy per-flight cheapest pick: F1->G1 (1), forcing F2->G2 (10) = 11 total.
        // Optimal: F1->G2 (2), F2->G1 (2) = 4 total.
        List<CostEntry> costs = List.of(
                new CostEntry("F1", 1, 1),
                new CostEntry("F1", 2, 2),
                new CostEntry("F2", 1, 2),
                new CostEntry("F2", 2, 10));

        Optional<Map<String, Integer>> result =
                GateFlightMatching.minimumCostMatching(List.of("F1", "F2"), List.of(1, 2), costs);

        assertTrue(result.isPresent());
        assertEquals(4.0, totalCost(result.get(), costs), 0.001);
    }

    @Test
    public void infeasibleFullAssignment_returnsEmpty() {
        // Both flights can only use gate 1 -> no perfect matching possible.
        List<CostEntry> costs = List.of(
                new CostEntry("F1", 1, 5),
                new CostEntry("F2", 1, 3));

        Optional<Map<String, Integer>> result =
                GateFlightMatching.minimumCostMatching(List.of("F1", "F2"), List.of(1, 2), costs);

        assertTrue(result.isEmpty());
    }

    @Test
    public void singleFlightSingleGate_trivialMatch() {
        List<CostEntry> costs = List.of(new CostEntry("F1", 1, 7));

        Optional<Map<String, Integer>> result =
                GateFlightMatching.minimumCostMatching(List.of("F1"), List.of(1), costs);

        assertEquals(Optional.of(Map.of("F1", 1)), result);
    }

    @Test
    public void tiedOptimalCost_anyMatchingIsAcceptedIfTotalIsOptimal() {
        List<CostEntry> costs = List.of(
                new CostEntry("F1", 1, 5),
                new CostEntry("F1", 2, 5),
                new CostEntry("F2", 1, 5),
                new CostEntry("F2", 2, 5));

        Optional<Map<String, Integer>> result =
                GateFlightMatching.minimumCostMatching(List.of("F1", "F2"), List.of(1, 2), costs);

        assertTrue(result.isPresent());
        assertEquals(10.0, totalCost(result.get(), costs), 0.001);
    }

    @Test
    public void negativeCost_throws() {
        List<CostEntry> costs = List.of(new CostEntry("F1", 1, -1));

        assertThrows(IllegalArgumentException.class,
                () -> GateFlightMatching.minimumCostMatching(List.of("F1"), List.of(1), costs));
    }
}
