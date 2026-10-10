package com.example.airport.ex16_air_traffic_separation_sweep;

import java.time.LocalDateTime;
import java.util.List;

public class AirTrafficSeparation {

    /**
     * See exercises/ex16-air-traffic-separation-sweep/README.md.
     * TODO: implement.
     */
    public static List<String> violations(List<Landing> landings, int minSeparationMinutes) {
        return null;
    }

    /**
     * TODO: implement.
     */
    public static int minimumDelayMinutesToFix(List<Landing> landings, int minSeparationMinutes) {
        return 0;
    }

    public record Landing(String flightId, LocalDateTime scheduledTime) {
    }
}
