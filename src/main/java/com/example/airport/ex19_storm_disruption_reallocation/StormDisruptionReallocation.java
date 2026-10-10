package com.example.airport.ex19_storm_disruption_reallocation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class StormDisruptionReallocation {

    /**
     * See exercises/ex19-storm-disruption-reallocation/README.md.
     * TODO: implement.
     */
    public static Optional<Map<String, StandAssignment>> reallocate(List<Stand> stands, List<FlightRequest> flights) {
        return null;
    }

    public record TimeWindow(LocalDateTime start, LocalDateTime end) {
    }

    public record Stand(int id, Set<String> capabilities, List<TimeWindow> blockedWindows) {
    }

    public record FlightRequest(
            String flightId,
            Set<String> requiredCapabilities,
            LocalDateTime earliestStart,
            LocalDateTime latestEnd,
            int durationMinutes) {
    }

    public record StandAssignment(int standId, LocalDateTime start, LocalDateTime end) {
    }
}
