package com.example.airport.ex18_gate_flight_matching;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GateFlightMatching {

    /**
     * See exercises/ex18-gate-flight-matching/README.md.
     * TODO: implement.
     */
    public static Optional<Map<String, Integer>> minimumCostMatching(
            List<String> flightIds, List<Integer> gateIds, List<CostEntry> costs) {
        return null;
    }

    public record CostEntry(String flightId, int gateId, double cost) {
    }
}
