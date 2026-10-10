package com.example.airport.ex12_max_runway_slot_usage;

import java.time.LocalDateTime;
import java.util.List;

public class MaxRunwaySlotUsage {

    /**
     * See exercises/ex12-max-runway-slot-usage/README.md.
     * TODO: implement.
     */
    public static List<String> maxNonOverlappingSlots(List<SlotRequest> requests) {
        return null;
    }

    public record SlotRequest(String flightId, LocalDateTime start, LocalDateTime end) {
    }
}
