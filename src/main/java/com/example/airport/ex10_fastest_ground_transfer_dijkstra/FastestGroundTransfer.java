package com.example.airport.ex10_fastest_ground_transfer_dijkstra;

import java.util.List;
import java.util.Optional;

public class FastestGroundTransfer {

    /**
     * See exercises/ex10-fastest-ground-transfer-dijkstra/README.md.
     * TODO: implement.
     */
    public static Optional<Integer> fastestTransferMinutes(List<Walkway> walkways, String fromGate, String toGate) {
        return null;
    }

    public record Walkway(String fromGate, String toGate, int minutesWeight) {
    }
}
