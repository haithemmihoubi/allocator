package com.example.airport.ex11_delay_propagation_topo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class DelayPropagation {

    /**
     * See exercises/ex11-delay-propagation-topo/README.md.
     * TODO: implement.
     */
    public static Map<String, LocalDateTime> computeActualDepartures(List<FlightLeg> legs) {
        return null;
    }

    public record Dependency(String dependsOnLegId, int minGapMinutes) {
    }

    public record FlightLeg(
            String id,
            LocalDateTime scheduledDeparture,
            int durationMinutes,
            List<Dependency> dependsOn) {
    }
}
